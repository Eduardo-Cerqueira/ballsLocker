package Helpers;

import Cipher.Polybe;
import Cipher.ROT;

public class CipherBuilder {
    static ROT rot = new ROT();
    static Polybe polybe = new Polybe(Polybe.SquareMethode.HORIZONTAL);

    private String usedAlgorithms = "";
    private String encryptedMessage;

    public String getUsedAlgorithms() {
        return usedAlgorithms;
    }

    public CipherBuilder(String message) {
        this.encryptedMessage = message;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void encryptROT(int numberOfRotate, boolean removeSpaces) {
        if (usedAlgorithms.isBlank()) {
            usedAlgorithms = "ROT".concat(";").concat(String.valueOf(numberOfRotate));
        } else {
            usedAlgorithms = usedAlgorithms.concat("-ROT").concat(";").concat(String.valueOf(numberOfRotate));
        }
        this.encryptedMessage = rot.encrypt(this.encryptedMessage, numberOfRotate, removeSpaces);
    }

    public void decryptROT(int numberOfRotate) {
        this.encryptedMessage = rot.decrypt(this.encryptedMessage, numberOfRotate);
    }

    public void encryptPolybe() {
        if (usedAlgorithms.isBlank()) {
            usedAlgorithms = "Polybe";
        } else {
            usedAlgorithms = usedAlgorithms.concat("-Polybe");
        }
        this.encryptedMessage = polybe.encrypt(this.encryptedMessage);
    }

    public void decryptPolybe() {
        this.encryptedMessage = polybe.decrypt(this.encryptedMessage);
    }
}
