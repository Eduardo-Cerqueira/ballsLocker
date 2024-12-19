package Steganography;

import Helpers.Convert;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import static Steganography.Common.extractPixels;


public class Encrypt {
    /**
     * Encrypts the message in the image.
     * @param inputFilePath The path of the image to encrypt.
     * @param outputFilePath The path of the new image with the encrypted message.
     * @param message The message to encrypt.
     */
    public static void encrypt(String inputFilePath, String outputFilePath, String message) {

        // Get input file by path
        File inputFile = new File(inputFilePath);
        // Get output file by path
        File newImageFile = new File(outputFilePath);

        try {
            // Read image
            BufferedImage image = ImageIO.read(inputFile);
            // Create a new image with the same properties as the original image
            BufferedImage imageToEncrypt = copyImage(image);
            // Extract the pixels from the image
            Pixel[] pixels = Common.extractPixels(imageToEncrypt);
            // Convert the message to a binary array
            String[] messageInBinary = convertMessageToBinaryArray(message);
            // Encode the message in the pixels
            encodeMessageBinaryInPixels(pixels, messageInBinary);
            // Replace the pixels in a new BufferedImage
            replacePixelsInNewBufferedImage(pixels, image);
            // Save the new image to a file
            saveNewFile(image, newImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy an image with the same properties as input image.
     * @param image The image to encrypt.
     * @return The image to encrypt.
     */
    private static BufferedImage copyImage(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        // Returns whether the alpha channel is premultiplied -> Objective : faithfully reproduce the image
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        // Returns a copy of the image data for this image as a WritableRaster
        // WritableRaster : A two-dimensional array of pixels (stored as integers) that represent an image.
        WritableRaster raster = image.copyData(null);
        // Create a new image with the same properties as the original image
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }

    /**
     * Convert the message to a binary array.
     * @param message The message to encrypt.
     * @return The message in binary.
     */
    private static String[] convertMessageToBinaryArray(String message) {
        // Convert the message to a String containing the binary representation of each character
        String fullBinary = Convert.convertStringToBinary(message);
        // Create an empty array of String
        String[] binaryArray = new String[message.length()];
        for (int i = 0; i < message.length(); i++) {
            // Fill the array with the binary representation of each character (8 bits)
            binaryArray[i] = fullBinary.substring(i * 8, (i + 1) * 8);
        }
        return binaryArray;
    }

    /**
     * Encode the message in the pixels.
     * @param pixels The pixels of the image.
     * @param messageBinary The message in binary.
     */
    private static void encodeMessageBinaryInPixels(Pixel[] pixels, String[] messageBinary) {
        int pixelIndex = 0;
        boolean isLastCharacter = false;

        // Loop through each character of the message, represented in binary
        for (int i = 0; i < messageBinary.length; i++) {
            // Select 3 consecutive pixels to encode one character.
            // Each pixel has 3 color components (Red, Green, and Blue), and each component can store 1 bit of the message.
            // So, 3 pixels will store enough information for 8 bits of a character, plus 1 extra bit for the control bit.
            Pixel[] currentPixels = new Pixel[] {pixels[pixelIndex], pixels[pixelIndex + 1], pixels[pixelIndex + 2]};
            // Check if this is the last character of the message.
            if (i + 1 == messageBinary.length) {
                isLastCharacter = true;
            }
            // Call method to change the color of the 3 pixels based on the binary character
            changePixelsColor(messageBinary[i], currentPixels, isLastCharacter);
            // Update the index to point to the next triplet of pixels
            pixelIndex = pixelIndex + 3;
        }
    }

    /**
     * Change the color of the pixels based on the binary character.
     * @param messageBinary The binary representation of the character.
     * @param pixels The pixels of the image.
     * @param isLastCharacter A boolean indicating if this is the last character of the message.
     */
    private static void changePixelsColor(String messageBinary, Pixel[] pixels, boolean isLastCharacter) {
        int messageBinaryIndex = 0;
        for(int i =0; i < pixels.length-1; i++) {
            char[] messageBinaryChars = new char[] {messageBinary.charAt(messageBinaryIndex), messageBinary.charAt(messageBinaryIndex+1), messageBinary.charAt(messageBinaryIndex+2)};
            String[] pixelRGBBinary = modifyPixelRGBBinary(pixels[i], messageBinaryChars);
            pixels[i].setColor(convertBinaryToColor(pixelRGBBinary));
            messageBinaryIndex = messageBinaryIndex + 3;
        }
        char lastBit = isLastCharacter ? '0' : '1';
        char[] messageBinaryChars = new char[] {messageBinary.charAt(messageBinaryIndex), messageBinary.charAt(messageBinaryIndex + 1), lastBit};
        String[] pixelRGBBinary = modifyPixelRGBBinary(pixels[pixels.length - 1], messageBinaryChars);
        pixels[pixels.length - 1].setColor(convertBinaryToColor(pixelRGBBinary));
    }

    /**
     * Modify the least significant bit of red, green and blue components of a pixel's color.
     * @param pixel The pixel to modify.
     * @param messageBinaryChars The binary representation of the message character.
     * @return An array of strings, each representing the modified binary string of the red, green,
     * and blue components of the pixel's color.
     */
    private static String[] modifyPixelRGBBinary(Pixel pixel, char[] messageBinaryChars) {
        return new String[] {
            // Modify the least significant bit of the red component
            modifyPixelLSB(Integer.toBinaryString(pixel.getColor().getRed()), messageBinaryChars[0]),
            // Modify the least significant bit of the green component
            modifyPixelLSB(Integer.toBinaryString(pixel.getColor().getGreen()), messageBinaryChars[1]),
            // Modify the least significant bit of the blue component
            modifyPixelLSB(Integer.toBinaryString(pixel.getColor().getBlue()), messageBinaryChars[2])
        };
    }

    /**
     * Modify the least significant bit of ONE binary representing a color component.
     * @param pixelBinary The binary representation of the pixel color component.
     * @param messageBinaryChar The character of the message to encode.
     * @return A new binary string where the last bit has been replaced with the provided binary character.
     */
    private static String modifyPixelLSB(String pixelBinary, char messageBinaryChar) {
        StringBuilder sb = new StringBuilder(pixelBinary);
        // Replace the last bit of the pixel with the message bit
        sb.setCharAt(pixelBinary.length()-1, messageBinaryChar);
        return sb.toString();
    }

    /**
     * Convert the binary representation of the color components to a Color object.
     * @param colorBinary The binary representation of the color components.
     * @return The Color object.
     */
    private static Color convertBinaryToColor(String[] colorBinary) {
        // Convert the binary string for the red component into an integer
        int red = Integer.parseInt(colorBinary[0], 2);
        // Convert the binary string for the green component into an integer
        int green = Integer.parseInt(colorBinary[1], 2);
        // Convert the binary string for the blue component into an integer
        int blue = Integer.parseInt(colorBinary[2], 2);
        // Return a Color object containing the decimal values of the red, green, and blue components.
        return new Color(red, green, blue);
    }

    /**
     * Replace the pixels in a new BufferedImage.
     * @param newPixels The new pixels to replace.
     * @param newImage The new image.
     */
    private static void replacePixelsInNewBufferedImage(Pixel[] newPixels, BufferedImage newImage) {
        for(int i = 0; i < newPixels.length; i++) {
            // Set the color of the pixel at the specified coordinates
            newImage.setRGB(newPixels[i].getX(), newPixels[i].getY(), newPixels[i].getColor().getRGB());
        }
    };

    /**
     * Save the new image to a file.
     * @param newImage The new image.
     * @param newImageFile The new image file.
     */
    private static void saveNewFile(BufferedImage newImage, File newImageFile) {
        try {
            // Write the new image to a file
            ImageIO.write(newImage, "png", newImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}