package CipherAlgorithm;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";


    /**
     * Generate a secret key for the AES algorithm
     * @param keySize The size of the key
     * @return The generated secret key
     */
    public SecretKey generateSecretKey(int keySize) {
        SecretKey key = null;
        try {
            // Returns a KeyGenerator object that generates secret keys for the specified algorithm.
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // Initialize the key generator with the key size
            keyGenerator.init(keySize);
            // Generate the key
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException ignored) {}
        return key;
    }

    /**
     * Generates a random 16-byte Initialization Vector (IV) using a secure random number generator.
     * This IV is used for AES encryption to ensure each encryption operation is unique.
     * @return A byte array containing the 16-byte randomly generated IV.
     */
    public byte[] generateIV() {
        byte[] iv = new byte[16];
        // Generate a random 16-byte Initialization Vector (IV)
        new java.security.SecureRandom().nextBytes(iv);
        return iv;
    }

    /**
     * Encrypts a plaintext message using the AES algorithm
     * The plaintext message is encrypted using the provided secret key and IV.
     * @param plainText The plaintext message to encrypt
     * @param secretKey The secret key used for encryption
     * @return The Base64-encoded ciphertext containing the IV and the encrypted message
     */
    public String encrypt(String plainText, SecretKey secretKey) {
        try {
            // Generate a random 16-byte Initialization Vector (IV)
            byte[] iv = generateIV();
            // Returns a Cipher object that implements the specified transformation.
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Initializes this cipher with a key and a set of algorithm parameters.
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            // Encrypt the plaintext message
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            // Combine the IV and encrypted data into a single array
            byte[] ivAndEncrypted = new byte[iv.length + encryptedBytes.length];
            // Copy the IV into the new array
            System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
            // Copy the encrypted data into the array after the IV
            System.arraycopy(encryptedBytes, 0, ivAndEncrypted, iv.length, encryptedBytes.length);

            // Encode the combined array into a Base64 string and return it
            return Base64.getEncoder().encodeToString(ivAndEncrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException
        ignored) {}
        return "";
    }

    /**
     * Decrypts a ciphertext message using the AES algorithm
     * The ciphertext message is decrypted using the provided secret key and IV.
     * @param encryptedText The Base64-encoded ciphertext containing the IV and the encrypted message
     * @param secretKey The secret key used for decryption
     * @return The decrypted plaintext message
     */
    public String decrypt(String encryptedText, SecretKey secretKey) {
        try {
            // Decode the Base64-encoded ciphertext to get the IV and encrypted bytes
            byte[] ivAndEncrypted = Base64.getDecoder().decode(encryptedText);

            // Extract the IV from the first 16 bytes
            byte[] iv = new byte[16];
            System.arraycopy(ivAndEncrypted, 0, iv, 0, 16);

            // Extract the encrypted message after the IV
            byte[] encryptedBytes = new byte[ivAndEncrypted.length - 16];
            System.arraycopy(ivAndEncrypted, 16, encryptedBytes, 0, encryptedBytes.length);

            // Set up the Cipher for decryption
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Decrypt the message and return the result as a string
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException ignored) {
        }
        return ""; // Return an empty string in case of error
    }

    /**
     * Loads a secret key from a Base64-encoded string.
     * @param key The Base64-encoded string representing the secret key.
     * @return The SecretKey object.
     */
    public SecretKey loadKeyFromString(String key) {
        // Decode the Base64-encoded string into a byte array
        byte[] decodedKey = Base64.getDecoder().decode(key);

        // Create and return a SecretKeySpec object using the decoded key and the AES algorithm
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }


}
