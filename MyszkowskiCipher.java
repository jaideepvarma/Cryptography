import java.util.*;

public class MyszkowskiCipher {

    public static List<Integer> getKeyOrder(String key) {
        List<Pair> keyChars = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            keyChars.add(new Pair(Character.toUpperCase(key.charAt(i)), i));
        }

        Collections.sort(keyChars, Comparator.comparing(Pair::getChar));

        List<Integer> keyOrder = new ArrayList<>(Collections.nCopies(key.length(), 0));
        int order = 1;
        Map<Character, Integer> seen = new HashMap<>();
        for (Pair pair : keyChars) {
            char ch = pair.getChar();
            int idx = pair.getIdx();
            if (!seen.containsKey(ch))
                seen.put(ch, order++);
            keyOrder.set(idx, seen.get(ch));
        }

        return keyOrder;
    }

    public static String encryptMyszkowski(String plaintext, String key) {
        StringBuilder text = new StringBuilder(plaintext);
        text = new StringBuilder(text.toString().replace(" ", ""));

        int cols = key.length();
        List<Integer> keyOrder = getKeyOrder(key);
        int rows = (text.length() + cols - 1) / cols;

        char[][] grid = new char[rows][cols];
        int idx = 0;

        for (int i = 0; i < rows && idx < text.length(); i++) {
            for (int j = 0; j < cols && idx < text.length(); j++) {
                grid[i][j] = text.charAt(idx++);
            }
        }

        System.out.println("\nEncryption Grid:");
        for (int i = 0; i < key.length(); i++) System.out.print(key.charAt(i) + " ");
        System.out.println();
        for (int i : keyOrder) System.out.print(i + " ");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder cipher = new StringBuilder();
        for (int order = 1; order <= Collections.max(keyOrder); order++) {
            List<Integer> columns = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                if (keyOrder.get(j) == order)
                    columns.add(j);
            }
            for (int i = 0; i < rows; i++) {
                for (int col : columns) {
                    if (grid[i][col] != ' ')
                        cipher.append(grid[i][col]);
                }
            }
        }

        return cipher.toString();
    }

    public static String decryptMyszkowski(String cipher, String key) {
        int cols = key.length();
        List<Integer> keyOrder = getKeyOrder(key);
        int rows = (cipher.length() + cols - 1) / cols;

        char[][] grid = new char[rows][cols];
        int idx = 0;

        for (int order = 1; order <= Collections.max(keyOrder); order++) {
            List<Integer> columns = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                if (keyOrder.get(j) == order)
                    columns.add(j);
            }
            for (int i = 0; i < rows && idx < cipher.length(); i++) {
                for (int col : columns) {
                    if (idx < cipher.length())
                        grid[i][col] = cipher.charAt(idx++);
                }
            }
        }

        System.out.println("\nDecryption Grid:");
        for (int i = 0; i < key.length(); i++) System.out.print(key.charAt(i) + " ");
        System.out.println();
        for (int i : keyOrder) System.out.print(i + " ");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != ' ')
                    plain.append(grid[i][j]);
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Myszkowski Transposition Cipher");
        System.out.print("1. Encrypt\n2. Decrypt\nChoose (1/2): ");
        int choice = sc.nextInt();
        sc.nextLine();  // Consume the newline

        System.out.print("Enter the key: ");
        String key = sc.nextLine();

        System.out.print((choice == 1 ? "Enter plaintext: " : "Enter ciphertext: "));
        String text = sc.nextLine();

        if (choice == 1) {
            String encrypted = encryptMyszkowski(text, key);
            System.out.println("\nEncrypted Text: " + encrypted);
        } else {
            String decrypted = decryptMyszkowski(text, key);
            System.out.println("\nDecrypted Text: " + decrypted);
        }
    }

    // Helper class to store a pair of character and its index
    static class Pair {
        char character;
        int index;

        Pair(char character, int index) {
            this.character = character;
            this.index = index;
        }

        public char getChar() {
            return character;
        }

        public int getIdx() {
            return index;
        }
    }
}
