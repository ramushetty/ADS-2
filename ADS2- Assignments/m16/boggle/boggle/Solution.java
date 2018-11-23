import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {

    /**
     * Constructs the object.
     */
    private Solution() {
        // empty constructor
    }

    /**
     * Main method.
     * Time complexity is O(N)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        String caseType = s.nextLine();
        if (!caseType.equals("Score")) {
            System.out.println("board is null");
            return;
        }
        switch (caseType) {
        case "Score":
            String dictionaryName = s.nextLine();
            In in = new In("/Files/" + dictionaryName);
            String[] dictionary = in.readAllStrings();
            BoggleSolver solver = new BoggleSolver(dictionary);

            String boardName = s.nextLine();
            BoggleBoard board = new BoggleBoard("/Files/" + boardName);
            int score = 0;
            for (String word : solver.getAllValidWords(board)) {
                score += solver.scoreOf(word);
            }
            System.out.println("Score = " + score);
            break;

        default:
            try {
                dictionaryName = s.nextLine();
                in = new In("/Files/" + dictionaryName);
                dictionary = in.readAllStrings();
                solver = new BoggleSolver(dictionary);
                board = null;
                score = 0;
                for (String word : solver.getAllValidWords(board)) {
                    score += solver.scoreOf(word);
                }
                System.out.println("Score = " + score);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            break;
        }

    }
}
