package Menu;

import Struct.MenuItem;

import java.util.Arrays;
import java.util.List;

public class Menus {
    public static final List<MenuItem> HomeMenu = Arrays.asList(
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

    public static final List<MenuItem> EncryptionChainMenu = Arrays.asList(
            new MenuItem("Ajouter du Rot encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre et la lettre chiffrée"),
            new MenuItem("Ajouter du Polybe encrypt", "Ajouter un chiffrement sur le message actuel (encrypté ou non) avec l'algorithme de Polybe")
    );

    public static final List<MenuItem> DecryptionChainMenu = Arrays.asList(
            new MenuItem("Ajouter du Rot decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de substitution ROT, vous devrez saisir le nombre de rotation entre votre lettre chiffrée et la lettre non chiffrée"),
            new MenuItem("Ajouter du Polybe decrypt", "Déchiffrer le message actuel (encrypté ou non) avec l'algorithme de Polybe")
    );
}
