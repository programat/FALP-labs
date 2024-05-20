import java.util.Arrays;

public class ArrangementsAndCombinations {

    public static void main(String[] args) {
        char[] alphabet = {'a', 'b', 'c'};
        int k = 2;

        System.out.println("Recursive arrangements with repetition:");
        generateArrangementsWithRepetition(alphabet, new char[k], 0, k);

        System.out.println("\nNon-recursive arrangements with repetition:");
        generateArrangementsWithRepetitionNonRecursive(alphabet, k);

        System.out.println("\nRecursive combinations without repetition:");
        generateCombinationsWithoutRepetition(alphabet, new char[k], 0, 0, k);

        System.out.println("\nNon-recursive combinations without repetition:");
        generateCombinationsWithoutRepetitionNonRecursive(alphabet, k);
    }

    // Recursive function for permutations with repetition
    public static void generateArrangementsWithRepetition(char[] alphabet, char[] result, int depth, int k) {
        if (depth == k) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (char c : alphabet) {
            result[depth] = c;
            generateArrangementsWithRepetition(alphabet, result, depth + 1, k);
        }
    }

    // Recursive function for combinations without repetition
    public static void generateCombinationsWithoutRepetition(char[] alphabet, char[] result, int start, int depth, int k) {
        if (depth == k) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = start; i < alphabet.length; i++) {
            result[depth] = alphabet[i];
            generateCombinationsWithoutRepetition(alphabet, result, i + 1, depth + 1, k);
        }
    }

    // Non-recursive function for permutations with repetition
    public static void generateArrangementsWithRepetitionNonRecursive(char[] alphabet, int k) {
        int n = alphabet.length;
        int[] indices = new int[k];
        while (true) {
            char[] result = new char[k];
            for (int i = 0; i < k; i++) {
                result[i] = alphabet[indices[i]];
            }
            System.out.println(Arrays.toString(result));

            int pos = k - 1;
            while (pos >= 0 && indices[pos] == n - 1) {
                pos--;
            }
            if (pos < 0) {
                break;
            }
            indices[pos]++;
            for (int i = pos + 1; i < k; i++) {
                indices[i] = 0;
            }
        }
    }

    // Non-recursive function for combinations without repetition
    public static void generateCombinationsWithoutRepetitionNonRecursive(char[] alphabet, int k) {
        int n = alphabet.length;
        int[] indices = new int[k];
        for (int i = 0; i < k; i++) {
            indices[i] = i;
        }

        while (true) {
            char[] result = new char[k];
            for (int i = 0; i < k; i++) {
                result[i] = alphabet[indices[i]];
            }
            System.out.println(Arrays.toString(result));

            int pos = k - 1;
            while (pos >= 0 && indices[pos] == n - k + pos) {
                pos--;
            }
            if (pos < 0) {
                break;
            }
            indices[pos]++;
            for (int i = pos + 1; i < k; i++) {
                indices[i] = indices[i - 1] + 1;
            }
        }
    }
}
