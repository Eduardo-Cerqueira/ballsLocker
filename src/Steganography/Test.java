package Steganography;

public class Test {
    public static void main(String[] args) {
        String inputImagePath = "image.png";
        String outputImagePath = "export-" + inputImagePath;
        String message = "Bonsoir à tous !";

        Encrypt.encrypt(inputImagePath, message);
        String decryptedMessage = Decrypt.decrypt(outputImagePath);
        System.out.println(decryptedMessage);
    }
}
