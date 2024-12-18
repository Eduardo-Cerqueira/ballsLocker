package Helpers;

public class Convert {
    /**
     * Convert string to binary
     * @param string string to be converted
     * @return string converted to binary
     */
    public static String convertStringToBinary(String string) {
        StringBuilder binary = new StringBuilder();

        for (char character : string.toCharArray()) {
            /* Integer.toBinaryString(character) -> convert string ascii value to binary
             * String.format("%8s", ...) -> specify that the size of converted binary must be 8 bits
             * .replace(' ', '0') -> replace spaces added by String.format by 0
             */
            String binaryChar = String.format("%8s", Integer.toBinaryString(character))
                    .replace(' ', '0');
            binary.append(binaryChar);
        }
        return binary.toString();
    }
}
