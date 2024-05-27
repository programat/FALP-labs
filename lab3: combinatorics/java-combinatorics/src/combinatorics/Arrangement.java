package combinatorics;

public abstract class Arrangement implements CombinatorialObject {
    protected char[] alphabet;
    protected int length;

    public Arrangement(char[] alphabet, int length) {
        this.alphabet = alphabet;
        this.length = length;
    }
}
