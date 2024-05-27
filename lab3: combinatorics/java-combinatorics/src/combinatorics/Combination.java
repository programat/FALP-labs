package combinatorics;

public abstract class Combination implements CombinatorialObject {
    protected char[] alphabet;
    protected int length;

    public Combination(char[] alphabet, int length) {
        this.alphabet = alphabet;
        this.length = length;
    }
}
