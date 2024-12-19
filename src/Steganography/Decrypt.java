package Steganography;

import java.awt.Color;
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
        // Get input file by path
        File inputFile = new File(inputImagePath);
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

    private static String decodeMessageFromPixels(Pixel[] pixels) {
        boolean completed = false;
        int pixelArrayIndex = 0;
        StringBuilder messageBuilder = new StringBuilder();
        while(!completed) {
            Pixel[] pixelsToRead = new Pixel[3];
            for(int i = 0; i < 3; i++) {
                pixelsToRead[i] = pixels[pixelArrayIndex];
                pixelArrayIndex++;
            }
            messageBuilder.append(ConvertPixelsToCharacter(pixelsToRead));
            if(IsEndOfMessage(pixelsToRead[2])) {
                completed = true;
            }
        }
        return messageBuilder.toString();
    }

    private static char ConvertPixelsToCharacter(Pixel[] pixelsToRead) {
        ArrayList<String> binaryValues = new ArrayList<String>();
        for(int i = 0; i < pixelsToRead.length; i++) {
            String[] currentBinary = TurnPixelIntegersToBinary(pixelsToRead[i]);
            binaryValues.add(currentBinary[0]);
            binaryValues.add(currentBinary[1]);
            binaryValues.add(currentBinary[2]);
        }
        return ConvertBinaryValuesToCharacter(binaryValues);
    }

    private static String[] TurnPixelIntegersToBinary(Pixel pixel) {
        String[] values = new String[3];
        values[0] = Integer.toBinaryString(pixel.getColor().getRed());
        values[1] = Integer.toBinaryString(pixel.getColor().getGreen());
        values[2] = Integer.toBinaryString(pixel.getColor().getBlue());
        return values;
    }

    private static boolean IsEndOfMessage(Pixel pixel) {
        if(TurnPixelIntegersToBinary(pixel)[2].endsWith("1")) {
            return false;
        }
        return true;
    }

    private static char ConvertBinaryValuesToCharacter(ArrayList<String> binaryValues) {
        StringBuilder endBinary = new StringBuilder("");
        for(int i = 0; i < binaryValues.size()-1; i++) {
            endBinary.append(binaryValues.get(i).charAt(binaryValues.get(i).length()-1));
        }
        String endBinaryString = endBinary.toString();
        String noZeros = RemovePaddedZeros(endBinaryString);
        int ascii = Integer.parseInt(noZeros, 2);
        return (char) ascii;
    }

    private static String RemovePaddedZeros(String endBinary) {
        StringBuilder builder = new StringBuilder(endBinary);
        int paddedZeros = 0;
        for(int i = 0; i < builder.length(); i++) {
            if(builder.charAt(i) == '0') {
                paddedZeros++;
            }
            else {
                break;
            }
        }
        for(int i = 0 ; i < paddedZeros; i++) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
}