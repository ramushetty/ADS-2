import java.util.Scanner;

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Method.
     * Complexity  is N M.
     * N  No of Strings
     * M Length of Each String.
     *
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        Scanner scan = new Scanner(System.in);

        TST<Integer> tst = new TST<Integer>();
        int val = 0;
        for (int i = 0; i < words.length; i++) {
            tst.put(words[i], val++);
            for (int j = 0; j < words[i].length(); j++) {
                tst.put(words[i].substring(j, words[i].length()), val++);
            }
        }
        String substring = scan.nextLine();
        for (String s : tst.keysWithPrefix(substring)) {
            System.out.println(s);
        }
    }
    /**
     * Loads words.
     *
     * @return     { returns String Array }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
