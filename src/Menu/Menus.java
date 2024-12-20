package Menu;

import Cipher.*;
import Hash.MD5;
import Hash.SHA256;
import Helpers.CipherBuilder;
import Struct.Action;
import Struct.MenuItem;
import TextFileHandler.TextFileHandler;
import TextFileHandler.FileLine;
import TextFileHandler.FileType;

import java.util.Arrays;
import java.util.List;

import static Helpers.Validation.inputInteger;
import static Helpers.Validation.inputString;

public class Menus {
    public static final List<MenuItem> HomeMenu = Arrays.asList(
            new MenuItem("Chiffrer un mot de passe", "Vous pouvez chiffrer un mot de passe avec l'algorithme de votre choix, vous devrez le choisir parmi un liste d'algorithmes et ce mot de passe sera stocké de manière sécurisée dans votre ordinateur", new Action() {
                public void executeAction() {
                }
            }),
            new MenuItem("Déchiffrer un mot de passe", "Vous pouvez déchiffrer un mot de passe que vous avez chiffré précedement, vous devrez le choisir parmi un liste d'algorithmes et ce mot de passe sera stocké de manière sécurisée dans votre ordinateur", new Action() {
                public void executeAction() {
                }
            })
    );

    public static final List<MenuItem> EncryptMenu = Arrays.asList(
            new MenuItem("ROT", "Chiffrer le mot de passe avec ROT, vous devrez saisir le nombre de rotation entre votre charactere et le charactere chiffré", new Action() {
                public void executeAction() {
                    displayROTEncryptionMenu();
                }
            }),
            new MenuItem("Polybe", "Chiffrer le mot de passe avec Polybe", new Action() {
                public void executeAction() {
                    displayPolybeEncryptionMenu();
                }
            }),
            new MenuItem("Vigenere", "Chiffrer le mot de passe avec Vigenere, vous devrez saisir une clé et le message à chiffrer", new Action() {
                public void executeAction() {
                    displayVigenereEncryptionMenu();
                }
            }),
            new MenuItem("RC4", "Chiffrer le mot de passe avec RC4, vous devrez saisir une clé et le message à chiffrer", new Action() {
                public void executeAction() {
                    displayRC4EncryptionMenu();
                }
            })
    );

    public static final List<MenuItem> DecryptMenu = Arrays.asList(
            new MenuItem("ROT", "Decrypter le mot de passe avec ROT, vous devrez saisir le nombre de rotation entre votre charactere chiffré et le charactere", new Action() {
                public void executeAction() {
                    displayROTEncryptionMenu();
                }
            }),
            new MenuItem("Polybe", "Decrypter le mot de passe avec Polybe", new Action() {
                public void executeAction() {
                    displayPolybeEncryptionMenu();
                }
            }),
            new MenuItem("Vigenere", "Decrypter le mot de passe avec Vigenere, vous devrez saisir une clé pour déchiffrer", new Action() {
                public void executeAction() {
                    displayVigenereEncryptionMenu();
                }
            })
    );

    /*
    public static final List<MenuItem> CompleteMenu = Arrays.asList(
            new MenuItem("Chiffrer un message avec ROT", "Vous pouvez chiffrer un message avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre et la lettre chiffrée", new Action() { public void executeAction() { displayROTEncryptionMenu(); }}),
            new MenuItem("Déchiffrer un message avec ROT", "Vous pouvez déchiffrer un message avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre chiffrée et la lettre", new Action() { public void executeAction() { displayROTDecryptionMenu(); }}),
            new MenuItem("Hasher un message avec MD5", "Vous pouvez hasher un message avec l'algorithme de hashing MD5", new Action() { public void executeAction() { displayMD5HashMenu(); }}),
            new MenuItem("Hasher un message avec SHA-256", "Vous pouvez hssssssssssssssssssssssssssssssssssssssssssasher un message avec l'algorithme de hashing SHA-256", new Action() { public void executeAction() { displaySHA256HashMenu(); }}),
            new MenuItem("Comparer un message avec un hash MD5", "Vous pouvez comparer un hash issu de l'algorithme MD5 avec un message", new Action() { public void executeAction() { displayMD5CompareHashMenu(); }}),
            new MenuItem("Comparer un message avec un hash SHA-256", "Vous pouvez comparer un hash issu de l'algorithme SHA-256 avec un message", new Action() { public void executeAction() { displaySHA256CompareHashMenu(); }}),
            new MenuItem("Chiffrer un message avec Polybe", "Vous pouvez chiffrer un message avec l'algorithme Polybe", new Action() { public void executeAction() { displayPolybeEncryptionMenu(); }}),
            new MenuItem("Déchiffrer un message avec Polybe", "Vous pouvez déchiffrer un message avec l'algorithme Polybe", new Action() { public void executeAction() { displayPolybeDecryptionMenu(); }}),
            new MenuItem("Chaine de encryptage", "Vous pouvez chiffrer un message avec plusieurs algorithmes, vous allez être demandé quels algorithmes vous voulez utiliser étape par étape", new Action() { public void executeAction() { displayCipherBuilderEncryptionMenu(); }}),
            new MenuItem("Chaine de decryptage", "Vous pouvez déchiffrer un message avec plusieurs algorithmes, vous allez être demandé quels algorithmes vous voulez utiliser étape par étape", new Action() { public void executeAction() { displayCipherBuilderDecryptionMenu(); }}),
            new MenuItem("Générer un nombre aléatoire", "Vous pouvez generer un nombre aléatoire à partir d'une chaine de characteres", new Action() { public void executeAction() { displayGenerateRandomNumber(); }}),
            new MenuItem("Chiffrer un message avec Vigenere", "Vous pouvez chiffrer un message avec l'algorithme de substitution Vigenere, vous devrez saisir une clé et le message à chiffrer", new Action() { public void executeAction() { displayVigenereEncryptionMenu(); }}),
            new MenuItem("Déchiffrer un message avec Vigenere", "Vous pouvez déchiffrer un message avec l'algorithme de substitution Vigenere, vous devrez saisir une clé et le message à déchiffrer", new Action() { public void executeAction() { displayVigenereDecryptionMenu(); }})
    );
    */

