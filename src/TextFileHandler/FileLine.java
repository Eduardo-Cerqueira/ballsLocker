package TextFileHandler;

public class FileLine {
    private FileType fileType;
    private String content;
    private String algorithm;

    // Constructor without otherInfo
    public FileLine(FileType fileType, String content, String algorithm) {
        this.fileType = fileType;
        this.content = content;
        this.algorithm = algorithm;
    }

    // Getters and Setters
    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlgorithm() {
        return algorithm;
    }


    @Override
    public String toString() {
        return content + ";" + algorithm;
    }
}
