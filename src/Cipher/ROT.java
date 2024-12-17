package Cipher;

public class ROT {
    public String encrypt(String wordToEncrypt, int numberOfRotate, boolean shouldMessageBeLowercase) {
        StringBuilder encryptedWord = new StringBuilder();

        for (int i = 0; i < wordToEncrypt.length(); i++) {
            int letterIndex = shouldMessageBeLowercase ? 97 : 65;
            char letter = shouldMessageBeLowercase ? wordToEncrypt.toLowerCase().charAt(i) : wordToEncrypt.toUpperCase().charAt(i);
            int newLetterPosition = ((int) letter - letterIndex) == 0 ? 26 : ((int) letter - letterIndex);

            encryptedWord.append(letter != '\n' ? (char) (((newLetterPosition + numberOfRotate) % 26) + letterIndex) : "");
        }

        return encryptedWord.toString();
    }
}
