import java.util.Scanner;
import java.util.HashMap;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * { returns number of vertices  }.
     *
     * @return     { returns no of vertices }
     */
    int vertices();
    /**
     * { returns number of edges }.
     *
     * @return     { return_value }
     */
    int edges();
    /**
     * Adds an edge.
     *
     * @param      v     { vertex }
     * @param      w     { vertex }
     */
    void addEdge(int v, int w);
    /**
     * { returns vertices present in it }.
     *
     * @param      v     { vertex }
     *
     * @return     { returns vertices }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { vextex }
     * @param      w     { vertex }
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
     * { vertices }.
     */
    private final int vertex;
    /**
     * { edges }.
     */
    private int edges;
    /**
     * {array bag contains  vertices and its adjacencies}.
     */
    private Bag<Integer>[] adj;
    /**
     * { returns number of vertices }.
     * time complexity O(1)
     * @return     { returns certices }
     */
    public int vertices() {
        return vertex;
    }
    /**
     * { returns edges in a bag }.
     * time complexity O(1)
     * @return     { returns number of edges }
     */
    public int edges() {
        return edges;
    }
    /**
     * Constructs the object.
     * time complexity O(vertices)
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
     * time complexity O(1)
     * @param      v     { vextex }
     * @param      w     { vertex }
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
     * {returns vertices adjacent to vertex}.
     *
     * @param      v     { parameter}
     * time complexity O(1).
     * @return     { return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     * time comlexity O(edge).
     * @param      v     { parameter }
     * @param      w     { parameter }
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
 * { solution class }.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty.
    }
    /**
     * { main function }.
     * time complexity of O(1).
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