package combinatorics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

// Recursive arrangement without repetition
class RecursiveArrangementWithoutRepetition extends Arrangement {

    public RecursiveArrangementWithoutRepetition(char[] alphabet, int length) {
        super(alphabet, length);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
        generateArrangementsWithoutRepetition(new char[length], 0, writer);
    }

    private void generateArrangementsWithoutRepetition(char[] result, int depth, FileWriter writer) throws IOException {
        if (depth == length) {
            writer.write(Arrays.toString(result) + "\n");
            return;
        }

        for (int i = 0; i < alphabet.length; i++) {
            boolean used = false;
            for (int j = 0; j < depth; j++) {
                if (result[j] == alphabet[i]) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                result[depth] = alphabet[i];
                generateArrangementsWithoutRepetition(result, depth + 1, writer);
            }
        }
    }
}

// Non-recursive arrangement without repetition
class NonRecursiveArrangementWithoutRepetition extends Arrangement {

    public NonRecursiveArrangementWithoutRepetition(char[] alphabet, int length) {
        super(alphabet, length);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
        int n = alphabet.length;
        int[] indices = new int[length];
        boolean[] used = new boolean[n];
        Arrays.fill(used, false);

        while (true) {
            char[] result = new char[length];
            for (int i = 0; i < length; i++) {
                result[i] = alphabet[indices[i]];
            }
            writer.write(Arrays.toString(result) + "\n");

            int pos = length - 1;
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
            for (int i = pos + 1; i < length; i++) {
                indices[i] = 0;
                used[indices[i]] = true;
            }
        }
    }
}
