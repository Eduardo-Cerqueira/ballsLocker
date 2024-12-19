package TextFileHandler;

public enum FileType {
    ENCRYPTED("encrypted.txt"),
    HASHED("hashed.txt");

    private final String fileName;

    FileType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}