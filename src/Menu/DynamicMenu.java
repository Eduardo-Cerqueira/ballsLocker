package Menu;

import Struct.Action;
import Struct.MenuItem;

import java.util.List;
import java.util.Scanner;

public class DynamicMenu {
    public static void displaySubMenu(List<MenuItem> menu, List<MenuItem> previousMenu) {
        int exitKey = menu.size() + 1;
        int menuEntry = DynamicMenu.generateMenu(menu, "\nWhere do you want to go ?", exitKey, 0);
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        while (true) {
            for (int i = 0; i < menu.size(); i++) {
                if (menuEntry == i + 1) {
                    Action action = menu.get(i).executeAction();
                    action.executeAction();

                } else if (menuEntry == exitKey) {
                    if (menu == Menus.HomeMenu) {
                        System.exit(0);
                    } else {
                        return;
                    }
                }
            }

            menuEntry = DynamicMenu.generateMenu(previousMenu, "\nWhere do you want to go ?", exitKey, 0);
        }
    }


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
}
