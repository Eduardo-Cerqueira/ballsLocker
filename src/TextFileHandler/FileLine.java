package TextFileHandler;

public class FileLine {
    private FileType fileType;
    private String content;
    private String algorithm;
    private String[] otherInfo;

    // Constructor with otherInfo
    public FileLine(FileType fileType, String content, String algorithm, String[] otherInfo) {
        this.fileType = fileType;
        this.content = content;
        this.algorithm = algorithm;
        this.otherInfo = otherInfo;
    }

    // Constructor without otherInfo
    public FileLine(FileType fileType, String content, String algorithm) {
        this.fileType = fileType;
        this.content = content;
        this.algorithm = algorithm;
        this.otherInfo = null;
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

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }


    @Override
    public String toString() {
        if (otherInfo != null) {
            return content + ";" + algorithm + ";" + String.join(";", otherInfo);
        }
        return content + ";" + algorithm;
    }
}
