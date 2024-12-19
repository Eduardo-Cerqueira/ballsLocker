package Steganography;

public class Test {
    public static void main(String[] args) {
        String inputImagePath = "image.png";
        String outputImagePath = "export.png";
        String message = "Salut l'équipe !";

        Encrypt.encrypt(inputImagePath, outputImagePath, message);
        String decryptedMessage = Decrypt.decrypt(outputImagePath);
        System.out.println(decryptedMessage);
    }
}
