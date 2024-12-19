package Cipher;

public class Vigenere {

  /**
   * Encrypts a plaintext using the Vigenere cipher with the specified key.
   * @param plaintext The plaintext to encrypt.
   * @param key The key to use for encryption.
   * @return The encrypted ciphertext.
   */
  public static String encrypt(String plaintext, String key) {  
    StringBuilder ciphertext = new StringBuilder();  
    
    // Iterate through the plaintext  
    for (int i = 0, j = 0; i < plaintext.length(); i++) {  
        char c = plaintext.charAt(i);  
        
        // Check if the current character is a letter  
        if (Character.isLetter(c)) {  
            // Determine the base character ('A' or 'a'), Checks if the character is an uppercase or lowercase letter
            int base = Character.isUpperCase(c) ? 'A' : 'a';  
            
            // Get the shift value from the key  
            int shift = Character.toUpperCase(key.charAt(j)) - 'A';  
            
            // Apply the shift and wrap around the alphabet if necessary, Apply encryption formula
            c = (char) (base + ((c - base + shift) % 26));  
            
            // Move to the next character in the key  
            j = (j + 1) % key.length();  
        }  
        
        // Append the (possibly shifted) character to the ciphertext  
        ciphertext.append(c);  
    }  
    
    return ciphertext.toString();  
  }  

  /**
   * Decrypts a ciphertext using the Vigenere cipher with the specified key.
   * @param ciphertext The ciphertext to decrypt.
   * @param key The key to use for decryption.
   * @return The decrypted plaintext.
   */
  public static String decrypt(String ciphertext, String key) {  
    StringBuilder plaintext = new StringBuilder();  
    
    // Iterate through the ciphertext  
    for (int i = 0, j = 0; i < ciphertext.length(); i++) {  
        char c = ciphertext.charAt(i);  
        
        // Check if the current character is a letter  
        if (Character.isLetter(c)) {  
            // Determine the base character ('A' or 'a'), checks if the character is an uppercase or lowercase letter
            int base = Character.isUpperCase(c) ? 'A' : 'a';  
            
            // Get the shift value from the key  
            int shift = Character.toUpperCase(key.charAt(j)) - 'A';  
            
            // Apply the inverse shift and wrap around the alphabet if necessary, Apply decryption formula
            c = (char) (base + ((c - base - shift + 26) % 26));  
            
            // Move to the next character in the key  
            j = (j + 1) % key.length();  
        }  
        
        // Append the (possibly shifted) character to the plaintext  
        plaintext.append(c);  
    }  
    
    return plaintext.toString();  
  }  

  //Main method for testing encryption and decryption
  public static void main(String[] args) {  
    String originalMessage = "Hello World!! We'll TEST IF this is secure and IF IT WORk";  
    String key = "BBBB";
    
    String encryptedMessage = encrypt(originalMessage, key);  
    System.out.println("Encrypted message: " + encryptedMessage);  
    
    String decryptedMessage = decrypt(encryptedMessage, key);  
    System.out.println("Decrypted message: " + decryptedMessage);  
  }  
}

