package Cipher;

public enum CipherAlgorithm {
    ROT("ROT"),
    POLYBE("Polybe"),
    VIGENERE("Vigenere"),
    RC4("RC4"),
    AES("AES"),
    ENIGMA("Enigma");

    private final String algorithmName;

    CipherAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

}
