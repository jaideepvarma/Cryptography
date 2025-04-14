import java.util.Scanner;

public class RouteCipher {

    // Method to encrypt the message using Route Cipher
    public static String encryptRouteCipher(String plaintext, int rows, int cols) {
        // Remove spaces from plaintext
        plaintext = plaintext.replaceAll(" ", "");

        // Padding with 'X' if needed
        while (plaintext.length() < rows * cols) {
            plaintext += 'X';
        }

        // Create matrix for encryption
        char[][] matrix = new char[rows][cols];
        int index = 0;

        // Fill the matrix row by row
        for (int i = 0; i < rows && index < plaintext.length(); ++i) {
            for (int j = 0; j < cols && index < plaintext.length(); ++j) {
                matrix[i][j] = plaintext.charAt(index++);
            }
        }

        // Generate the ciphertext by reading the matrix column by column
        StringBuilder cipher = new StringBuilder();
        for (int j = 0; j < cols; ++j) {
            for (int i = 0; i < rows; ++i) {
                cipher.append(matrix[i][j]);
            }
        }

        return cipher.toString();
    }

    // Method to decrypt the message using Route Cipher
    public static String decryptRouteCipher(String ciphertext, int rows, int cols) {
        // Create matrix for decryption
        char[][] matrix = new char[rows][cols];
        int index = 0;

        // Fill the matrix column by column
        for (int j = 0; j < cols && index < ciphertext.length(); ++j) {
            for (int i = 0; i < rows && index < ciphertext.length(); ++i) {
                matrix[i][j] = ciphertext.charAt(index++);
            }
        }

        // Read the matrix row by row to generate the decrypted text
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                plaintext.append(matrix[i][j]);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user whether they want to encrypt or decrypt
        System.out.print("Enter E for encryption or D for decryption: ");
        String choice = sc.nextLine().trim();

        // Ask for number of rows and columns for the cipher matrix
        System.out.print("Enter the number of rows and columns (e.g., 4 5): ");
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine(); // Consume newline character

        if (choice.equalsIgnoreCase("E")) {
            // Encrypt the plaintext
            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();
            String encryptedMessage = encryptRouteCipher(plaintext, rows, cols);
            System.out.println("Encrypted message: " + encryptedMessage);
        } else if (choice.equalsIgnoreCase("D")) {
            // Decrypt the ciphertext
            System.out.print("Enter ciphertext: ");
            String ciphertext = sc.nextLine();
            String decryptedMessage = decryptRouteCipher(ciphertext, rows, cols);
            System.out.println("Decrypted message: " + decryptedMessage);
        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
