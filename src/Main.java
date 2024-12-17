import Cipher.ROT;
import Hash.MD5;
import Hash.SHA256;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Menu menu = new Menu();
    static ROT rotCipher = new ROT();
    static MD5 md5 = new MD5();
    static SHA256 sha256 = new SHA256();

    public static void main(String[] args) {
        List<String> homeMenu = Arrays.asList(
                "Crypter un message avec ROT",
                "Decrypter un message avec ROT\n",
                "Hasher un message avec MD5",
                "Hasher un message avec SHA-256\n",
                "Comparer un message avec un hash MD5",
                "Comparer un message avec un hash SHA-256"
        );

        int menuEntry = menu.generateMenu(homeMenu, "\nWhere do you want to go ?");
        System.out.println("Menu entry " + menuEntry + " has been choosen !");

        if (menuEntry == 1) {
            Scanner scanner = new Scanner(System.in);

            boolean wordIsValid = false;
            String word = "";

            while (!wordIsValid) {
                System.out.println("Word to encrypt:");
                word = scanner.nextLine();

                if (!word.isBlank()) {
                    wordIsValid = true;
                } else {
                    System.out.println("Word is invalid !");
                }
            }

            boolean numberIsValid = false;

            while (!numberIsValid) {
                try {
                    System.out.println("Number of rotate:");
                    String numberOfRotate = scanner.nextLine();

                    int parsedInt = Integer.parseInt(numberOfRotate);
                    numberIsValid = true;

                    String encryptedWord = rotCipher.encrypt(word, parsedInt, false);
                    System.out.println("Encrypted word is: ".concat(encryptedWord));
                } catch (NumberFormatException e) {
                    System.out.println("Number is invalid !"); // if number < -2147483648 (Integer.MIN_VALUE) or number > 2147483648 (Integer.MAX_VALUE)
                }
            }
        } else if (menuEntry == 2) {
            Scanner scanner = new Scanner(System.in);

            boolean wordIsValid = false;
            String word = "";

            while (!wordIsValid) {
                System.out.println("Word to decrypt:");
                word = scanner.nextLine();

                if (!word.isBlank()) {
                    wordIsValid = true;
                } else {
                    System.out.println("Word is invalid !");
                }
            }

            boolean numberIsValid = false;

            while (!numberIsValid) {
                try {
                    System.out.println("Number of rotate:");
                    String numberOfRotate = scanner.nextLine();

                    int parsedInt = Integer.parseInt(numberOfRotate);
                    numberIsValid = true;

                    String decryptedWord = rotCipher.decrypt(word, parsedInt);
                    System.out.println("Decrypted word is: ".concat(decryptedWord));
                } catch (NumberFormatException e) {
                    System.out.println("Number is invalid !"); // if number < -2147483648 (Integer.MIN_VALUE) or number > 2147483648 (Integer.MAX_VALUE)
                }
            }
        } else if (menuEntry == 3) {
            Scanner scanner = new Scanner(System.in);

            boolean wordIsValid = false;
            String word = "";

            while (!wordIsValid) {
                System.out.println("Word to hash:");
                word = scanner.nextLine();

                if (!word.isBlank()) {
                    wordIsValid = true;
                } else {
                    System.out.println("Word is invalid !");
                }
            }

            System.out.println(md5.hash(word));
        } else if (menuEntry == 4) {
            Scanner scanner = new Scanner(System.in);

            boolean wordIsValid = false;
            String word = "";

            while (!wordIsValid) {
                System.out.println("Word to hash:");
                word = scanner.nextLine();

                if (!word.isBlank()) {
                    wordIsValid = true;
                } else {
                    System.out.println("Word is invalid !");
                }
            }

            System.out.println(sha256.hash(word));
        } else if (menuEntry == 5) {
            Scanner scanner = new Scanner(System.in);

            boolean wordIsValid = false;
            String word = "";

            while (!wordIsValid) {
                System.out.println("Word to hash:");
                word = scanner.nextLine();

                if (!word.isBlank()) {
                    wordIsValid = true;
                } else {
                    System.out.println("Word is invalid !");
                }
            }

            boolean hashIsValid = false;
            String hash = "";

            while (!hashIsValid) {
                System.out.println("Hash:");
                hash = scanner.nextLine();

                if (!hash.isBlank()) {
                    hashIsValid = true;
                } else {
                    System.out.println("Hash is invalid !");
                }
            }

            System.out.println(md5.compare(word, hash));
        } else if (menuEntry == 6) {
            Scanner scanner = new Scanner(System.in);

            boolean wordIsValid = false;
            String word = "";

            while (!wordIsValid) {
                System.out.println("Word to hash:");
                word = scanner.nextLine();

                if (!word.isBlank()) {
                    wordIsValid = true;
                } else {
                    System.out.println("Word is invalid !");
                }
            }

            boolean hashIsValid = false;
            String hash = "";

            while (!hashIsValid) {
                System.out.println("Hash:");
                hash = scanner.nextLine();

                if (!hash.isBlank()) {
                    hashIsValid = true;
                } else {
                    System.out.println("Hash is invalid !");
                }
            }

            System.out.println(sha256.compare(word, hash));
        }
    }
}