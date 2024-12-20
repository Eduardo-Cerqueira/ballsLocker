package TextFileHandler;

import Cipher.ROT;
import Hash.SHA256;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
                if(fileType == FileType.ENCRYPTED) {
                    String encrypt = ROT.encrypt("Hello World", 3, true);
                    writeNewLineToFile(new FileLine(FileType.ENCRYPTED, "Hello World", encrypt));
                }
                else if(fileType == FileType.HASHED) {
                    String encrypt = ROT.encrypt("Hello World", 3, true);
                    String hash = SHA256.hash(encrypt);
                    writeNewLineToFile(new FileLine(FileType.HASHED, encrypt, hash));
                }

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
    public List<FileLine> getLinesFromFile(FileType fileType) {
        Path filePath = directoryPath.resolve(fileType.getFileName());
        List<FileLine> fileLines = new ArrayList<FileLine>();
        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(filePath);
            // Convert list to array
            // new String[0] : to create an empty array of strings of lines length

            for(String line : lines) {
                String[] splittedLine = line.split(";");
                fileLines.add(new FileLine(FileType.ENCRYPTED, splittedLine[0], splittedLine[1]));
            }

            return fileLines;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        // Return empty array if error
        return fileLines;
    }

}
