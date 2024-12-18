package Steganography;

public class Test {
    public static void main(String[] args) {
        String inputImagePath = "image.png";
        String outputImagePath = "export.png";
        String message = "Salut l'équipe !";
        Encrypt encrypt = new Encrypt();
        Decrypt decrypt = new Decrypt();

        encrypt.encrypt(inputImagePath, outputImagePath, message);
        decrypt.Decrypt();
    }
}
