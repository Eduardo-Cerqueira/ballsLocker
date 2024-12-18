package Steganography;

import Helpers.Convert;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;


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
            Pixel[] pixels = extractPixels(imageToEncrypt);
            // Convert the message to a binary array
            String[] messageInBinary = convertMessageToBinaryArray(message);
            encodeMessageBinaryInPixels(pixels, messageInBinary);
            ReplacePixelsInNewBufferedImage(pixels, image);
            SaveNewFile(image, newImageFile);
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
     * Extract the pixels from the image.
     * @param imageToEncrypt The image to encrypt.
     * @return The pixels of the image.
     */
    private static Pixel[] extractPixels(BufferedImage imageToEncrypt){
        int height = imageToEncrypt.getHeight();
        int width = imageToEncrypt.getWidth();
        // Create an array of empty pixels
        Pixel[] pixels = new Pixel[height * width];
        int count = 0;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                // Get the color of the pixel at the specified coordinates
                Color colorToAdd = new Color(imageToEncrypt.getRGB(x, y));
                pixels[count] = new Pixel(x, y, colorToAdd);
                count++;
            }
        }
        return pixels;
    };

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
            ChangePixelsColor(messageBinary[i], currentPixels, isLastCharacter);
            // Update the index to point to the next triplet of pixels
            pixelIndex = pixelIndex + 3;
        }
    }

    private static void ChangePixelsColor(String messageBinary, Pixel[] pixels, boolean isLastCharacter) {
        int messageBinaryIndex = 0;
        for(int i =0; i < pixels.length-1; i++) {
            char[] messageBinaryChars = new char[] {messageBinary.charAt(messageBinaryIndex), messageBinary.charAt(messageBinaryIndex+1), messageBinary.charAt(messageBinaryIndex+2)};
            String[] pixelRGBBinary = GetPixelsRGBBinary(pixels[i], messageBinaryChars);
            pixels[i].setColor(GetNewPixelColor(pixelRGBBinary));
            messageBinaryIndex = messageBinaryIndex + 3;
        }
        if(!isLastCharacter) {
            char[] messageBinaryChars = new char[] {messageBinary.charAt(messageBinaryIndex), messageBinary.charAt(messageBinaryIndex+1), '1'};
            String[] pixelRGBBinary = GetPixelsRGBBinary(pixels[pixels.length-1], messageBinaryChars);
            pixels[pixels.length-1].setColor(GetNewPixelColor(pixelRGBBinary));
        }else {
            char[] messageBinaryChars = new char[] {messageBinary.charAt(messageBinaryIndex), messageBinary.charAt(messageBinaryIndex+1), '0'};
            String[] pixelRGBBinary = GetPixelsRGBBinary(pixels[pixels.length-1], messageBinaryChars);
            pixels[pixels.length-1].setColor(GetNewPixelColor(pixelRGBBinary));
        }
    }

    private static String[] GetPixelsRGBBinary(Pixel pixel, char[] messageBinaryChars) {
        return new String[] {
            ChangePixelBinary(Integer.toBinaryString(pixel.getColor().getRed()), messageBinaryChars[0]),
            ChangePixelBinary(Integer.toBinaryString(pixel.getColor().getGreen()), messageBinaryChars[1]),
            ChangePixelBinary(Integer.toBinaryString(pixel.getColor().getBlue()), messageBinaryChars[2])
        };
    }

    private static String ChangePixelBinary(String pixelBinary, char messageBinaryChar) {
        StringBuilder sb = new StringBuilder(pixelBinary);
        sb.setCharAt(pixelBinary.length()-1, messageBinaryChar);
        return sb.toString();
    }

    private static Color GetNewPixelColor(String[] colorBinary) {
        return new Color(Integer.parseInt(colorBinary[0], 2), Integer.parseInt(colorBinary[1], 2), Integer.parseInt(colorBinary[2], 2));
    }

    private static void ReplacePixelsInNewBufferedImage(Pixel[] newPixels, BufferedImage newImage) {
        for(int i = 0; i < newPixels.length; i++) {
            newImage.setRGB(newPixels[i].getX(), newPixels[i].getY(), newPixels[i].getColor().getRGB());
        }
    };

    private static void SaveNewFile(BufferedImage newImage, File newImageFile) {
        try {
            ImageIO.write(newImage, "png", newImageFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}