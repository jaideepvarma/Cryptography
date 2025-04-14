import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text;
        char choice;
        int key;

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt? ");
        choice = scanner.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E for Encrypt or D for Decrypt.");
            scanner.close();
            return;
        }

        System.out.print("Enter the message: ");
        text = scanner.next();

        System.out.print("Enter the key: ");
        key = scanner.nextInt();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                if (Character.isLowerCase(ch)) {
                    ch = (char) ((choice == 'E')
                            ? (ch - 'a' + key) % 26 + 'a'
                            : (ch - 'a' - key + 26) % 26 + 'a');
                } else if (Character.isUpperCase(ch)) {
                    ch = (char) ((choice == 'E')
                            ? (ch - 'A' + key) % 26 + 'A'
                            : (ch - 'A' - key + 26) % 26 + 'A');
                } else if (Character.isDigit(ch)) {
                    ch = (char) ((choice == 'E')
                            ? (ch - '0' + key) % 10 + '0'
                            : (ch - '0' - key + 10) % 10 + '0');
                }
                result.append(ch);
            } else {
                System.out.println("Invalid character in message. Only letters and digits are allowed.");
                scanner.close();
                return;
            }
        }

        if (choice == 'E')
            System.out.println("Encrypted message: " + result);
        else
            System.out.println("Decrypted message: " + result);

        scanner.close();
    }
}
