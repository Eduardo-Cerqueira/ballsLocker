package Hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    /**
     * This function gives the hash of text using MD5 algorithm
     * @param stringToHash Text to hash
     * @return Hash for text
     */
    public static String hash(String stringToHash) {
        try {
            MessageDigest MD5Instance = MessageDigest.getInstance("MD5");

            byte[] messageDigest = MD5Instance.digest(stringToHash.getBytes(StandardCharsets.UTF_8));

            BigInteger rawMD5Hash = new BigInteger(1, messageDigest);

            return rawMD5Hash.toString(16);
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param stringToCompareWithHash Text to compare with hash
     * @param hash Hash MD5 to match with text
     * @return Boolean attesting the text integrity
     */
    public static boolean compare(String stringToCompareWithHash, String hash) {
        return hash(stringToCompareWithHash).equals(hash);
    }
}
