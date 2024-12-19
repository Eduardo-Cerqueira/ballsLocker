import Cipher.Polybe;
import Cipher.ROT;
import Cipher.Vigenere;
import Hash.MD5;
import Hash.SHA256;
import Helpers.CipherBuilder;
import Menu.DynamicMenu;
import Menu.Menus;

import static Helpers.Validation.inputInteger;
import static Helpers.Validation.inputString;

public class Main {
    static ROT rotCipher = new ROT();
    static MD5 md5 = new MD5();
    static SHA256 sha256 = new SHA256();
    static Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);
    static Vigenere vigenere = new Vigenere();

    public static void main(String[] args) {
        int exitKey = Menus.HomeMenu.size() + 1;
        int menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        while (true) {
            if (menuEntry == 1) {
                String word = inputString("Word to encrypt:", "Word is invalid !");
                int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");

                String encryptedWord = rotCipher.encrypt(word, parsedInt, false);
                System.out.println("Encrypted word is: ".concat(encryptedWord));

            } else if (menuEntry == 2) {
                String word = inputString("Word to decrypt:", "Word is invalid !");
                int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");

                String encryptedWord = rotCipher.decrypt(word, parsedInt);
                System.out.println("Decrypted word is: ".concat(encryptedWord));

            } else if (menuEntry == 3) {
                String word = inputString("Word to hash:", "Word is invalid !");
                System.out.println(md5.hash(word));

            } else if (menuEntry == 4) {
                String word = inputString("Word to hash:", "Word is invalid !");
                System.out.println(sha256.hash(word));

            } else if (menuEntry == 5) {
                String word = inputString("Word to compare with hash:", "Word is invalid !");
                String hash = inputString("Hash:", "Hash is invalid !");
                System.out.println(md5.compare(word, hash));

            } else if (menuEntry == 6) {
                String word = inputString("Word to compare with hash:", "Word is invalid !");
                String hash = inputString("Hash:", "Hash is invalid !");
                System.out.println(sha256.compare(word, hash));
            } else if (menuEntry == 7) { //TODO : choose methode
                String word = inputString("Word to encrypt:", "Word is invalid !");
                System.out.println(polybe.encrypt(word));
            } else if (menuEntry == 8) {
                String word = inputString("Word to decrypt:", "Word is invalid !");
                System.out.println(polybe.decrypt(word));
            } else if (menuEntry == 9) {
                String word = inputString("Word to encrypt:", "Word is invalid !");
                CipherBuilder cipherBuilder = new CipherBuilder(word);

                boolean addAlgorithm = true;
                while (addAlgorithm) {

                    int algorithmsMenuEntry = DynamicMenu.generateMenu(Menus.EncryptionChainMenu, "\nWhere do you want to go ?", Menus.EncryptionChainMenu.size() + 1, 0);

                    switch (algorithmsMenuEntry) {
                        case 1:
                            int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");
                            cipherBuilder.encryptROT(parsedInt, true);
                            System.out.println(cipherBuilder.getEncryptedMessage());
                            break;
                        case 2:
                            cipherBuilder.encryptPolybe();
                            System.out.println(cipherBuilder.getEncryptedMessage());
                            break;
                        default:
                            addAlgorithm = false;
                            break;
                    }
                }

                System.out.println(cipherBuilder.getEncryptedMessage());

            } else if (menuEntry == 10) {
                String word = inputString("Word to decrypt:", "Word is invalid !");
                CipherBuilder cipherBuilder = new CipherBuilder(word);

                boolean addAlgorithm = true;
                while (addAlgorithm) {

                    int algorithmsMenuEntry = DynamicMenu.generateMenu(Menus.DecryptionChainMenu, "\nWhere do you want to go ?", Menus.DecryptionChainMenu.size() + 1, 0);

                    switch (algorithmsMenuEntry) {
                        case 1:
                            int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");
                            cipherBuilder.decryptROT(parsedInt);
                            System.out.println(cipherBuilder.getEncryptedMessage());
                            break;
                        case 2:
                            cipherBuilder.decryptPolybe();
                            System.out.println(cipherBuilder.getEncryptedMessage());
                            break;
                        default:
                            addAlgorithm = false;
                            break;
                    }
                }

                System.out.println(cipherBuilder.getEncryptedMessage());
            } else if (menuEntry == 11) {
                String seed = inputString("Seed:", "Seed is invalid !");
                int range = inputInteger("Range:", "Range is invalid !");
                System.out.println(new Cipher.LFSR(seed).generate(range));

            } else if(menuEntry == 12) {
                String word = inputString("Word to encrypt:", "Word is invalid !");
                String key = inputString("Key:", "Key is invalid !");
                System.out.println(vigenere.encrypt(word, key));
            } else if(menuEntry == 13) {
                String word = inputString("Word to decrypt:", "Word is invalid !");
                String key = inputString("Key:", "Key is invalid !");
                System.out.println(vigenere.decrypt(word, key));
            } else if (menuEntry == exitKey) {
                System.exit(0);
            }

            menuEntry = DynamicMenu.generateMenu(Menus.HomeMenu, "\nWhere do you want to go ?", exitKey, 0);
        }
    }
}