package Cipher;

import Helpers.Validation;

public class ROT {
    static Validation validation = new Validation();

    public String encrypt(String wordToEncrypt, int numberOfRotate, boolean removeSpaces) {
        String space = removeSpaces ? "" : " ";
        String cleanWordToEncrypt = wordToEncrypt.replaceAll("[^A-Za-z]+", space);

        StringBuilder encryptedWord = new StringBuilder();

        for (int i = 0; i < cleanWordToEncrypt.length(); i++) {
            char letter = cleanWordToEncrypt.charAt(i);
            int letterIndex = validation.isLowerCase(String.valueOf(letter)) ? 97 : 65;
            int newLetterPosition = ((int) letter - letterIndex) == 0 ? 26 : ((int) letter - letterIndex);

            encryptedWord.append(letter != ' ' ? (char) (((newLetterPosition + numberOfRotate) % 26) + letterIndex) : space);
        }

        return encryptedWord.toString();
    }

    public String decrypt(String wordToEncrypt, int numberOfRotate) {
        StringBuilder encryptedWord = new StringBuilder();

        for (int i = 0; i < wordToEncrypt.length(); i++) {
            char letter = wordToEncrypt.charAt(i);
            int letterIndex = validation.isLowerCase(String.valueOf(letter)) ? 97 : 65;
            int newLetterPosition = ((int) letter - letterIndex) < numberOfRotate ? ((int) letter - letterIndex) + 26 : ((int) letter - letterIndex);

            encryptedWord.append(letter != ' ' ? (char) (((newLetterPosition - numberOfRotate) % 26) + letterIndex) : " ");
        }

        return encryptedWord.toString();
    }
}
