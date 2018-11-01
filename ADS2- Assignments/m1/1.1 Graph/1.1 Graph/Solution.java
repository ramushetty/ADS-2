import java.util.Scanner;
import java.util.HashMap;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class Graphh implements Graph {
	private final int vertices;
	int edges;
	public Bag<Integer>[] adj;
	public int V() {
		return vertices;
	}
	public int E() {
		return edges;
	}
	Graphh(final int ver) {
		this.vertices = ver;
		this.edges = 0;
		adj = (Bag<Integer>[]) new Bag[ver];
		for (int i = 0; i < ver; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
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
    public Iterable<Integer> adj(int v) {
    	return adj[v];
    }
    public boolean hasEdge(int v, int w) {
    	for (int k : adj[v]) {
    		if (k == w) {
    			return true;
    		}
    	}
    	return false;
    }


}
public final class Solution {
	private Solution() {
		//empty.
	}
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
			g.addEdge(Integer.parseInt(key[0]), Integer.parseInt(key[1]));

		}
		System.out.println(g.V() + " vertices, " + g.E() + " edges");
		if (vertices <= 1 && edges <= 1) {
			System.out.println("No edges");
			return;
		} 
		switch(names) {
			case "List":
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < vertices; i++) {
					s.append(map.get(i) + ": ");
					for (int k : g.adj[i]) {
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
		}
	}
}
