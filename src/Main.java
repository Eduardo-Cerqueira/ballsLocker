import Cipher.ROT;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Menu menu = new Menu();
    static ROT rotCipher = new ROT();

    public static void main(String[] args) {

        List<String> homeMenu = Arrays.asList("Crypter un message avec ROT", "Decrypter un message avec ROT");

        int menuEntry = menu.generateMenu(homeMenu, "\nWhere do you want to go ?");
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        if (menuEntry == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Word to encrypt:");
            String word = scanner.nextLine();

            boolean numberIsValid = false;

            while (!numberIsValid) {
                try {
                    System.out.println("Number of rotate:");  // TODO: check if word valid (empty, ...)
                    String numberOfRotate = scanner.nextLine();

                    int parsedInt = Integer.parseInt(numberOfRotate);
                    numberIsValid = true;

                    rotCipher.encrypt(word, parsedInt, false);
                } catch (NumberFormatException e) {
                    System.out.println("Number is invalid !"); // if number < -2147483648 (Integer.MIN_VALUE) or number > 2147483648 (Integer.MAX_VALUE)
                }
            }
        }
    }
}