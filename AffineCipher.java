import java.util.Scanner;

public class AffineCipher {

    // Function to compute modular inverse using Extended Euclidean Algorithm
    static int modInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            q = a / m;
            t = m;

            m = a % m;
            a = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    // Function to check if two numbers are coprime
    static boolean isCoprime(int a, int m) {
        while (m != 0) {
            int temp = m;
            m = a % m;
            a = temp;
        }
        return a == 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to (E)ncrypt or (D)ecrypt using Affine Cipher? ");
        char choice = scanner.next().toUpperCase().charAt(0);

        if (choice != 'E' && choice != 'D') {
            System.out.println("Invalid choice. Please enter E for Encrypt or D for Decrypt.");
            return;
        }

        System.out.print("Enter the message: ");
        String text = scanner.next();

        System.out.print("Enter key 'a' (must be coprime with 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter key 'b': ");
        int b = scanner.nextInt();

        if (!isCoprime(a, 26)) {
            System.out.println("Invalid key 'a'. It must be coprime with 26.");
            return;
        }

        int a_inv = modInverse(a, 26); // Needed for decryption
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                boolean isLower = Character.isLowerCase(ch);
                char base = isLower ? 'a' : 'A';
                int x = ch - base;

                if (choice == 'E') {
                    x = (a * x + b) % 26;
                } else {
                    x = (a_inv * (x - b + 26)) % 26;
                }

                ch = (char) (base + x);
                result.append(ch);
            } else {
                System.out.println("Invalid character in message. Only letters are allowed.");
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
