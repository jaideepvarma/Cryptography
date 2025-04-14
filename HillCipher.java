import java.util.Scanner;

public class HillCipher {

    static final int MOD = 26;

    static int[][] multiplyMatrix(int[][] key, int[] block) {
        int n = key.length;
        int[][] result = new int[n][1];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                result[i][0] += key[i][k] * block[k];
            }
            result[i][0] %= MOD;
        }
        return result;
    }

    static int determinant(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];

        int det = 0, sign = 1;
        for (int i = 0; i < n; i++) {
            int[][] minor = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0, col = 0; k < n; k++) {
                    if (k == i) continue;
                    minor[j - 1][col++] = matrix[j][k];
                }
            }
            det += sign * matrix[0][i] * determinant(minor);
            sign *= -1;
        }
        return (det % MOD + MOD) % MOD;
    }

    static int modInverse(int a) {
        a = a % MOD;
        for (int x = 1; x < MOD; x++) {
            if ((a * x) % MOD == 1) return x;
        }
        return -1;
    }

    static int[][] adjoint(int[][] matrix) {
        int n = matrix.length;
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] minor = new int[n - 1][n - 1];
                for (int k = 0, row = 0; k < n; k++) {
                    if (k == i) continue;
                    for (int l = 0, col = 0; l < n; l++) {
                        if (l == j) continue;
                        minor[row][col++] = matrix[k][l];
                    }
                    row++;
                }
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                int cofactor = sign * determinant(minor);
                adj[j][i] = (cofactor % MOD + MOD) % MOD;
            }
        }
        return adj;
    }

    static String encrypt(String plaintext, int[][] key) {
        StringBuilder modifiedText = new StringBuilder(plaintext);
        int n = key.length;
        int padding = n - (modifiedText.length() % n);
        if (padding != n) {
            for (int i = 0; i < padding; i++) {
                modifiedText.append('X');
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < modifiedText.length(); i += n) {
            int[] block = new int[n];
            for (int j = 0; j < n; j++) {
                block[j] = modifiedText.charAt(i + j) - 'A';
            }
            int[][] result = multiplyMatrix(key, block);
            for (int[] row : result) {
                ciphertext.append((char) (row[0] + 'A'));
            }
        }
        return ciphertext.toString();
    }

    static String decrypt(String ciphertext, int[][] key) {
        int det = determinant(key);
        int invDet = modInverse(det);
        if (invDet == -1) {
            return "Key matrix is not invertible. Decryption not possible.";
        }

        int[][] adj = adjoint(key);
        int n = key.length;
        int[][] inverseKey = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseKey[i][j] = (adj[i][j] * invDet) % MOD;
                if (inverseKey[i][j] < 0) inverseKey[i][j] += MOD;
            }
        }

        return encrypt(ciphertext, inverseKey);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] key = new int[3][3];
        String input;
        char choice;

        System.out.println("HILL CIPHER (3x3)");
        System.out.print("Enter E for encryption or D for decryption: ");
        choice = scanner.next().toUpperCase().charAt(0);

        System.out.println("Enter the 3x3 key matrix (row-wise, integers mod 26):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                key[i][j] = scanner.nextInt();
            }
        }

        if (choice == 'E') {
            System.out.print("Enter plaintext (UPPERCASE only): ");
            input = scanner.next();
            String cipher = encrypt(input, key);
            System.out.println("Encrypted Text: " + cipher);
        } else if (choice == 'D') {
            System.out.print("Enter ciphertext (UPPERCASE only): ");
            input = scanner.next();
            String plain = decrypt(input, key);
            System.out.println("Decrypted Text: " + plain);
        } else {
            System.out.println("Invalid choice. Use E or D.");
        }

        scanner.close();
    }
}
