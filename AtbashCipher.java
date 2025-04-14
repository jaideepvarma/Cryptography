import java.util.Scanner;

public class AtbashCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        char choice;

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt using Atbash Cipher? ");
        choice = scanner.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E for Encrypt or D for Decrypt.");
            return;
        }

        System.out.print("Enter the message: ");
        text = scanner.next();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    ch = (char) ('z' - (ch - 'a'));
                } else {
                    ch = (char) ('Z' - (ch - 'A'));
                }
            } else if (Character.isDigit(ch)) {
                ch = (char) ('9' - (ch - '0'));
            } else {
                System.out.println("Invalid character in message. Only letters and digits are allowed.");
                return;
            }

            result.append(ch);
        }

        System.out.println("Resulting message: " + result.toString());
        scanner.close();
    }
}
