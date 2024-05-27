package combinatorics;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        char[] alphabet = {'a', 'b', 'c', 'd', 'e'};
        int k = 3;

        try (FileWriter writer = new FileWriter("lab3: combinatorics/java-combinatorics/data/combinatorial_objects.txt")) {
            CombinatorialObject[] objects = {
                    new RecursiveArrangementWithoutRepetition(alphabet, k),
                    new NonRecursiveArrangementWithoutRepetition(alphabet, k),
                    new RecursivePermutation(alphabet),
                    new NonRecursivePermutation(alphabet),
                    new RecursiveCombinationWithoutRepetition(alphabet, k),
                    new NonRecursiveCombinationWithoutRepetition(alphabet, k),
                    new WordsWithTwoAs(alphabet),
                    new WordsWithTwoRepeatingLetters(alphabet)
            };

            String[] messages = {
                    "Recursive arrangements without repetition:",
                    "Non-recursive arrangements without repetition:",
                    "Recursive permutations:",
                    "Non-recursive permutations:",
                    "Recursive combinations without repetition:",
                    "Non-recursive combinations without repetition:",
                    "Words with exactly two 'a's:",
                    "Words with exactly two letters repeating twice:"
            };

            for (int i = 0; i < objects.length; i++) {
                writer.write(messages[i] + "\n");
                objects[i].generate(writer);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}