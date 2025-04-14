import java.util.Scanner;

public class GronsfeldCipher {

    public static String generateNumericKey(String text, String key) {
        StringBuilder fullKey = new StringBuilder();
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                fullKey.append(key.charAt(j % key.length()));
                j++;
            } else {
                fullKey.append(text.charAt(i)); // retain same length for indexing
            }
        }
        return fullKey.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text, key, result = "";
        char choice;

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt using Gronsfeld Cipher? ");
        choice = scanner.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E or D.");
            scanner.close();
            return;
        }

        System.out.print("Enter the message: ");
        text = scanner.next();

        System.out.print("Enter the numeric key (digits only): ");
        key = scanner.next();

        // Validate key
        for (char c : key.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("Invalid key. Only digits (0–9) are allowed.");
                scanner.close();
                return;
            }
        }

        String fullKey = generateNumericKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                boolean isLower = Character.isLowerCase(ch);
                char base = isLower ? 'a' : 'A';

                int shift = fullKey.charAt(i) - '0';

                if (choice == 'E') {
                    ch = (char) ((ch - base + shift) % 26 + base);
                } else {
                    ch = (char) ((ch - base - shift + 26) % 26 + base);
                }

                result += ch;
            } else {
                System.out.println("Invalid character in message. Only letters are allowed.");
                scanner.close();
                return;
            }
        }

        if (choice == 'E') {
            System.out.println("Encrypted message: " + result);
        } else {
            System.out.println("Decrypted message: " + result);
        }

        scanner.close();
    }
}
