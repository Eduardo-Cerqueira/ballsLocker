import java.util.List;
import java.util.Scanner;

public class Menu {
    /**
     * This function prints a menu list to the console and returns the input using the interactWithMenu function
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @return User input as integer
     */
    public static int generateMenu(List<String> menu, String question) {
        for (int i = 0; i < menu.size(); i++) {
            String string = (i + 1) +
                    ". " +
                    menu.get(i);

            System.out.println(string);
        }

        return interactWithMenu(menu, question);
    }

    /**
     * This function prints a question, waits for a valid user input and return the input when it's valid
     * @param menu Each menu entry that can be chosen
     * @param question Question to ask when requesting user input
     * @return  User input as integer
     */
    public static int interactWithMenu(List<String> menu, String question) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(question);
            String choice = scanner.nextLine();

            for (int i = 0; i < menu.size(); i++) {
                if (choice.equals(String.valueOf(i + 1))) {
                    return Integer.parseInt(choice);
                }
            }
        }
    }
}
