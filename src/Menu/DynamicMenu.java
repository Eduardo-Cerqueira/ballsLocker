package Menu;

import Struct.MenuItem;

import java.util.List;
import java.util.Scanner;

public class DynamicMenu {
    /**
     * This function prints a menu list to the console and returns the input using the interactWithKey/interactWithMenu function
     * @param menuBuilder A menu building containing all information about menu
     * @return User input as integer
     */
    public static int generateMenu(MenuBuilder menuBuilder) {
        for (int i = 0; i < menuBuilder.getMenu().size(); i++) {
            String string = (i + 1) +
                    ". " +
                    (menuBuilder.isOnHelpMode() ? menuBuilder.getMenu().get(i).choice().concat(" => ").concat(menuBuilder.getMenu().get(i).helper()) : menuBuilder.getMenu().get(i).choice());

            System.out.println(string);
        }

        if (menuBuilder.isOnHelpMode()) {
            System.out.println("\nYou can request help using '".concat(Integer.toString(menuBuilder.getHelpKey())).concat("'"));
        } else {
            System.out.println("\nYou can leave using '".concat(Integer.toString(menuBuilder.getQuitKey())).concat("'"));
        }

        return menuBuilder.isOnHelpMode()
                ? DynamicMenu.interactWithKey(menuBuilder.getQuestion(), menuBuilder.getQuitKey(), !menuBuilder.isOnHelpMode())
                : DynamicMenu.interactWithMenu(menuBuilder.getMenu(), menuBuilder.getQuestion(), menuBuilder.getQuitKey(), menuBuilder.getHelpKey());
    }

    /**
     * This function prints a question, waits for a valid user input and return the input when it's valid
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @return  User input as integer
     */
    public static int interactWithMenu(List<MenuItem> menu, String question, int quitKey, int helpKey) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(question);
            String choice = scanner.nextLine();

            if (choice.equals(String.valueOf(quitKey)) || choice.equals(String.valueOf(helpKey))) {
                return Integer.parseInt(choice);
            }

            for (int i = 0; i < menu.size(); i++) {
                if (choice.equals(String.valueOf(i + 1))) {
                    return Integer.parseInt(choice);
                }
            }
        }
    }

    /**
     * This function check for a given input and return when input is given
     * @param question Question to ask when requesting user input
     * @param quitKey Input key to check for
     * @param hasQuestion If interaction has a question to be displayed
     * @return  User input as integer
     */
    public static int interactWithKey(String question, int quitKey, boolean hasQuestion) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (hasQuestion) System.out.println(question);
            String input = scanner.nextLine();

            if (input.equals(String.valueOf(quitKey))) {
                return Integer.parseInt(input);
            }
        }
    }
}
