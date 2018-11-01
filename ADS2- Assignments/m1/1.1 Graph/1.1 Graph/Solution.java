import java.util.Scanner;
import java.util.HashMap;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    int vertices();
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    int edges();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * Class for graphh.
 */
class Graphh implements Graph {
    /**
     * { var_description }.
     */
    private final int vertex;
    /**
     * { var_description }.
     */
    private int edges;
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return vertex;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int edges() {
        return edges;
    }
    /**
     * Constructs the object.
     *
     * @param      ver   The version
     */
    Graphh(final int ver) {
        this.vertex = ver;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int i = 0; i < ver; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            edges++;
        }
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int k : adj[v]) {
            if (k == w) {
                return true;
            }
        }
        return false;
    }


}
/**
 * { item_description }.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty.
    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, String> map = new HashMap<>();
        String names = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Graphh g = new Graphh(vertices);
        String[] keys = scan.nextLine().split(",");
        for (int i = 0; i < vertices; i++) {
            map.put(i, keys[i]);
        }
        for (int j = 0; j < edges; j++) {
            String[] key = scan.nextLine().split(" ");
            g.addEdge(
    Integer.parseInt(key[0]), Integer.parseInt(key[1]));

        }
        System.out.println(
g.vertices() + " vertices, " + g.edges() + " edges");
        if (vertices <= 1 && edges <= 1) {
            System.out.println("No edges");
            return;
        }
        switch (names) {
        case "List":
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < vertices; i++) {
                s.append(map.get(i) + ": ");
                for (int k : g.adj(i)) {
                    s.append(map.get(k) + " ");
                }
                s.append("\n");
            }
            System.out.println(s.toString());
            break;
        case "Matrix":
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (g.hasEdge(i, j)) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                }
                System.out.println();

            }
            break;
        default:
            break;
        }
    }
}
