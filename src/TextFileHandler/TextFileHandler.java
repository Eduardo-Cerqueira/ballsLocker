package TextFileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class TextFileHandler {
    private Path directoryPath = Paths.get("files");

    public TextFileHandler() {
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

    /**
     * Write a new line to a file
     * @param fileLine File line containing line information
     */
    public void writeNewLineToFile(FileLine fileLine) {
        Path filePath = directoryPath.resolve(fileLine.getFileType().getFileName());
        try {
            Files.writeString(filePath, fileLine + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Get all lines from a file
     * @param fileType Enum of file types
     * @return Array of lines from file
     */
    public String[] getLinesFromFile(FileType fileType) {
        Path filePath = directoryPath.resolve(fileType.getFileName());
        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(filePath);
            // Convert list to array
            // new String[0] : to create an empty array of strings of lines length
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        // Return empty array if error
        return new String[0];
    }

    public static void main(String[] args) {
        TextFileHandler textFileHandler = new TextFileHandler();
        FileLine line = new FileLine(FileType.ENCRYPTED, "Hello World", "ROT");
        textFileHandler.writeNewLineToFile(line);
        String[] lines = textFileHandler.getLinesFromFile(FileType.ENCRYPTED);
        System.out.println(Arrays.toString(lines));
    }

}
