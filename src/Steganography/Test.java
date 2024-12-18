package Steganography;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        String inputImagePath = "image.png";
        String message = "TESTING";
        Encrypt encrypt = new Encrypt();
        Decrypt decrypt = new Decrypt();

//        encrypt.Encrypt(new File(inputImagePath), message);
        decrypt.Decrypt();
    }
}
