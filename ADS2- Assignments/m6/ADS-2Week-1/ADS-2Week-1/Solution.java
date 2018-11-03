
import java.util.Scanner;
import java.util.HashMap;

class PageRank {
	Digraph di;

	PageRank(Digraph digraph) {
		di =  digraph;
	}
	public double getPR(int v) {
		int indeg = di. indegree(v);
		int[] ad = new int[indeg];
		int outdeg = di.outdegree(v);
		int i = 0;
		for (int k : di.adj(v)) {
			ad[i] = k;
			i++;
		}
		return pagerank(v, ad);
	}
	public double pagerank(int v , int[] ad) {
		HashMap<Integer, Double> map = new HashMap<>();
		int allver = di.V();
		for (int j = 0; j < allver; j++) {
			map.put(j, (double)1/allver);
		}
		for (int i = 0 ; i < 1000 ; i++) {

		}
		return 0.0;
	}
}





class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		Digraph di = new Digraph(vertices);
		for (int i = 0 ; i < vertices; i++) {
			String[] tokens = scan.nextLine().split(" ");
			for (int j = 0; j < tokens.length - 1; j++) {
				di.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[j+1]));
			}
			
		}
		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		PageRank page = new PageRank(di);

		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		System.out.println(di.toString());
		
		for (int i = 0; i < vertices; i++) {
			System.out.println(i + " - " + page.getPR(i));
		}
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
