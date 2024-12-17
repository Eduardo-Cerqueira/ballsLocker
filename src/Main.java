import java.util.Arrays;
import java.util.List;

public class Main {
    static Menu menu = new Menu();

    public static void main(String[] args) {

        List<String> homeMenu = Arrays.asList("Crypter un message", "Decrypter un message");

        int menuEntry = menu.generateMenu(homeMenu, "\nWhere do you want to go ?");

        System.out.println("Menu entry " + menuEntry + " has been choosen !");
    }
}