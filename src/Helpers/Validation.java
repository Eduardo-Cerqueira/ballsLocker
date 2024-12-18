package Helpers;

public class Validation {
    public boolean isLowerCase(String word) {
        return word.equals(word.toLowerCase());
    }

    /**
     *
     * @param stringToCompareWithHash Text to compare with hash
     * @param hash Hash to match with text
     * @return Boolean attesting the text integrity
     */
    public boolean compare(String stringToCompareWithHash, String hash) {
        return hash(stringToCompareWithHash).equals(hash);
    }
}
