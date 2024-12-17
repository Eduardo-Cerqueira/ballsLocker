package Cipher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Polybe {

    public enum SquareMethode {
        HORIZONTAL,
        INVERTED_HORIZONTAL,
        VERTICAL,
        INVERTED_VERTICAL,
    }

    // polybe base square
    private final char[] polybeBaseCharacters = {
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'X', 'Y', 'Z'
    };

    private SquareMethode methode;
    private char[][] polybeSquare;
    private Map<Character, String> positionMap;

    // Constructor
    public Polybe(SquareMethode methode) {
        this.methode = methode;
        this.polybeSquare = generateSquare();
        this.positionMap = buildPositionMap();
    }

    // Getters and Setters
    public char[][] getPolybeSquare() {
        return polybeSquare;
    }

    public void setPolybeSquare(char[][] polybeSquare) {
        this.polybeSquare = polybeSquare;
    }

    public void setMethode(SquareMethode methode) {
        this.methode = methode;
    }

    public Map<Character, String> getPositionMap() {
        return positionMap;
    }

    public void setPositionMap(Map<Character, String> positionMap) {
        this.positionMap = positionMap;
    }

    /**
     * Returns a 5x5 matrix with the alphabet letters depending on the methode
     *
     * @return : polybe square
     */
    private char[][] generateSquare() {
        return switch (this.methode) {
            case VERTICAL -> generateVerticalSquare(false);
            case INVERTED_HORIZONTAL -> generateHorizontalSquare(true);
            case INVERTED_VERTICAL -> generateVerticalSquare(true);
            default -> generateHorizontalSquare(false);
        };
    }

    /**
     * Returns a 5x5 matrix with the alphabet letters in a horizontal way
     *
     * @param inverted : if true, the letters are in a inverted way
     * @return : horizontal 5x5 matrix
     */
    private char[][] generateHorizontalSquare(boolean inverted) {
        char[][] square = new char[5][5];
        int letterIndex = inverted ? polybeBaseCharacters.length - 1 : 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                square[i][j] = polybeBaseCharacters[letterIndex];
                letterIndex += inverted ? -1 : 1;
            }
        }
        return square;
    }

    /**
     * Returns a 5x5 matrix with the alphabet letters in a vertical way
     *
     * @param inverted : if true, the letters are in a inverted way
     * @return : vertical 5x5 matrix
     */
    private char[][] generateVerticalSquare(boolean inverted) {
        char[][] square = new char[5][5];
        int letterIndex = inverted ? polybeBaseCharacters.length - 1 : 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                square[j][i] = polybeBaseCharacters[letterIndex];
                letterIndex += inverted ? -1 : 1;
            }
        }
        return square;
    }

    private Map<Character, String> buildPositionMap() {
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map.put(polybeSquare[i][j], "" + i + j);
            }
        }
        return map;
    }


        /**
         * Prints the polybe square
         */
    public void printSquare() {
        System.out.println("Polybe Square:");
        for (char[] row : this.polybeSquare) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Returns the encrypted message
     * @param message : the message to encrypt
     * @return : the encrypted message
     */
    private String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char letter : message.toCharArray()) {
            if (positionMap.containsKey(letter)) {
                encryptedMessage.append(positionMap.get(letter));
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Returns the decrypted message
     *
     * @return : the decrypted message
     */
    private String decrypt(String encryptedMessage) {
        return "";
    }


    public static void main(String[] args) {
        Polybe polybe = new Polybe(SquareMethode.HORIZONTAL);
        polybe.printSquare();

        String message = "HELLO";
        String encryptedMessage = polybe.encrypt(message);
        String decryptedMessage = polybe.decrypt(encryptedMessage);

        System.out.println(message);
        System.out.println(encryptedMessage);
        System.out.println(decryptedMessage);
    }
}
