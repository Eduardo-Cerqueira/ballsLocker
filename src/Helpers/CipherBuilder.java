package Helpers;

import Cipher.Polybe;
import Cipher.ROT;

public class CipherBuilder {
    static ROT rot = new ROT();
    static Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);

    private String encryptedMessage;

    public CipherBuilder(String message) {
        this.encryptedMessage = message;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void encryptROT(int numberOfRotate, boolean removeSpaces) {
        this.encryptedMessage = rot.encrypt(this.encryptedMessage, numberOfRotate, removeSpaces);
    }

    public void decryptROT(int numberOfRotate) {
        this.encryptedMessage = rot.decrypt(this.encryptedMessage, numberOfRotate);
    }

    public void encryptPolybe() {
        this.encryptedMessage = polybe.encrypt(this.encryptedMessage);
    }

    public void decryptPolybe() {
        this.encryptedMessage = polybe.decrypt(this.encryptedMessage);
    }
}