/**
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**
     * vextex marked.
     */
    private boolean[] marked;
    /**
     * previous vertex.
     */
    private int[] edgeTo;
    /**
     * vertex in the stack.
     */
    private boolean[] onStack;
    /**
     * directed cycle.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether the digraph {@code G}
     * has a directed cycle and, if so,
     * finds such a cycle.
     * @param g the digraph
     */
    public DirectedCycle(final Digraph g) {
        marked  = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        edgeTo  = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v] && cycle == null) {
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
    private void dfs(final Digraph g, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    /**
     * Does the digraph have a directed cycle?.
     * @return {@code true} if the digraph has
     * a directed cycle, {@code false} otherwise
     * Time complexity  O(1).
     */
    public boolean hasCycle() {
        return cycle != null;
    }
    /**
     * Returns a directed cycle if the digraph has
     * a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable)
     * if the digraph has a directed cycle,
     *    and {@code null} otherwise
     * Time complexity O(1).
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
}
