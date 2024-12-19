import Menu.DynamicMenu;
import Menu.Menus;

public class Main {
    public static void main(String[] args) {
        int exitKey = Menus.HomeMenu.size() + 1;
        int menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        while (true) {
            switch (menuEntry) {
                case 1:
                    Menus.displayROTEncryptionMenu();
                    break;
                case 2:
                    Menus.displayROTDecryptionMenu();
                    break;
                case 3:
                    Menus.displayMD5HashMenu();
                    break;
                case 4:
                    Menus.displaySHA256HashMenu();
                    break;
                case 5:
                    Menus.displayMD5CompareHashMenu();
                    break;
                case 6:
                    Menus.displaySHA256CompareHashMenu();
                    break;
                case 7:
                    Menus.displayPolybeEncryptionMenu();
                    break;
                case 8:
                    Menus.displayPolybeDecryptionMenu();
                    break;
                case 9:
                    Menus.displayCipherBuilderEncryptionMenu();
                    break;
                case 10:
                    Menus.displayCipherBuilderDecryptionMenu();
                    break;
                case 11:
                    Menus.displayGenerateRandomNumber();
                    break;
                case 12:
                    Menus.displayVigenereEncryptionMenu();
                    break;
                case 13:
                    Menus.displayVigenereDecryptionMenu();
                    break;
                default:
                    if (menuEntry == exitKey) {
                        System.exit(0);
                    }
                    break;
            }

            menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        }
    }
}