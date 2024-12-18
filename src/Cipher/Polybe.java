package Cipher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Polybe {

    // Choose method for each generation of polybe square
    public enum SquareMethode {
        HORIZONTAL,
        INVERTED_HORIZONTAL,
        VERTICAL,
        INVERTED_VERTICAL,
    }

    // Array containing all alphabet letters
    private final char[] polybeBaseCharacters = {
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'X', 'Y', 'Z'
    };

    private SquareMethode methode;
    private char[][] polybeSquare;
    private Map<Character, String> positionMap; // (format : {letter=coordinates})

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

    public SquareMethode getMethode() {
        return methode;
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
     * @return polybe square
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
     * @param inverted if true, the letters are in an inverted way
     * @return horizontal 5x5 matrix
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
     * @param inverted if true, the letters are in an inverted way
     * @return vertical 5x5 matrix
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

    /**
     * Associate each character to its coordinates
     * <p>
     * Format : {letter : coordinates} <br/>
     * Example : {"A":"00", "B":"01"}
     * </p>
     * @return position map
     */
    private Map<Character, String> buildPositionMap() {
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Add coordinates for each letter (format : {letter=coordinates})
                map.put(polybeSquare[i][j], "" + i + j);
            }
        }
        return map;
    }

    /**
     * Print the polybe square
     */
    public void printSquare() {
        System.out.println("Polybe Square:");
        for (char[] row : this.polybeSquare) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Returns the encrypted message
     * @param message the message to encrypt
     * @return the encrypted message
     */
    public String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        // Upper message and remove numeric values and special characters
        String cleanMessage = message.toUpperCase().replaceAll("[^A-Z]+", "");
        for (char letter : cleanMessage.toCharArray()) {
            // Replace "W" by "VV" because "W" does not exist in polybe base characters
            if (letter == 'W') {
                encryptedMessage.append("VV");
            // Check if hash map contains letter
            } else if (positionMap.containsKey(letter)) {
                // Add coordinates to encrypted message
                encryptedMessage.append(positionMap.get(letter));
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Returns the decrypted message
     * @param encryptedMessage The encrypted message to decrypt
     * @return the decrypted message
     */
    public String decrypt(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();

        // For loop each pair (coordinates)
        for (int i = 0; i < encryptedMessage.length(); i += 2) {
            String pair = encryptedMessage.substring(i, i + 2);
            // Replace "VV" by "W"
            if (pair.equals("VV")) {
                decryptedMessage.append('W');
            } else {
                // Get row index of the pair (converted to int)
                int row = Character.getNumericValue(pair.charAt(0));  // getNumericValue converts char to int != (int) -> return ascii code
                // Get col index of the pair (converted to int)
                int col = Character.getNumericValue(pair.charAt(1));
                // Add char to message
                decryptedMessage.append(polybeSquare[row][col]);
            }
        }
        return decryptedMessage.toString();
    }
}
