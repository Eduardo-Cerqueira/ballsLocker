import CipherAlgorithm.AES;
import CipherAlgorithm.Polybe;
import CipherAlgorithm.ROT;
import Hash.MD5;
import Hash.SHA256;
import Helpers.CipherBuilder;
import Menu.DynamicMenu;
import Menu.MenuBuilder;
import Struct.MenuItem;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static Helpers.Validation.inputInteger;
import static Helpers.Validation.inputString;

public class Main {
    static ROT rotCipher = new ROT();
    static MD5 md5 = new MD5();
    static SHA256 sha256 = new SHA256();
    static Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);
    static AES aes = new AES();

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
                new MenuItem("Chiffrer un message avec AES", "Vous pouvez chiffrer un message avec l'algorithme moderne AES"),
                new MenuItem("Dechiffrer un message avec AES", "Vous pouvez déchiffrer un message avec l'algorithme moderne AES")
        );

        MenuBuilder homeMenuBuilder = new MenuBuilder.Builder().menu(homeMenu).onHelpMode(false).helpKey(0).exitKey(homeMenu.size() + 1).build();
        MenuBuilder menuBuilder = homeMenuBuilder;

        int menuEntry = DynamicMenu.generateMenu(menuBuilder);
        boolean firstRun = true;

        while (true) {
            if ((menuEntry == menuBuilder.getQuitKey()) && !firstRun) {
                menuEntry = DynamicMenu.generateMenu(homeMenuBuilder);
            } else if (menuEntry == menuBuilder.getHelpKey()) {
                menuEntry = DynamicMenu.generateMenu(menuBuilder);
            } else {
                firstRun = false;
            }

            System.out.println("Menu entry " + menuEntry + " has been choosen !");

            if (menuEntry == menuBuilder.getHelpKey()) {
                menuBuilder = new MenuBuilder.Builder().menu(menuBuilder.getMenu()).onHelpMode(true).exitKey(menuBuilder.getHelpKey()).build();
                DynamicMenu.generateMenu(menuBuilder);
                menuBuilder = new MenuBuilder.Builder().menu(menuBuilder.getMenu()).onHelpMode(false).helpKey(menuBuilder.getHelpKey()).exitKey(menuBuilder.getMenu().size() + 1).build();
            } else if (menuEntry == 1) {
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

                List<MenuItem> algorithmsMenu = Arrays.asList(
                        new MenuItem("Ajouter du Rot encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre et la lettre chiffrée"),
                        new MenuItem("Ajouter du Polybe encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de Polybe")
                );

            while (addAlgorithm) {
                menuBuilder = new MenuBuilder.Builder().menu(algorithmsMenu).onHelpMode(false).helpKey(0).exitKey(homeMenu.size() + 1).build();
                int algorithmsMenuEntry = DynamicMenu.generateMenu(menuBuilder);

                if (algorithmsMenuEntry == menuBuilder.getHelpKey()) {
                    menuBuilder = new MenuBuilder.Builder().menu(menuBuilder.getMenu()).onHelpMode(true).exitKey(menuBuilder.getHelpKey()).build();
                    DynamicMenu.generateMenu(menuBuilder);
                    menuBuilder = new MenuBuilder.Builder().menu(menuBuilder.getMenu()).onHelpMode(false).helpKey(menuBuilder.getHelpKey()).exitKey(menuBuilder.getMenu().size() + 1).build();
                } else {
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
                            menuEntry = algorithmsMenuEntry;
                            addAlgorithm = false;
                            break;
                    }
                }
            }

            System.out.println(cipherBuilder.getEncryptedMessage());

        } else if (menuEntry == 10) {
                String word = inputString("Word to decrypt:", "Word is invalid !");
                CipherBuilder cipherBuilder = new CipherBuilder(word);

                boolean addAlgorithm = true;

                List<MenuItem> algorithmsMenu = Arrays.asList(
                        new MenuItem("Ajouter du Rot decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre chiffrée et la lettre non chiffrée"),
                        new MenuItem("Ajouter du Polybe decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de Polybe")
                );

                while (addAlgorithm) {
                    menuBuilder = new MenuBuilder.Builder().menu(algorithmsMenu).onHelpMode(false).helpKey(0).exitKey(homeMenu.size() + 1).build();
                    int algorithmsMenuEntry = DynamicMenu.generateMenu(menuBuilder);

                    if (algorithmsMenuEntry == menuBuilder.getHelpKey()) {
                        menuBuilder = new MenuBuilder.Builder().menu(menuBuilder.getMenu()).onHelpMode(true).exitKey(menuBuilder.getHelpKey()).build();
                        DynamicMenu.generateMenu(menuBuilder);
                        menuBuilder = new MenuBuilder.Builder().menu(menuBuilder.getMenu()).onHelpMode(false).helpKey(menuBuilder.getHelpKey()).exitKey(menuBuilder.getMenu().size() + 1).build();
                    } else {
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
                                menuEntry = algorithmsMenuEntry;
                                addAlgorithm = false;
                                break;
                        }
                    }

                }
                
                System.out.println(cipherBuilder.getEncryptedMessage());

            } else if (menuEntry == 11) {
                String message = inputString("Message to encrypt:", "Message is invalid !");

                SecretKey secretKey = aes.generateSecretKey(128);
                String encrypted = aes.encrypt(message, secretKey);

                System.out.println("Message chiffré : " + encrypted);
                System.out.println("Clé secrète : " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));

            } else if (menuEntry == 12) {
                String message = inputString("Message to decrypt:", "Message is invalid !");
                String key = inputString("Private Key/Password:", "Private Key/Password is invalid !");

                SecretKey secretKey = aes.loadKeyFromString(key);
                String decryptedText = aes.decrypt(message, secretKey);

                System.out.println("Message déchiffré : " + decryptedText);
            } else if (menuEntry == menuBuilder.getQuitKey()) {
                System.exit(0);
            }
        }
    }
}