public class RC4 {
    public static byte[] encrypt(byte[] stringBytesToEncrypt, byte[] encryptionKeyBytes) {
        //Prevent the user from entering a key too small. 
        //A key too big will only have it's first 255 bytes taken into account.
        if(encryptionKeyBytes.length < 1) {
            throw new IllegalArgumentException("key must be more than 1 byte.");
        } else {
            //We initialize all our variables.
            //This will hold values from 0 to 255.
            int[] S = new int[256]; //Ask Clement how to name this. baseArray ? modelArray ?
            //This will hold our key bytes repeated over and over until we hit 255.
            int[] extendedKeyBytes = new int[256];
            //This will be our encryptedBytes and will be what we return.
            byte[] encryptedBytes = new byte[stringBytesToEncrypt.length];
            //These will be used as indexes during our loops.
            int i, temp, XORcomparisonIndex, currByteIndex, swapIndex = 0;

            //We initialize S values from 0 to 255.
            //We initialize the extendedKeyBytes values by repeating the key bytes until we hit 255.
            for (i = 0; i < 256; i++) {
                S[i] = i;
                extendedKeyBytes[i] = encryptionKeyBytes[i % encryptionKeyBytes.length];
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

            //This is the actual encryption process of the RC4 algorithm
            swapIndex = 0;
            for(currByteIndex = 0; currByteIndex < stringBytesToEncrypt.length; currByteIndex++) {
                i = (i + 1) % 256;
                //Here we change the position of the swapIndex, we use it afterwards to swap values around in S and to generate the XORindex.
                swapIndex = (swapIndex + S[i]) % 256;
                //Here we swap S[i] and S[swapIndex] values.
                temp = S[i];
                S[i] = S[swapIndex];
                S[swapIndex] = temp;
                //Here we generate an index we will use afterwards during a XOR comparison.
                XORcomparisonIndex = (S[currByteIndex] + S[swapIndex]) % 256;
                //We compare the value from S[XORcomparisonIndex] to the current byte we are processing using a XOR operator.
                encryptedBytes[currByteIndex] = (byte) (stringBytesToEncrypt[currByteIndex] ^ S[XORcomparisonIndex]);
            }

            return encryptedBytes;
        }
    }

    public static byte[] decrypt(byte[] stringBytesToDecrypt, byte[] decryptionKeyBytes) {
        //RC4 decryption process is the same as encryption process, so this is just for more intuitive use.
        return encrypt(stringBytesToDecrypt, decryptionKeyBytes);
    }
}
