import Menu.DynamicMenu;
import Menu.Menus;
import Struct.MenuItem;

import java.util.List;

import static Menu.DynamicMenu.displaySubMenu;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menu = Menus.HomeMenu;
        int exitKey = menu.size() + 1;
        
        // Display main menu
        int menuEntry = DynamicMenu.generateMenu(menu, "\nWhere do you want to go ?", exitKey, 0);
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        // Display sub menus
        while (true) {
            switch (menuEntry) {
                case 1:
                    displaySubMenu(Menus.EncryptMenu, Menus.HomeMenu);
                    break;
                case 2:
                    Menus.displayToDecryptPasswordMenu();
                    break;
                case 3:
                    Menus.displayGenerateRandomNumber();
                    break;
            }

            if (menuEntry == exitKey) {
                System.exit(0);
            }

            // Reset to main menu
            menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        }
    }
}