import java.util.Scanner;
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
     *Time complexity is O(N)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tokens = scan.nextLine().split(" ");
        int first = Integer.parseInt(tokens[0]);
        int second = Integer.parseInt(tokens[1]);
        String[] st = scan.nextLine().split(" ");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(first);
        LinearProbingHashST<String, Integer> hash;
        hash = new LinearProbingHashST<String, Integer>();
        for (int i = 0; i < st.length; i++) {
            hash.put(st[i], i);
        }
        while (second > 0) {
            String[] rout = scan.nextLine().split(" ");
            ewg.addEdge(new Edge(
                            hash.get(rout[0]), hash.get(rout[1]),
                            Double.parseDouble(rout[2])));
            second--;
        }
        int three = Integer.parseInt(scan.nextLine());
        while (three > 0) {
            String[] qu = scan.nextLine().split(" ");
            int n = hash.get(qu[0]);
            DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(ewg, n);
            if (dsp.hasPathTo(hash.get(qu[1]))) {
                System.out.println((int) dsp.distTo(hash.get(qu[1])));
            }
            three--;
        }
    }
}
