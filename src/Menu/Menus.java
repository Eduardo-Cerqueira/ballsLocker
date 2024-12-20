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
            }),
            new MenuItem("Générer un nombre aléatoire", "Vous pouvez generer un nombre aléatoire à partir d'une chaine de characteres", new Action() {
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
            /*new MenuItem("RC4", "Chiffrer le mot de passe avec RC4, vous devrez saisir une clé et le message à chiffrer", new Action() {
                public void executeAction() {
                    displayRC4EncryptionMenu();
                }
            }),*/
            new MenuItem("Chain", "Chiffrer un message avec plusieurs algorithmes, vous allez être demandé quels algorithmes vous voulez utiliser étape par étape", new Action() {
                public void executeAction() {
                    displayCipherBuilderEncryptionMenu();
                }
            })
    );

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

    public static void displayCipherBuilderDecryptionMenu(String word) {
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
        System.out.println("Random number is : ".concat(String.valueOf(new Cipher.LFSR(seed).generate(range))));
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
                    displayCipherBuilderDecryptionMenu(password.getContent());
                }
            }
        }
        //System.out.println(Arrays.toString(lines));
    }
}
