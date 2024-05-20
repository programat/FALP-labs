import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CombinatorialObjects {

    public static void main(String[] args) {
        char[] alphabet = {'a', 'b', 'c', 'd', 'e'};
        int k = 3;

        try (FileWriter writer = new FileWriter("lab3: combinatorics/java-combinatorics/data/combinatorial_objects.txt")) {
            writer.write("Recursive arrangements without repetition:\n");
            generateArrangementsWithoutRepetition(alphabet, new char[k], 0, writer);

            writer.write("\nRecursive permutations:\n");
            generatePermutations(alphabet, new char[alphabet.length], new boolean[alphabet.length], 0, writer);

            writer.write("\nNon-recursive arrangements without repetition:\n");
            generateArrangementsWithoutRepetitionNonRecursive(alphabet, k, writer);

            writer.write("\nNon-recursive permutations:\n");
            generatePermutationsNonRecursive(alphabet, writer);

            writer.write("\nWords of length 5 with exactly 2 'a's, others non-repeating:\n");
            generateWordsWithTwoAs(alphabet, writer);

            writer.write("\nWords of length 6 with exactly 2 letters repeating twice, others non-repeating:\n");
            generateWordsWithTwoRepeatingLetters(alphabet, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Recursive function for arrangements without repetition
    public static void generateArrangementsWithoutRepetition(char[] alphabet, char[] result, int depth, FileWriter writer) throws IOException {
        if (depth == result.length) {
            writer.write(Arrays.toString(result) + "\n");
            return;
        }

        for (char c : alphabet) {
            boolean used = false;
            for (int j = 0; j < depth; j++) {
                if (result[j] == c) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                result[depth] = c;
                generateArrangementsWithoutRepetition(alphabet, result, depth + 1, writer);
            }
        }
    }

    // Recursive function for permutations
    public static void generatePermutations(char[] alphabet, char[] result, boolean[] used, int depth, FileWriter writer) throws IOException {
        if (depth == alphabet.length) {
            writer.write(Arrays.toString(result) + "\n");
            return;
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (!used[i]) {
                used[i] = true;
                result[depth] = alphabet[i];
                generatePermutations(alphabet, result, used, depth + 1, writer);
                used[i] = false;
            }
        }
    }

    // Non-recursive function for arrangements without repetition
    public static void generateArrangementsWithoutRepetitionNonRecursive(char[] alphabet, int k, FileWriter writer) throws IOException {
        int n = alphabet.length;
        int[] indices = new int[k];
        boolean[] used = new boolean[n];
        Arrays.fill(used, false);

        while (true) {
            char[] result = new char[k];
            for (int i = 0; i < k; i++) {
                result[i] = alphabet[indices[i]];
            }
            writer.write(Arrays.toString(result) + "\n");

            int pos = k - 1;
            while (pos >= 0 && (indices[pos] == n - 1 || used[indices[pos]])) {
                used[indices[pos]] = false;
                pos--;
            }
            if (pos < 0) {
                break;
            }
            used[indices[pos]] = false;
            indices[pos]++;
            used[indices[pos]] = true;
            for (int i = pos + 1; i < k; i++) {
                indices[i] = 0;
                used[indices[i]] = true;
            }
        }
    }

    // Non-recursive function for permutations
    public static void generatePermutationsNonRecursive(char[] alphabet, FileWriter writer) throws IOException {
        int n = alphabet.length;
        int[] indices = new int[n];
        boolean[] used = new boolean[n];
        Arrays.fill(used, false);

        while (true) {
            char[] result = new char[n];
            for (int i = 0; i < n; i++) {
                result[i] = alphabet[indices[i]];
            }
            writer.write(Arrays.toString(result) + "\n");

            int pos = n - 1;
            while (pos >= 0 && (indices[pos] == n - 1 || used[indices[pos]])) {
                used[indices[pos]] = false;
                pos--;
            }
            if (pos < 0) {
                break;
            }
            used[indices[pos]] = false;
            indices[pos]++;
            used[indices[pos]] = true;
            for (int i = pos + 1; i < n; i++) {
                indices[i] = 0;
                used[indices[i]] = true;
            }
        }
    }

    // Non-recursive function for words of length 5 with exactly 2 'a's, others non-repeating
    public static void generateWordsWithTwoAs(char[] alphabet, FileWriter writer) throws IOException {
        char[] result = new char[5];
        int n = alphabet.length;

        for (int i = 0; i < n; i++) {
            if (alphabet[i] == 'a') continue;
            for (int j = 0; j < n; j++) {
                if (alphabet[j] == 'a' || i == j) continue;
                for (int k = 0; k < n; k++) {
                    if (alphabet[k] == 'a' || i == k || j == k) continue;
                    for (int l = 0; l < n; l++) {
                        if (alphabet[l] == 'a' || i == l || j == l || k == l) continue;
                        result[0] = 'a';
                        result[1] = 'a';
                        result[2] = alphabet[i];
                        result[3] = alphabet[j];
                        result[4] = alphabet[k];
                        writer.write(Arrays.toString(result) + "\n");
                    }
                }
            }
        }
    }

    // Non-recursive function for words of length 6 with exactly 2 letters repeating twice, others non-repeating
    public static void generateWordsWithTwoRepeatingLetters(char[] alphabet, FileWriter writer) throws IOException {
        char[] result = new char[6];
        int n = alphabet.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k) continue;
                        for (int m = 0; m < n; m++) {
                            if (m == i || m == j || m == k || m == l) continue;
                            result[0] = alphabet[i];
                            result[1] = alphabet[i];
                            result[2] = alphabet[j];
                            result[3] = alphabet[j];
                            result[4] = alphabet[k];
                            result[5] = alphabet[l];
                            writer.write(Arrays.toString(result) + "\n");
                        }
                    }
                }
            }
        }
    }
}
