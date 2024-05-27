package combinatorics;

public abstract class Word implements CombinatorialObject {
    protected char[] alphabet;

    public Word(char[] alphabet) {
        this.alphabet = alphabet;
    }
}
