package CipherAlgorithm;

import Struct.AESEncryptedWithIV;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    public static SecretKey generateSecretKey(int n) {
        SecretKey key = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(n);
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException ignored) {}

        return key;
    }

    public static SecureRandom generateInitializationVector() {
        byte[] iv = new byte[16];
        return new SecureRandom(iv);
    }

    public String encrypt(String message, SecretKey privateKey) {
        String encryptedString = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // or AES/CBC/NoPadding
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] encryptedMessage = cipher.doFinal(message.getBytes());
            encryptedString = Base64.getEncoder().encodeToString(encryptedMessage);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ignored) {}
        return encryptedString;
    }

    public AESEncryptedWithIV encryptWithIV(String message, SecureRandom initializationVector, SecretKey privateKey) {
        AESEncryptedWithIV encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // or AES/CBC/NoPadding
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] encryptedMessage = cipher.doFinal(message.getBytes());
            encryptedString =  new AESEncryptedWithIV(Base64.getEncoder().encodeToString(encryptedMessage), initializationVector);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ignored) {}
        return encryptedString;
    }

    public String decrypt(String encryptedMessage, SecretKey privateKey) {
        String decryptedString = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // or AES/CBC/NoPadding

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            decryptedString = new String(plainText);


        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException ignored) {
        }
        return decryptedString;
    }

    public String decryptWithIV(String encryptedMessage, SecureRandom initializationVector, SecretKey privateKey) {
        String decryptedString = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // or AES/CBC/NoPadding
            cipher.init(Cipher.DECRYPT_MODE, privateKey, initializationVector);

            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            decryptedString = Arrays.toString(plainText);


        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException ignored) {
        }

        return decryptedString;
    }
}