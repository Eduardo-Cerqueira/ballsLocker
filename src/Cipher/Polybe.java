package Cipher;

import java.util.Arrays;

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

    private String message;
    private SquareMethode methode;
    private char[][] polybeSquare;

    // Constructor
    public Polybe(String message, SquareMethode methode){
        this.message = message;
        this.methode = methode;
        this.polybeSquare = generateSquare();
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public char[][] getPolybeSquare() {
        return polybeSquare;
    }

    public void setPolybeSquare(char[][] polybeSquare) {
        this.polybeSquare = polybeSquare;
    }

    /**
     * Returns a 5x5 matrix with the alphabet letters depending on the methode
     * @return : polybe square
     */
    private char[][] generateSquare(){
        return switch (this.methode) {
            case VERTICAL -> generateVerticalSquare(false);
            case INVERTED_HORIZONTAL -> generateHorizontalSquare(true);
            case INVERTED_VERTICAL -> generateVerticalSquare(true);
            default -> generateHorizontalSquare(false);
        };
    }

    /**
     * Returns a 5x5 matrix with the alphabet letters in a horizontal way
     * @param inverted : if true, the letters are in a inverted way
     * @return : horizontal 5x5 matrix
     * */
    private char[][] generateHorizontalSquare(boolean inverted) {
        char[][] square = new char[5][5];
        int letterIndex = inverted ? polybeBaseCharacters.length : 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                square[i][j] = polybeBaseCharacters[letterIndex];
                letterIndex += inverted ? -1 : 1;
            }
        }
        return square;
    }

    /**
     * Returns a 5x5 matrix with the alphabet letters in a vertical way
     * @param inverted : if true, the letters are in a inverted way
     * @return : vertical 5x5 matrix
     * */
    private char[][] generateVerticalSquare(boolean inverted) {
        char[][] square = new char[5][5];
        int letterIndex = inverted ? polybeBaseCharacters.length : 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                square[j][i] = polybeBaseCharacters[letterIndex];
                letterIndex += inverted ? -1 : 1;
            }
        }
        return square;
    }

    /**
     * Returns the encrypted message
     * @return : the encrypted message
     */
    private String encrypt(String message) {
       return "";
    }

    /**
     * Returns the decrypted message
     * @return : the decrypted message
     */
    private String decrypt() {
        return "";
    }

    public void printSquare(){
        System.out.println("Cipher.Polybe Square:");
        for (char[] row : this.polybeSquare){
            System.out.println(Arrays.toString(row));
        }

    }

    public static void main(String[] args) {
        Polybe polybe = new Polybe("Message", SquareMethode.VERTICAL);
        char[][] square =  polybe.getPolybeSquare();
        polybe.printSquare();
    }
}
