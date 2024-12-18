package Struct;

import java.security.SecureRandom;

public record AESEncryptedWithIV(String encryptedString, SecureRandom initializationVector) {
}