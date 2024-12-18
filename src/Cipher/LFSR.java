package Cipher;

import Helpers.Convert;

import java.util.stream.IntStream;

public class LFSR {
    private String seed;

    // Constructor
    public LFSR(String seed) {
        this.seed = Convert.convertStringToBinary(seed);
    }

    // Getters and Setters
    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    /**
     * Generate a random number between 0 and range
     * @param range max number
     * @return random number
     */
    public int generate(int range) {
        // Convert seed to bytes
        byte[] bytes = seed.getBytes();

        // Convert bytes to Byte objects
        Byte[] bytesObject = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bytesObject[i] = bytes[i];
        }

        // Convert Byte objects to int values
        int[] bytesIntValues = new int[bytesObject.length];
        for (int i = 0; i < bytesObject.length; i++) {
            bytesIntValues[i] = bytesObject[i].intValue();
        }
        // Return sum of bytes modulo range
        return IntStream.of(bytesIntValues).sum() % range;
    }

    public static void main(String[] args) {
        LFSR lfsr = new LFSR("graine");
        System.out.println(lfsr.generate(100));
    }
}