    public static final List<MenuItem> EncryptionChainMenu = Arrays.asList(
            new MenuItem("Ajouter du Rot encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre et la lettre chiffrée", new Action() {
                public void executeAction() {
                }
            }),
            new MenuItem("Ajouter du Polybe encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de Polybe", new Action() {
                public void executeAction() {
                }
            })
    );

    public static final List<MenuItem> DecryptionChainMenu = Arrays.asList(
            new MenuItem("Ajouter du Rot decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre chiffrée et la lettre non chiffrée", new Action() {
                public void executeAction() {
                }
            }),
            new MenuItem("Ajouter du Polybe decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de Polybe", new Action() {
                public void executeAction() {
                }
            })
    );

    public static void displayROTEncryptionMenu() {
        String word = inputString("Word to encrypt:", "Word is invalid !");
        int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");

        String encryptedWord = ROT.encrypt(word, parsedInt, false);
        System.out.println("Encrypted word is: ".concat(encryptedWord));
        saveEncryptedPassword(encryptedWord, CipherAlgorithm.ROT.getAlgorithmName());
    }

    public static void displayPolybeEncryptionMenu() {
        Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);

        String word = inputString("Word to encrypt:", "Word is invalid !");
        String encrypted = polybe.encrypt(word);
        System.out.println(encrypted);
        saveEncryptedPassword(encrypted, CipherAlgorithm.POLYBE.getAlgorithmName());
    }

    public static void displayVigenereEncryptionMenu() {
        String word = inputString("Word to encrypt:", "Word is invalid !");
        String key = inputString("Key:", "Key is invalid !");
        String encrypted = Vigenere.encrypt(word, key);
        System.out.println(encrypted);
        saveEncryptedPassword(encrypted, CipherAlgorithm.VIGENERE.getAlgorithmName());
    }

    public static void displayRC4EncryptionMenu() {
        String word = inputString("Word to encrypt:", "Word is invalid !");
        String key = inputString("Key:", "Key is invalid !");
        String encrypted = Arrays.toString(RC4.encrypt(word.getBytes(), key.getBytes()));
        System.out.println(encrypted);
        saveEncryptedPassword(encrypted, CipherAlgorithm.RC4.getAlgorithmName());
    }

    public static void displayROTDecryptionMenu() {
        String word = inputString("Word to decrypt:", "Word is invalid !");
        int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");

        String encryptedWord = ROT.decrypt(word, parsedInt);
        System.out.println("Decrypted word is: ".concat(encryptedWord));
    }

    public static void displayMD5HashMenu() {
        String word = inputString("Word to hash:", "Word is invalid !");
        System.out.println(MD5.hash(word));
    }

    public static void displaySHA256HashMenu() {
        String word = inputString("Word to hash:", "Word is invalid !");
        System.out.println(SHA256.hash(word));
    }

    public static void displayMD5CompareHashMenu() {
        String word = inputString("Word to compare with hash:", "Word is invalid !");
        String hash = inputString("Hash:", "Hash is invalid !");
        System.out.println(MD5.compare(word, hash));
    }

    public static void displaySHA256CompareHashMenu() {
        String word = inputString("Word to compare with hash:", "Word is invalid !");
        String hash = inputString("Hash:", "Hash is invalid !");
        System.out.println(SHA256.compare(word, hash));
    }


    public static void displayPolybeDecryptionMenu() {
        Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);

        String word = inputString("Word to decrypt:", "Word is invalid !");
        System.out.println(polybe.decrypt(word));
    }

    public static void displayCipherBuilderEncryptionMenu() {
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

        String encrypted = cipherBuilder.getEncryptedMessage();
        System.out.println(encrypted);
        saveEncryptedPassword(encrypted, cipherBuilder.getUsedAlgorithms());
    }

    public static void displayCipherBuilderDecryptionMenu() {
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
    }

    public static void displayGenerateRandomNumber() {
        String seed = inputString("Seed:", "Seed is invalid !");
        int range = inputInteger("Range:", "Range is invalid !");
        System.out.println(new Cipher.LFSR(seed).generate(range));
    }

    public static void displayVigenereDecryptionMenu() {
        String word = inputString("Word to decrypt:", "Word is invalid !");
        String key = inputString("Key:", "Key is invalid !");
        System.out.println(Vigenere.decrypt(word, key));
    }

    public static void cipherBuilderAddEncryptROT(CipherBuilder cipherBuilder) {
        int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");
        cipherBuilder.encryptROT(parsedInt, true);
        System.out.println(cipherBuilder.getEncryptedMessage());
    }

    public static void cipherBuilderAddEncryptPolybe(CipherBuilder cipherBuilder) {
        cipherBuilder.encryptPolybe();
        System.out.println(cipherBuilder.getEncryptedMessage());
    }

    public static void cipherBuilderAddDecryptROT(CipherBuilder cipherBuilder) {
        int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");
        cipherBuilder.decryptROT(parsedInt);
        System.out.println(cipherBuilder.getEncryptedMessage());
    }

    public static void cipherBuilderAddDecryptPolybe(CipherBuilder cipherBuilder) {
        cipherBuilder.decryptPolybe();
        System.out.println(cipherBuilder.getEncryptedMessage());
    }

    public static void saveEncryptedPassword(String encrypted, String algorithm) {
        TextFileHandler textFileHandler = new TextFileHandler();
        FileLine line = new FileLine(FileType.ENCRYPTED, encrypted, algorithm);
        textFileHandler.writeNewLineToFile(line);

        String hash = SHA256.hash(encrypted);

        line = new FileLine(FileType.HASHED, hash, "SHA256");
        textFileHandler.writeNewLineToFile(line);
    }

    /**
     * Display the decryption menu, display the list of local password and the user can choose one to decrypt
     */
    public static void displayToDecryptPasswordMenu() {
        TextFileHandler textFileHandler = new TextFileHandler();
        List<FileLine> lines = textFileHandler.getLinesFromFile(FileType.ENCRYPTED);

        // Display passwords
        for (int i = 0; i < lines.size(); i++) {
            String string = (i + 1) +
                    ". " +
                    lines.get(i).getContent();

            System.out.println(string);
        }

            int choice = inputInteger("Which password do you want to decrypt ?", "Input is invalid");
            for (int i = 0; i < lines.size(); i++) {
                if (choice == i + 1) {
                    FileLine password = lines.get(i);

                    String algorithm = password.getAlgorithm();

                    // check which algorith is choosen
                    if (algorithm.equals(CipherAlgorithm.ROT.getAlgorithmName())) {
                        int parsedInt = inputInteger("Number of rotate:", "Number is invalid !");
                        String decryptedPassword = ROT.decrypt(password.getContent(), parsedInt);
                        System.out.print(decryptedPassword.concat("\n"));
                    } else if (algorithm.equals(CipherAlgorithm.POLYBE.getAlgorithmName())) {
                        Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);
                        System.out.println(polybe.decrypt(password.getContent()));
                    } else if (algorithm.equals(CipherAlgorithm.VIGENERE.getAlgorithmName())) {
                        String key = inputString("Key:", "Key is invalid !");
                        System.out.println(Vigenere.decrypt(password.getContent(), key));
                    } else if (algorithm.equals(CipherAlgorithm.RC4.getAlgorithmName())) {
                        String key = inputString("Key:", "Key is invalid !");
                        System.out.println(Arrays.toString(RC4.encrypt(password.getContent().getBytes(), key.getBytes())));
                    } else if (algorithm.equals(CipherAlgorithm.ENIGMA.getAlgorithmName())) {
                        System.out.println("Algorithm not implemented yet");
                    } else if (algorithm.equals(CipherAlgorithm.CHAIN.getAlgorithmName())) {
                        System.out.println("Algorithm not implemented yet");
                    }
                }
            }
        //System.out.println(Arrays.toString(lines));
    }
}
