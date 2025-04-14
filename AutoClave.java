import java.util.Scanner;

public class AutoClave {

    public static String sanitizeInput(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(Character.toUpperCase(c));
            }
        }
        return result.toString();
    }

    public static String encryptAutoclave(String plaintext, String key) {
        plaintext = sanitizeInput(plaintext);
        key = sanitizeInput(key);

        String extendedKey = key + plaintext;
        extendedKey = extendedKey.substring(0, plaintext.length());

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = extendedKey.charAt(i);
            char c = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
            ciphertext.append(c);
        }
        return ciphertext.toString();
    }

    public static String decryptAutoclave(String ciphertext, String key) {
        ciphertext = sanitizeInput(ciphertext);
        key = sanitizeInput(key);

        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char k;
            if (i < key.length()) {
                k = key.charAt(i);
            } else {
                k = plaintext.charAt(i - key.length());
            }
            char c = ciphertext.charAt(i);
            char p = (char) (((c - k + 26) % 26) + 'A');
            plaintext.append(p);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text, key;
        int choice;

        System.out.println("Autoclave Cipher");
        System.out.print("1. Encrypt\n2. Decrypt\nEnter choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter plaintext: ");
            text = scanner.nextLine();
            System.out.print("Enter key: ");
            key = scanner.nextLine();
            String encrypted = encryptAutoclave(text, key);
            System.out.println("Encrypted Text: " + encrypted);
        } else if (choice == 2) {
            System.out.print("Enter ciphertext: ");
            text = scanner.nextLine();
            System.out.print("Enter key: ");
            key = scanner.nextLine();
            String decrypted = decryptAutoclave(text, key);
            System.out.println("Decrypted Text: " + decrypted);
        } else {
            System.out.println("Invalid option!");
        }

        scanner.close();
    }
}
