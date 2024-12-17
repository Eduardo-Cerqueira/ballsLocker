public class RC4 {
    public static byte[] encrypt(byte[] stringBytesToEncrypt, String encryptionKey) {
        //Prevent the user from entering a key too small or too big, maybe handle keys too big later
        if(encryptionKey.length() < 1 || encryptionKey.length() > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        } else {
            //We initialize all our variables.
            //This will hold values from 0 to 255.
            int[] S = new int[256]; //Ask Clement how to name this. baseArray ? modelArray ?
            //This will hold our key bytes repeated over and over until we hit 255.
            int[] extendedKeyBytes = new int[256];
            //This will be our encryptedBytes and will be what we return.
            byte[] encryptedBytes = new byte[stringBytesToEncrypt.length];
            //These will be used as indexes during our loops.
            int i, temp, swapIndex = 0;

            //We initialize S values from 0 to 255.
            //We initialize the extendedKeyBytes values by repeating the key bytes until we hit 255.
            for (i = 0; i < 256; i++) {
                S[i] = i;
                extendedKeyBytes[i] = encryptionKey.getBytes()[i % encryptionKey.length()];
            }

            //We scramble S by generating a swap index within a range of 255 based on our key bytes,
            //and swapping its elements that are at the loop index i and swapIndex.
            //Since it's based on our key bytes it will always give the same result if we use the same key;
            //We do this so we can use S later to encrypt our string.
            for (i = 0; i < 256; i++) {
                //Here we generate the swapIndex.
                swapIndex = (swapIndex + S[i] + extendedKeyBytes[i]) % 256;
                //This is the swap.
                temp = S[i];
                S[i] = S[swapIndex];
                S[swapIndex] = temp;
            }

            swapIndex = 0;
            for(i = 0; i < stringBytesToEncrypt.length; i += 1 % 256) {
                //Here we change the position of the swapIndex.
                swapIndex = (swapIndex + S[i]) % 256;
                //Here we swap S[i] and S[swapIndex] values.
                temp = S[i];
                S[i] = S[swapIndex];
                S[swapIndex] = temp;
                //Continue the RC4 algorithm ? Ask Clement if I need to understand/explain WHY we do this or just WHAT we are doing.
                int XORcomparisonIndex = (S[i] + S[swapIndex]) % 256;
                encryptedBytes[i] = (byte) (stringBytesToEncrypt[i] ^ S[XORcomparisonIndex]);
            }

            return encryptedBytes;
        }
    }

    public static byte[] decrypt(byte[] stringBytesToDecrypt, String decryptionKey) {
        //RC4 decryption process is the same as encryption process, so this is just for more intuitive use.
        return encrypt(stringBytesToDecrypt, decryptionKey);
    }
}
