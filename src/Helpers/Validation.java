package Helpers;

import java.util.Scanner;

public class Validation {
    /**
     * Check if a text is LowerCase or UpperCase, return true if it's LowerCase
     * @param word Text to validate/check
     * @return Boolean stating if text is LowerCase or UpperCase
     */
    public boolean isLowerCase(String word) {
        return word.equals(word.toLowerCase());
    }

    /**
     * This function prompts for input until valid input is provided (here a string)
     * @param question Question displayed to request input
     * @param errorMessage Message displayed on generic error
     * @return String from the input
     */
    public static String inputString(String question, String errorMessage) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(question);
                String string = scanner.nextLine();

                if (string.isBlank()) {
                    throw new Error();
                }
                
                scanner.close();
                return string;
            } catch (NumberFormatException | Error e) {
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * This function prompts for input until valid input is provided (here a integer)
     * @param question Question displayed to request input
     * @param errorMessage Message displayed on generic error
     * @return Integer from the input
     */
    public static int inputInteger(String question, String errorMessage) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(question);
                String integer = scanner.nextLine();

                int parsed = Integer.parseInt(integer);
                scanner.close();
                return parsed;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage); // if number < -2147483648 (Integer.MIN_VALUE) or number > 2147483648 (Integer.MAX_VALUE)
            }
        }
    }
}
