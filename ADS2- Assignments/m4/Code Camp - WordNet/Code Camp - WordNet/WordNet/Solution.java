/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // empty Constructor.
    }
    /**
     * {main function}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String fnam = StdIn.readLine();
        String hfnam = StdIn.readLine();
        String token = StdIn.readLine();
        try {
            WordNet wordNet = new WordNet(
                fnam, hfnam);
            switch (token) {
            case "Graph":
                System.out.println(wordNet.getDigraph());
                break;
            case "Queries":
                while (StdIn.hasNextLine()) {
                    String[] tokens = StdIn.readLine().split(" ");
                    if (tokens[0].equals("null") || tokens[1].equals("null")) {
                        throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    } else {
                        System.out.println(
                            "distance = " + wordNet.distance(
                                tokens[0], tokens[1])
                            + ", ancestor = " + wordNet.sap(
                                tokens[0], tokens[1]));
                    }
                }
                break;
            default:
                break;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
