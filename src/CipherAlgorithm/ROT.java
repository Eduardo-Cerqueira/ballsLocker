package CipherAlgorithm;

import Helpers.Validation;

public class ROT {
    static Validation validation = new Validation();

    /**
     * This function encrypts a word/sentence using ROT algorithm and return the encrypted result
     * NOTE: The function will remove all special characters and only keep spaces if removeSpaces is false
     * @param wordToEncrypt Word/sentence to encrypt
     * @param numberOfRotate Number of letter rotations to use
     * @param removeSpaces If the encrypted sentence should keep the spaces
     * @return Encrypted word/sentence
     */
    public String encrypt(String wordToEncrypt, int numberOfRotate, boolean removeSpaces) {
        String space = removeSpaces ? "" : " ";

        // Remove all special characters
        String cleanWordToEncrypt = wordToEncrypt.replaceAll("[^A-Za-z]+", space);

        StringBuilder encryptedWord = new StringBuilder();

        for (int i = 0; i < cleanWordToEncrypt.length(); i++) {
            char letter = cleanWordToEncrypt.charAt(i);

            // Use different ascii index following if letter is lowercase or uppercase
            int letterIndex = validation.isLowerCase(String.valueOf(letter)) ? 97 : 65;

            int letterAlphabetPosition = ((int) letter - letterIndex) < 0 ? 26 : ((int) letter - letterIndex);
            int letterAlphabetPositionWithRotate = letterAlphabetPosition + numberOfRotate;
            int letterAlphabetPositionWithoutNegativeValue = letterAlphabetPositionWithRotate < 0 ? 26 + (letterAlphabetPositionWithRotate % 26) : letterAlphabetPositionWithRotate;
            int letterASCIIPosition = (letterAlphabetPositionWithoutNegativeValue % 26) + letterIndex;

            encryptedWord.append(letter != ' ' ? (char) letterASCIIPosition : space);
        }

        return encryptedWord.toString();
    }

    /**
     * This function decrypts a word/sentence using ROT algorithm and return the decrypted result
     * @param wordToEncrypt Encrypted word to decrypt
     * @param numberOfRotate Number of letter rotations to use (should be the same used during encryption)
     * @return Decrypted word/sentence
     */
    public String decrypt(String wordToEncrypt, int numberOfRotate) {
        StringBuilder encryptedWord = new StringBuilder();

        for (int i = 0; i < wordToEncrypt.length(); i++) {
            char letter = wordToEncrypt.charAt(i);

            // Use different ascii index following if letter is lowercase or uppercase
            int letterIndex = validation.isLowerCase(String.valueOf(letter)) ? 97 : 65;

            int newLetterPosition = ((int) letter - letterIndex) < numberOfRotate ? ((int) letter - letterIndex) + 26 : ((int) letter - letterIndex);

            encryptedWord.append(letter != ' ' ? (char) (((newLetterPosition - numberOfRotate) % 26) + letterIndex) : " ");
        }

        return encryptedWord.toString();
    }
}
