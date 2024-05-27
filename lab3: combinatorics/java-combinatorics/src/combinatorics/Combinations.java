package combinatorics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

// Recursive combination without repetition
class RecursiveCombinationWithoutRepetition extends Combination {

    public RecursiveCombinationWithoutRepetition(char[] alphabet, int length) {
        super(alphabet, length);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
        generateCombinationsWithoutRepetition(new char[length], 0, 0, writer);
    }

    private void generateCombinationsWithoutRepetition(char[] result, int start, int depth, FileWriter writer) throws IOException {
        if (depth == length) {
            writer.write(Arrays.toString(result) + "\n");
            return;
        }

        for (int i = start; i < alphabet.length; i++) {
            result[depth] = alphabet[i];
            generateCombinationsWithoutRepetition(result, i + 1, depth + 1, writer);
        }
    }
}

// Non-recursive combination without repetition
class NonRecursiveCombinationWithoutRepetition extends Combination {

    public NonRecursiveCombinationWithoutRepetition(char[] alphabet, int length) {
        super(alphabet, length);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
        int n = alphabet.length;
        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }

        while (true) {
            char[] result = new char[length];
            for (int i = 0; i < length; i++) {
                result[i] = alphabet[indices[i]];
            }
            writer.write(Arrays.toString(result) + "\n");

            int pos = length - 1;
            while (pos >= 0 && indices[pos] == n - length + pos) {
                pos--;
            }
            if (pos < 0) {
                break;
            }
            indices[pos]++;
            for (int i = pos + 1; i < length; i++) {
                indices[i] = indices[i - 1] + 1;
            }
        }
    }
}
