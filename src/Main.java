import Cipher.Polybe;
import Cipher.ROT;
import Hash.MD5;
import Hash.SHA256;

import java.util.Arrays;
import java.util.List;

import static Helpers.Validation.inputInteger;
import static Helpers.Validation.inputString;

public class Main {
    static ROT rotCipher = new ROT();
    static MD5 md5 = new MD5();
    static SHA256 sha256 = new SHA256();
    static Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);

    public static void main(String[] args) {
        List<String> homeMenu = Arrays.asList(
                "Chiffrer un message avec ROT",
                "Déchiffrer un message avec ROT\n",
                "Hasher un message avec MD5",
                "Hasher un message avec SHA-256\n",
                "Comparer un message avec un hash MD5",
                "Comparer un message avec un hash SHA-256\n",
                "Chiffrer un message avec Polybe",
                "Déchiffrer un message avec Polybe"
        );

        int menuEntry = Menu.generateMenu(homeMenu, "\nWhere do you want to go ?");
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

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
        }
    }
}