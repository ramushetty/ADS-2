import java.util.Scanner;
/**
 * { Solution class }.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty.
    }
    /**
     * main function .
     *
     * time complexity O(N)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        while (scan.hasNextLine()) {
            String[] tokens = scan.nextLine().split(" ");
            Edge e = new Edge(Integer.parseInt(
                    tokens[0]), Integer.parseInt(
                    tokens[1]), Double.parseDouble(tokens[2]));
            ewg.addEdge(e);
        }
        KruskalMST kmst = new KruskalMST(ewg);
        System.out.format("%.5f", kmst.weight());
    }
}
