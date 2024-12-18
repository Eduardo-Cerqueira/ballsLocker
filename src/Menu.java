import java.util.List;
import java.util.Scanner;

public class Menu {
    /**
     * This function prints a menu list to the console and returns the input using the interactWithMenu function
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @return User input as integer
     */
    public static int generateMenu(List<String> menu, String question, int exitKey) {
        for (int i = 0; i < menu.size(); i++) {
            String string = (i + 1) +
                    ". " +
                    menu.get(i);

            System.out.println(string);
        }

        System.out.println("\nYou can leave using '".concat(Integer.toString(exitKey)).concat("'"));

        return interactWithMenu(menu, question, exitKey);
    }

    /**
     * This function prints a question, waits for a valid user input and return the input when it's valid
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @return  User input as integer
     */
    public static int interactWithMenu(List<String> menu, String question, int exitKey) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(question);
            String choice = scanner.nextLine();

            if (choice.equals(String.valueOf(exitKey))) {
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
