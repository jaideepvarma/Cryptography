import java.util.Scanner;

public class augustcipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        char choice;
        int key = 1;  // Fixed shift of 1 for August Cipher

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt using August Cipher? ");
        choice = scanner.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E for Encrypt or D for Decrypt.");
            return;
        }

        System.out.print("Enter the message (letters only): ");
        text = scanner.next();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int shift = (choice == 'E') ? key : (26 - key);
                ch = (char) ((ch - base + shift) % 26 + base);
                result.append(ch);
            } else {
                System.out.println("Invalid character in message. Only letters are allowed.");
                return;
            }
        }

        if (choice == 'E') {
            System.out.println("Encrypted message using August Cipher: " + result);
        } else {
            System.out.println("Decrypted message using August Cipher: " + result);
        }

        scanner.close();
    }
}
