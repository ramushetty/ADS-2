/**
 * Class for graph.
 */
class Graph {
    /**
     * integer var vertices.
     */
    private int vertices;
    /**
     * integer var edges.
     */
    private int edges;
    /**
     * array of bag.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     */
    Graph() {

    }
    /**
     * Constructs the object.
     * Time complexity O(n).
     * @param      vertex     integer variable.
     */
    Graph(final int vertex) {
        vertices = vertex;
        edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * return vertices.
     * Time complexity O(1).
     * @return  vertices.
     */
    public int vertices() {
        return vertices;
    }
    /**
     * return edge.
     * Time complexity O(1).
     * @return edges.
     */
    public int edges() {
        return edges;
    }
    /**
     * Adds edge.
     * Time complexity O(1)
     * @param      v     integer variable.
     * @param      w     integer variable.
     */
    public void addEdge(final int v, final int w) {
        if (!hasEdge(v, w)) {
            edges++;
            adj[v].add(w);
            adj[w].add(v);
        }
        if (v == w) {
            return;
        }
    }
    /**
     * checks for edge.
     * @param      v     integer variable.
     * @param      w     integer variable.
     * Time complexity O(v)
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        if (adj[v] == null) {
            return true;
        }
        for (int i : adj[v]) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * iterable function.
     * @param      v integer variable.
     * Time complexity  O(v)
     * @return   array.
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * matrix.
     * Time complexity O(1)
     * @return   array.
     */
    public Bag[] matrix() {
        return adj;
    }
    /**
     * list.
     * Time complexity O(1)
     * @return  array.
     */
    public Bag[] list() {
        return adj;
    }
}
