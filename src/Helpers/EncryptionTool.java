package Helpers;

import java.io.*;  
import java.security.*;  
import java.security.spec.*;  
import javax.crypto.*;  
import javax.crypto.spec.*;  
import java.util.Base64;

public class EncryptionTool {  
    private static final String FILENAME = "encrypted_message.txt";  

    public static void main(String[] args) {  
        try {  
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  

            // Ask the user to enter a message  
            System.out.print("Enter your message : ");  
            String message = reader.readLine();  

            // Asks the user for encryption type  
            System.out.println("Choose the encryption type :");  
            System.out.println("1. Vigenère");  
            System.out.println("2. RSA");  
            System.out.println("3. ROT"); 
            int choice = Integer.parseInt(reader.readLine());  

            // Encrypts the message according to user choice  
            String encryptedMessage = "";  
            switch (choice) {  
                case 1:  
                    String vigenereKey = getVigenereKey(reader);  
                    encryptedMessage = encryptVigenere(message, vigenereKey);  
                    break;  
                case 2:  
                    KeyPair keyPair = generateRSAKeyPair();  
                    encryptedMessage = encryptRSA(message, keyPair.getPublic());  
                    break;  
                case 3:   
                    int rotKey = getRotKey(reader);  
                    encryptedMessage = encryptROT(message, rotKey);  
                    break;
                default:  
                    System.out.println("Invalid choice.");  
                    return;  
            }  

            // Asks the user if they want to save the encrypted message  
            System.out.println("Do you want to save the encrypted message ? (Y/N)");  
            String saveChoice = reader.readLine();  
            if (saveChoice.equalsIgnoreCase("O")) {  
                writeToFile(encryptedMessage);  
                System.out.println("The message has been saved to file " + FILENAME);  
            }  

            // Asks the user if they want to decrypt the message  
            System.out.println("Do you want to decrypt the message ? (Y/N)");  
            String decryptChoice = reader.readLine();  
            if (decryptChoice.equalsIgnoreCase("O")) {  
                String decryptedMessage = "";  
                switch (choice) {  
                    case 1:  
                        String vigenereKey = getVigenereKey(reader);
                        decryptedMessage = decryptVigenere(encryptedMessage, vigenereKey);  
                        break;  
                    case 2:  
                        KeyPair keyPair = generateRSAKeyPair();
                        decryptedMessage = decryptRSA(encryptedMessage, keyPair.getPrivate());  
                        break;  
                    case 3: // ROT  
                        int rotKey = getRotKey(reader);
                        decryptedMessage = decryptROT(encryptedMessage, rotKey);  
                        break; 
                }  
                System.out.println("Decrypted message : " + decryptedMessage);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

    // Method to obtain the Vigenère key from the user  
    private static String getVigenereKey(BufferedReader reader) throws IOException {  
        System.out.print("Enter the Vigenère encryption key : ");  
        return reader.readLine();  
    }  

    //Vigenère encryption method  
    private static String encryptVigenere(String message, String key) {  

        StringBuilder ciphertext = new StringBuilder();  

    for (int i = 0, j = 0; i < message.length(); i++) {  
        char c = message.charAt(i);          
        if (Character.isLetter(c)) {  
            int base = Character.isUpperCase(c) ? 'A' : 'a';              
            int shift = Character.toUpperCase(key.charAt(j)) - 'A';              
            c = (char) (base + ((c - base + shift) % 26));              
            j = (j + 1) % key.length();  
        }  
        ciphertext.append(c);  
    }  
      return ciphertext.toString();
    }  

    // Vigenère decipherment method  
    private static String decryptVigenere(String encryptedMessage, String key) {  

        StringBuilder plaintext = new StringBuilder();  
    
    for (int i = 0, j = 0; i < encryptedMessage.length(); i++) {  
            char c = encryptedMessage.charAt(i);  
            if (Character.isLetter(c)) {  
            int base = Character.isUpperCase(c) ? 'A' : 'a';              
            int shift = Character.toUpperCase(key.charAt(j)) - 'A';  
            c = (char) (base + ((c - base - shift + 26) % 26));  
            j = (j + 1) % key.length();  
        }  
        plaintext.append(c);  
    }  
    
    return plaintext.toString();  
    }  

    // Method to generate an RSA key pair  
    private static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {  
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");  
        keyGen.initialize(2048);  
        return keyGen.generateKeyPair();  
    }  

    // RSA encryption method  
    private static String encryptRSA(String message, PublicKey publicKey) throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());  
        return Base64.getEncoder().encodeToString(encryptedBytes);  
    }  

    // RSA decryption method  
    private static String decryptRSA(String encryptedMessage, PrivateKey privateKey) throws Exception {  
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage);  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);  
        return new String(decryptedBytes);  
    }  

    // Method to get ROT key from user  
    private static int getRotKey(BufferedReader reader) throws IOException {  
      System.out.print("Entrez la clé de rotation (0-25) : ");  
      return Integer.parseInt(reader.readLine());  
    }

    // ROT encryption method  
    private static String encryptROT(String message, int key) {  
    StringBuilder result = new StringBuilder();  
    key = key % 26; // Ensures the key stays in the range 0 to 25  
    for (char m : message.toCharArray()) {  
        if (Character.isLetter(m)) {  
            char base = Character.isUpperCase(m) ? 'A' : 'a';  
            result.append((char) ((m + key - base) % 26 + base));  
        } else {  
            result.append(m);  
        }  
    }  
    return result.toString();  
  }  

    // ROT decryption method  
    private static String decryptROT(String encryptedMessage, int key) {  
    return encryptROT(encryptedMessage, 26 - key); // Uses encryption method to decrypt  
  }  

    // Method to write the message to a file  
    private static void writeToFile(String message) throws IOException {  
        try (FileWriter writer = new FileWriter(FILENAME)) {  
            writer.write(message);  
        }  
    }  
}
