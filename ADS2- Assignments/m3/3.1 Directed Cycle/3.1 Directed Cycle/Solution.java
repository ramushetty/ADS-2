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
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = scan.nextInt();
        int edges = scan.nextInt();
        Digraph digraph = new Digraph(vertices);
        for (int i = 0; i < edges; i++) {
            digraph.addEdge(scan.nextInt(), scan.nextInt());
        }
        DirectedCycle dicycle = new DirectedCycle(digraph);
        System.out.println(",.");

        if (dicycle.hasCycle()) {
            System.out.println("Cycle exists.");
        System.out.println("d");
            
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}