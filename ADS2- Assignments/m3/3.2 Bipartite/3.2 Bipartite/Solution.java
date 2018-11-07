import java.util.Scanner;
/**
 * Solution class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     * Time complexity O(N).
     * 
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = scan.nextInt();
        int edges = scan.nextInt();
        Graph graph = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            graph.addEdge(scan.nextInt(), scan.nextInt());
        }
        Bipartite bip = new Bipartite(graph);
        if (bip.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
