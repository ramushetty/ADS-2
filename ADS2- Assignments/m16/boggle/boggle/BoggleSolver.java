import java.util.*;
/**boggle.**/
public class BoggleSolver {
    /**.
     * variable.
     */
    private TrieSET dictionary;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(final String[] dictionary) {
        this.dictionary = new TrieSET();
        for (String s : dictionary) {
            this.dictionary.add(s);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(N ^ 2)
     * @param board value
     * @return value
     * Time complexity is O(rows * cols)
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        Set<String> validWords = new HashSet<String>();

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                collect(board, i, j, marked, "", validWords);
            }
        }

        return validWords;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     *
     * @param board value
     * @param row value
     * @param col value
     * @param marked value
     * @param prefix value
     * @param set value
     * Time complexity is O(9)
     */
    private void collect(final BoggleBoard board, final int row,
        final int col, final boolean[][] marked,
    final String prefix, final Set<String> set) {
        if (marked[row][col]) {
            return;
        }


        char letter = board.getLetter(row, col);
        String word = prefix;
        if (letter == 'Q') {
            word += "QU";
        } else {
            word += letter;
        }

        if (!dictionary.hasPrefix(word)) {
            return;
        }

        if (word.length() > 2 && dictionary.contains(word)) {
            set.add(word);
        }

        marked[row][col] = true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
                    collect(board, row + i, col + j, marked, word, set);
                }
            }
        }

        marked[row][col] = false;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param word value
     * @return value
     */
    public int scoreOf(String word) {
        if (dictionary.contains(word)) {
            switch (word.length()) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            default:
                return 11;
            }
        } else {
            return 0;
        }
    }
}
