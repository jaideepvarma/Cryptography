import java.util.Scanner;

public class Vigenere {

    // Function to repeat or truncate the keyword to match the message length
    public static String generateKey(String text, String keyword) {
        StringBuilder key = new StringBuilder();
        int keyLen = keyword.length();

        for (int i = 0, j = 0; i < text.length(); ++i) {
            if (Character.isAlphabetic(text.charAt(i))) {
                key.append(keyword.charAt(j % keyLen));
                j++;
            } else {
                key.append(text.charAt(i)); // to preserve non-alphabet characters if needed
            }
        }
        return key.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt using Vigenère Cipher? ");
        char choice = sc.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E or D.");
            return;
        }

        // Input the message and keyword
        sc.nextLine(); // consume newline left by next()
        System.out.print("Enter the message: ");
        String text = sc.nextLine();

        System.out.print("Enter the keyword: ");
        String keyword = sc.nextLine();

        // Generate the key
        String key = generateKey(text, keyword);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);

            if (Character.isAlphabetic(ch)) {
                boolean isLower = Character.isLowerCase(ch);
                char base = isLower ? 'a' : 'A';

                char keyCh = Character.toLowerCase(key.charAt(i));
                int shift = keyCh - 'a';

                if (choice == 'E') {
                    ch = (char) ((ch - base + shift) % 26 + base);
                } else {
                    ch = (char) ((ch - base - shift + 26) % 26 + base);
                }

                result.append(ch);
            } else {
                System.out.println("Invalid character in message. Only letters allowed.");
                return;
            }
        }

        // Display the result
        if (choice == 'E') {
            System.out.println("Encrypted message: " + result.toString());
        } else {
            System.out.println("Decrypted message: " + result.toString());
        }

        sc.close();
    }
}
