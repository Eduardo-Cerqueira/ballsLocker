import Menu.DynamicMenu;
import Menu.Menus;
import Struct.Action;

public class Main {
    public static void main(String[] args) {
        int exitKey = Menus.HomeMenu.size() + 1;
        int menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        while (true) {
            for (int i = 0; i < Menus.HomeMenu.size(); i++) {
                if (menuEntry == i + 1) {
                    Action action = Menus.HomeMenu.get(i).executeAction();
                    action.executeAction();

                } else if (menuEntry == exitKey) {
                    System.exit(0);
                }
            }

            menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        }
    }
}