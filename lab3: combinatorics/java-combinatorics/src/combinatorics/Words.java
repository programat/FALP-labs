package combinatorics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

// Words of length 5 with exactly 2 'a's, others non-repeating
class WordsWithTwoAs extends Word {

    public WordsWithTwoAs(char[] alphabet) {
        super(alphabet);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
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
}

// Words of length 6 with exactly 2 letters repeating twice, others non-repeating
class WordsWithTwoRepeatingLetters extends Word {

    public WordsWithTwoRepeatingLetters(char[] alphabet) {
        super(alphabet);
    }

    @Override
    public void generate(FileWriter writer) throws IOException {
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
