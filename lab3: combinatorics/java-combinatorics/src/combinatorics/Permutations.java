package combinatorics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

// Recursive permutation
class RecursivePermutation extends Arrangement {

    public RecursivePermutation(char[] alphabet) {
        super(alphabet, alphabet.length);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
        generatePermutations(new char[alphabet.length], new boolean[alphabet.length], 0, writer);
    }

    private void generatePermutations(char[] result, boolean[] used, int depth, FileWriter writer) throws IOException {
        if (depth == alphabet.length) {
            writer.write(Arrays.toString(result) + "\n");
            return;
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (!used[i]) {
                used[i] = true;
                result[depth] = alphabet[i];
                generatePermutations(result, used, depth + 1, writer);
                used[i] = false;
            }
        }
    }
}

// Non-recursive permutation
class NonRecursivePermutation extends Arrangement {

    public NonRecursivePermutation(char[] alphabet) {
        super(alphabet, alphabet.length);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
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
}
