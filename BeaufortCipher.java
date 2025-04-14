import java.util.Scanner;

public class BeaufortCipher {

    public static String generateKey(String text, String keyword) {
        StringBuilder key = new StringBuilder();
        int keyLen = keyword.length();
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                key.append(keyword.charAt(j % keyLen));
                j++;
            } else {
                key.append(ch); // keep non-letters as is
            }
        }

        return key.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text, keyword, result = "";
        char choice;

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt using Beaufort Cipher? ");
        choice = scanner.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E or D.");
            scanner.close();
            return;
        }

        System.out.print("Enter the message: ");
        text = scanner.next();

        System.out.print("Enter the keyword: ");
        keyword = scanner.next();

        String key = generateKey(text, keyword);

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                boolean isLower = Character.isLowerCase(ch);
                char base = isLower ? 'a' : 'A';

                char keyCh = Character.toLowerCase(key.charAt(i));
                int keyVal = keyCh - 'a';
                int plainVal = Character.toLowerCase(ch) - 'a';
                int cipherVal = (keyVal - plainVal + 26) % 26;
                char cipherChar = (char) (isLower ? ('a' + cipherVal) : ('A' + cipherVal));

                result += cipherChar;
            } else {
                System.out.println("Invalid character in message. Only letters allowed.");
                scanner.close();
                return;
            }
        }

        System.out.println((choice == 'E' ? "Encrypted" : "Decrypted") + " message: " + result);
        scanner.close();
    }
}
