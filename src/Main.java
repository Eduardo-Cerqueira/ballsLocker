import Cipher.Polybe;
import Cipher.ROT;
import Cipher.Vigenere;
import Hash.MD5;
import Hash.SHA256;
import Helpers.CipherBuilder;
import Menu.DynamicMenu;
import Struct.MenuItem;

import java.util.Arrays;
import java.util.List;

import static Helpers.Validation.inputInteger;
import static Helpers.Validation.inputString;

public class Main {
    static ROT rotCipher = new ROT();
    static MD5 md5 = new MD5();
    static SHA256 sha256 = new SHA256();
    static Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);
    static Vigenere vigenere = new Vigenere();

    public static void main(String[] args) {
        List<MenuItem> homeMenu = Arrays.asList(
                new MenuItem("Chiffrer un message avec ROT", "Vous pouvez chiffrer un message avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre et la lettre chiffrée"),
                new MenuItem("Déchiffrer un message avec ROT", "Vous pouvez déchiffrer un message avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre chiffrée et la lettre"),
                new MenuItem("Hasher un message avec MD5", "Vous pouvez hasher un message avec l'algorithme de hashing MD5"),
                new MenuItem("Hasher un message avec SHA-256", "Vous pouvez hasher un message avec l'algorithme de hashing SHA-256"),
                new MenuItem("Comparer un message avec un hash MD5", "Vous pouvez comparer un hash issu de l'algorithme MD5 avec un message"),
                new MenuItem("Comparer un message avec un hash SHA-256", "Vous pouvez comparer un hash issu de l'algorithme SHA-256 avec un message"),
                new MenuItem("Chiffrer un message avec Polybe", "Vous pouvez chiffrer un message avec l'algorithme Polybe"),
                new MenuItem("Déchiffrer un message avec Polybe", "Vous pouvez déchiffrer un message avec l'algorithme Polybe"),
                new MenuItem("Chaine de encryptage", "Vous pouvez chiffrer un message avec plusieurs algorithmes, vous allez être demandé quels algorithmes vous voulez utiliser étape par étape"),
                new MenuItem("Chaine de decryptage", "Vous pouvez déchiffrer un message avec plusieurs algorithmes, vous allez être demandé quels algorithmes vous voulez utiliser étape par étape"),
                new MenuItem("Générer un nombre aléatoire", "Vous pouvez generer un nombre aléatoire à partir d'une chaine de characteres"),
                new MenuItem("Chiffrer un message avec Vigenere", "Vous pouvez chiffrer un message avec l'algorithme de substitution Vigenere, vous devrez saisir une clé et le message à chiffrer"),
                new MenuItem("Déchiffrer un message avec Vigenere", "Vous pouvez déchiffrer un message avec l'algorithme de substitution Vigenere, vous devrez saisir une clé et le message à déchiffrer")
        );


        int exitKey = homeMenu.size() + 1;
        int menuEntry = DynamicMenu.generateMenu(homeMenu, "\nWhere do you want to go ?", exitKey, 0);
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
                    List<MenuItem> algorithmsMenu = Arrays.asList(
                            new MenuItem("Ajouter du Rot encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre et la lettre chiffrée"),
                            new MenuItem("Ajouter du Polybe encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de Polybe")
                    );

                    int algorithmsMenuEntry = DynamicMenu.generateMenu(algorithmsMenu, "\nWhere do you want to go ?", algorithmsMenu.size() + 1, 0);

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
                    List<MenuItem> algorithmsMenu = Arrays.asList(
                            new MenuItem("Ajouter du Rot decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre chiffrée et la lettre non chiffrée"),
                            new MenuItem("Ajouter du Polybe decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de Polybe")
                    );

                    int algorithmsMenuEntry = DynamicMenu.generateMenu(algorithmsMenu, "\nWhere do you want to go ?", algorithmsMenu.size() + 1, 0);

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

            menuEntry = DynamicMenu.generateMenu(homeMenu, "\nWhere do you want to go ?", homeMenu.size() + 1, 0);
        }
    }
}