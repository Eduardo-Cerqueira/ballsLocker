package Cipher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Steganography {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Steganography Program!");
        System.out.println("Choose an option:");
        System.out.println("1. Encode a message into an image");
        System.out.println("2. Decode a message from an image");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        switch (choice) {
            case 1 -> {
                System.out.print("Enter the path of the source image: ");
                String sourceImagePath = scanner.nextLine();
                System.out.print("Enter the message to hide: ");
                String message = scanner.nextLine();
                System.out.print("Enter the path to save the encoded image: ");
                String encodedImagePath = scanner.nextLine();

                try {
                    BufferedImage sourceImage = ImageIO.read(new File(sourceImagePath));
                    BufferedImage encodedImage = encodeMessage(sourceImage, message);
                    ImageIO.write(encodedImage, "bmp", new File(encodedImagePath));
                    System.out.println("The encoded image has been saved to: " + encodedImagePath);
                } catch (IOException e) {
                    System.out.println("Error processing the image: " + e.getMessage());
                }
            }
            case 2 -> {
                System.out.print("Enter the path of the encoded image: ");
                String encodedImagePath = scanner.nextLine();

                try {
                    BufferedImage encodedImage = ImageIO.read(new File(encodedImagePath));
                    String decodedMessage = decodeMessage(encodedImage);
                    System.out.println("Decoded message: " + decodedMessage);
                } catch (IOException e) {
                    System.out.println("Error processing the image: " + e.getMessage());
                }
            }
            default -> System.out.println("Invalid choice. Please restart the program.");
        }

        scanner.close();
    }

    // Method to encode a message into an image
    public static BufferedImage encodeMessage(BufferedImage image, String message) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage encodedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Add the null terminator to the message
        message += '\0';

        int messageIndex = 0;
        int messageLength = message.length();
        int bitIndex = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                if (messageIndex < messageLength) {
                    // Get the current character and its current bit
                    char character = message.charAt(messageIndex);
                    int bit = (character >> (7 - bitIndex)) & 1; // Extract the bit (left to right)

                    // Encode the bit into the pixel
                    pixel = (pixel & 0xFFFFFFFE) | bit;

                    // Update the indices
                    bitIndex++;
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        messageIndex++;
                    }
                }

                encodedImage.setRGB(x, y, pixel);
            }
        }
        return encodedImage;
    }

    // Method to decode a hidden message from an image
    public static String decodeMessage(BufferedImage image) {
        StringBuilder message = new StringBuilder();

        int width = image.getWidth();
        int height = image.getHeight();

        int bitIndex = 0;
        int character = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                // Extract the LSB
                int bit = pixel & 1;

                // Reconstruct the current character
                character = (character << 1) | bit;
                bitIndex++;

                // If 8 bits have been retrieved, construct the character
                if (bitIndex == 8) {
                    if (character == 0) { // Null terminator
                        return message.toString();
                    }
                    message.append((char) character);
                    bitIndex = 0;
                    character = 0;
                }
            }
        }
        return message.toString();
    }
}
