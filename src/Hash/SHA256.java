package Hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    /**
     * This function gives the hash of text using SHA-256 algorithm
     * @param stringToHash Text to hash
     * @return Hash for text
     */
    public static String hash(String stringToHash) {
        try {

            MessageDigest MD5Instance = MessageDigest.getInstance("SHA-256");

            byte[] messageDigest = MD5Instance.digest(stringToHash.getBytes(StandardCharsets.UTF_8));

            BigInteger rawSHA256Hash = new BigInteger(1, messageDigest);

            return rawSHA256Hash.toString(16);
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param stringToCompareWithHash Text to compare with hash
     * @param hash Hash SHA-256 to match with text
     * @return Boolean attesting the text integrity
     */
    public static boolean compare(String stringToCompareWithHash, String hash) {
        return hash(stringToCompareWithHash).equals(hash);
    }
}
