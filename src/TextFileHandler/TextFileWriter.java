package TextFileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextFileWriter {
    private Path directoryPath = Paths.get("files");

    public TextFileWriter() {
        setupFiles();
    }

    /**
     * Setup files and directory
     */
    private void setupFiles() {
        try {
            // Create directory if it doesn't exist
            Files.createDirectories(directoryPath);
        } catch (Exception e) {
            System.out.println("Error creating directory: " + e.getMessage());
        }
        // Create files if they don't exist
        createFile(FileType.ENCRYPTED);
        createFile(FileType.HASHED);
    }

    /**
     * Create file if it doesn't exist
     * @param fileType Enum of file types
     */
    private void createFile(FileType fileType){
        Path filePath = directoryPath.resolve(fileType.getFileName());
        if(!Files.exists(filePath)){
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private void writeNewLineToFile(FileType fileType, String content) {
        Path filePath = directoryPath.resolve(fileType.getFileName());
        try {
            Files.writeString(filePath, content + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TextFileWriter textFileWriter = new TextFileWriter();
        textFileWriter.writeNewLineToFile(FileType.ENCRYPTED, "Hello World");

    }

}
