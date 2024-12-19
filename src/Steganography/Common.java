package Steganography;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Common {

    /**
     * Extract the pixels from the image.
     * @param imageToEncrypt The image to encrypt.
     * @return The pixels of the image.
     */
    public static Pixel[] extractPixels(BufferedImage imageToEncrypt){
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
    }
}
