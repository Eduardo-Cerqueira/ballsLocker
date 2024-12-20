package Menu;

import Struct.MenuItem;

import java.util.List;
import java.util.Scanner;

public class DynamicMenu {
    /**
     * This function prints a menu list to the console and returns the menu input;
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @param exitKey Key to press to exit the program
     * @param helpKey Key to press to get help
     * @return User input as integer
     */
    public static int generateMenu(List<MenuItem> menu, String question, int exitKey, int helpKey) {
        displayMenu(menu, false);

        String leaveString = "You can leave using '".concat(Integer.toString(exitKey)).concat("'");
        String helpString = "\nYou can get help using '".concat(Integer.toString(helpKey)).concat("'");

        System.out.println(helpString);
        System.out.println(leaveString);

        int key = interactWithMenu(menu, question, exitKey, helpKey);

        while (key == helpKey) {
            displayMenu(menu, true);
            System.out.println("\nYou can hide the help menu using '".concat(Integer.toString(helpKey)).concat("'"));
            System.out.println(leaveString);
            key = interactWithMenu(menu, question, exitKey, helpKey);

            if (key == helpKey) {
                displayMenu(menu, false);
                System.out.println(helpString);
                System.out.println(leaveString);
                key = interactWithMenu(menu, question, exitKey, helpKey);
            }
        }

        return key;
    }

    /**
     * This function prints a menu list to the console
     * @param menu Each menu entry that can be chosen
     * @param helpMode Which menu to display
     */
    private static void displayMenu(List<MenuItem> menu, boolean helpMode) {
        for (int i = 0; i < menu.size(); i++) {
            String string = (i + 1) +
                    ". " +
                    (helpMode ? menu.get(i).choice().concat(" => ").concat(menu.get(i).helper()) : menu.get(i).choice());

            System.out.println(string);
        }
    }

    /**
     * This function prints a question, waits for a valid user input and return the input when it's valid
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @return  User input as integer
     */
    public static int interactWithMenu(List<MenuItem> menu, String question, int exitKey, int helpKey) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(question);
            String choice = scanner.nextLine();

            if (choice.equals(String.valueOf(exitKey)) || choice.equals(String.valueOf(helpKey))) {
                return Integer.parseInt(choice);
            }

            for (int i = 0; i < menu.size(); i++) {
                if (choice.equals(String.valueOf(i + 1))) {
                    return Integer.parseInt(choice);
                }
            }
        }
    }

    public static void displayLogo(){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        // Texte à afficher
        String text =
                " _           _ _     _               _             \n" +
                        "| |__   __ _| | |___| |    ___   ___| | _____ _ __ \n" +
                        "| '_ \\ / _` | | / __| |   / _ \\ / __| |/ / _ \\ '__|\n" +
                        "| |_) | (_| | | \\__ \\ |__| (_) | (__|   <  __/ |   \n" +
                        "|_.__/ \\__,_|_|_|___/_____\\___/ \\___|_|\\_\\___|_|   \n";

        String[] lines = text.split("\n");
        for (String line : lines) {
            System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + line + ANSI_RESET);
        }
    }
}
