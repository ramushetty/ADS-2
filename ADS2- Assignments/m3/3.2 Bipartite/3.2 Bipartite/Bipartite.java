/**
 * Class for bipartite.
 */
public class Bipartite {
    /**
     * graph bipartite?.
     */
    private boolean isBipartite;
    /**
     * color.
     */
    private boolean[] color;
    /**
     * true if visited.
     */
    private boolean[] marked;
    /**
     * edgeto.
     */
    private int[] edgeTo;
    /**
     * odd-length cycle.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether an undirected graph
     * is bipartite and finds either a
     * bipartition or an odd-length cycle.
     * @param  g the graph
     */
    public Bipartite(final Graph g) {
        isBipartite = true;
        color  = new boolean[g.vertices()];
        marked = new boolean[g.vertices()];
        edgeTo = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }
    /**
     * check that algorithm computes either
     * the topological order or finds a directed cycle.
     * @param      g     g of type Digraph.
     * @param      v     v of type int.
     * Time complexity O(Edges)
     * 
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            // short circuit if odd-length cycle found
            if (cycle != null) {
                return;
            }
            // found uncolored vertex, so recur
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }
    /**
     * Returns true if the graph is bipartite.
     * @return {@code true} if the graph
     * is bipartite; {@code false} otherwise
     * Time complexity O(1).
     */
    public boolean isBipartite() {
        return isBipartite;
    }
    /**
     * Returns the side of the bipartite that vertex {@code v} is on.
     * @param  v the vertex
     * @return the side of the bipartition that
     * vertex {@code v} is on; two vertices()
     * are in the same side of the bipartition
     * if and only if they have the same color
     * Time complexity  O(1).
     */
    public boolean color(final int v) {
        return color[v];
    }
    /**
     * Returns an odd-length cycle if the graph is not bipartite, and
     * {@code null} otherwise.
     * @return an odd-length cycle if the graph is not bipartite
     *         (and hence has an odd-length cycle), and {@code null}
     *         otherwise
     * Time complexity O(1).
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }
}
