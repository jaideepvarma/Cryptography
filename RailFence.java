import java.util.Scanner;

public class RailFence {

    // Method to encrypt the message using Rail Fence Cipher
    public static String encryptMessage(String plaintext, int rails) {
        char[][] rail = new char[rails][plaintext.length()];

        // Initialize rail array with newline characters
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        boolean moveDown = false;
        int row = 0, col = 0;
        // Build the rail fence cipher grid
        for (int i = 0; i < plaintext.length(); i++) {
            if (row == 0 || row == rails - 1) {
                moveDown = !moveDown;
            }
            rail[row][col++] = plaintext.charAt(i);
            row = moveDown ? row + 1 : row - 1;
        }

        // Read the cipher text from the rail grid
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                if (rail[i][j] != '\n') {
                    ciphertext.append(rail[i][j]);
                }
            }
        }
        return ciphertext.toString();
    }

    // Method to decrypt the message using Rail Fence Cipher
    public static String decryptMessage(String ciphertext, int rails) {
        char[][] rail = new char[rails][ciphertext.length()];

        // Initialize rail array with newline characters
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        boolean moveDown = true;
        int row = 0, col = 0;

        // Mark the positions with '*' (path taken by the encryption process)
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0) {
                moveDown = true;
            }
            if (row == rails - 1) {
                moveDown = false;
            }
            rail[row][col++] = '*';
            row = moveDown ? row + 1 : row - 1;
        }

        int index = 0;
        // Fill the rail with characters from ciphertext
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                if (rail[i][j] == '*' && index < ciphertext.length()) {
                    rail[i][j] = ciphertext.charAt(index++);
                }
            }
        }

        // Read the plaintext from the rail grid
        StringBuilder plaintext = new StringBuilder();
        row = 0;
        col = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0) {
                moveDown = true;
            }
            if (row == rails - 1) {
                moveDown = false;
            }

            if (rail[row][col] != '*') {
                plaintext.append(rail[row][col++]);
            }
            row = moveDown ? row + 1 : row - 1;
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter E for encryption or D for decryption: ");
        char choice = sc.next().charAt(0);
        System.out.print("Enter the number of rails: ");
        int rails = sc.nextInt();

        sc.nextLine();  // Consume newline character

        if (choice == 'E' || choice == 'e') {
            System.out.print("Enter plaintext (no spaces allowed): ");
            String input = sc.nextLine();
            String encryptedMessage = encryptMessage(input, rails);
            System.out.println("Encrypted Message: " + encryptedMessage);
        } else if (choice == 'D' || choice == 'd') {
            System.out.print("Enter ciphertext (no spaces allowed): ");
            String input = sc.nextLine();
            String decryptedMessage = decryptMessage(input, rails);
            System.out.println("Decrypted Message: " + decryptedMessage);
        } else {
            System.out.println("Invalid choice. Please enter E for encryption or D for decryption.");
        }

        sc.close();
    }
}
