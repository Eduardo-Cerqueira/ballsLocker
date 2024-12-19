package Steganography;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Decrypt {
    /**
     * Decrypts the message in the image.
     * @param inputImagePath The path of the image to decrypt.
     * @return The decrypted message.
     */
    public static String decrypt(String inputImagePath) {
        String directory = "images/";
        // Get input file by path
        File inputFile = new File(directory + inputImagePath);
        try {
            // Read image
            BufferedImage image = ImageIO.read(inputFile);
            // Extract the pixels from the image
            Pixel[] pixels = Common.extractPixels(image);
            // Return decoded message
            return decodeMessageFromPixels(pixels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decodes the message from the pixels.
     * @param pixels The pixels to decode.
     * @return The decoded message.
     */
    private static String decodeMessageFromPixels(Pixel[] pixels) {
        boolean completed = false;
        int pixelArrayIndex = 0;
        StringBuilder messageBuilder = new StringBuilder();
        while(!completed) {
            // Read 3 pixels
            Pixel[] pixelsToRead = new Pixel[3];
            for(int i = 0; i < 3; i++) {
                pixelsToRead[i] = pixels[pixelArrayIndex];
                pixelArrayIndex++;
            }
            // Convert the 3 pixels to a character
            messageBuilder.append(convertPixelsToCharacter(pixelsToRead));
            // Check if the message has ended
            if(isEndOfMessage(pixelsToRead[2])) {
                completed = true;
            }
        }
        return messageBuilder.toString();
    }

    /**
     * Converts 3 pixels to a character.
     * @param pixelsToRead The pixels to convert.
     * @return The character.
     */
    private static char convertPixelsToCharacter(Pixel[] pixelsToRead) {
        ArrayList<String> binaryValues = new ArrayList<>();
        for (Pixel pixel : pixelsToRead) {
            String[] currentBinary = convertPixelToBinary(pixel);
            binaryValues.add(currentBinary[0]);
            binaryValues.add(currentBinary[1]);
            binaryValues.add(currentBinary[2]);
        }
        return convertBinaryValuesToCharacter(binaryValues);
    }

    /**
     * Converts a pixel to a binary string array.
     * @param pixel The pixel to convert.
     * @return The binary string array.
     */
    private static String[] convertPixelToBinary(Pixel pixel) {
        return new String[] {
            Integer.toBinaryString(pixel.getColor().getRed()),
            Integer.toBinaryString(pixel.getColor().getGreen()),
            Integer.toBinaryString(pixel.getColor().getBlue())
        };
    }

    /**
     * Check if the message has ended.
     * If the blue component of the pixel equals 1 : the message has not ended.
     * @param pixel The pixel to check.
     * @return True if the message has ended, false otherwise.
     */
    private static boolean isEndOfMessage(Pixel pixel) {
        return !convertPixelToBinary(pixel)[2].endsWith("1");
    }

    /**
     * Converts an array of binary values to a character.
     * @param binaryValues The binary values to convert.
     * @return The character.
     */
    private static char convertBinaryValuesToCharacter(ArrayList<String> binaryValues) {
        StringBuilder endBinary = new StringBuilder();
        for(int i = 0; i < binaryValues.size()-1; i++) {
            // Append the last bit of each binary value
            endBinary.append(binaryValues.get(i).charAt(binaryValues.get(i).length()-1));
        }
        String endBinaryString = endBinary.toString();
        // Remove padded zeros
        String noZeros = removePaddedZeros(endBinaryString);
        // Convert the binary string to an ASCII character
        int ascii = Integer.parseInt(noZeros, 2);
        // Return the character
        return (char) ascii;
    }

    /**
     * Removes the padded zeros from the binary string.
     * @param endBinary The binary string to remove the padded zeros from.
     * @return The binary string without the padded zeros.
     */
    private static String removePaddedZeros(String endBinary) {
        int paddedZeros = 0;
        // While the character is a zero and the padded zeros are less than the length of the binary string
        while (paddedZeros < endBinary.length() && endBinary.charAt(paddedZeros) == '0') {
            paddedZeros++;
        }
        return endBinary.substring(paddedZeros);
    }

}