import java.util.*;

public class NgramCounter {

    // Method to preprocess text by removing non-alphabet characters and converting to uppercase
    public static String preprocessText(String input) {
        StringBuilder cleaned = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                cleaned.append(Character.toUpperCase(ch));
            }
        }
        return cleaned.toString();
    }

    // Method to count N-grams in the text
    public static void countNgrams(String text, int n) {
        if (text.length() < n) {
            System.out.println("Text is too short for " + n + "-grams.");
            return;
        }

        Map<String, Integer> ngramFreq = new HashMap<>();

        for (int i = 0; i <= text.length() - n; ++i) {
            String ngram = text.substring(i, i + n);
            ngramFreq.put(ngram, ngramFreq.getOrDefault(ngram, 0) + 1);
        }

        System.out.println("\n--- " + n + "-gram Frequencies ---");
        for (Map.Entry<String, Integer> entry : ngramFreq.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input text from the user
        System.out.print("Enter the text: ");
        String input = sc.nextLine();

        String cleanedText = preprocessText(input);

        // Choose the type of N-gram
        System.out.println("\nChoose N-gram type:");
        System.out.println("1. Unigram");
        System.out.println("2. Bigram (Digram)");
        System.out.println("3. Trigram");
        System.out.println("4. Tetragram");
        System.out.print("Enter choice (1-4): ");
        int choice = sc.nextInt();

        // Process the N-gram based on user choice
        switch (choice) {
            case 1:
                countNgrams(cleanedText, 1);
                break;
            case 2:
                countNgrams(cleanedText, 2);
                break;
            case 3:
                countNgrams(cleanedText, 3);
                break;
            case 4:
                countNgrams(cleanedText, 4);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
