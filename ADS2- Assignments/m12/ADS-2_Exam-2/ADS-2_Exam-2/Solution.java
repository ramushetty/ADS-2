import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);

		int n = Integer.parseInt(scan.nextLine()); // no of cities.
		int k = Integer.parseInt(scan.nextLine()); // no roads lines.
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
		while (k > 0) {
				String[] tokens = scan.nextLine().split(" ");
            	Edge e = new Edge(Integer.parseInt(
                    tokens[0]), Integer.parseInt(
                    tokens[1]), Double.parseDouble(tokens[2]));
            		ewg.addEdge(e);
			k--;
		}
		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(ewg);
			// System.out.println("ramu");
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] tokens = scan.nextLine().split(" ");

			DijkstraUndirectedSP d = new DijkstraUndirectedSP(ewg, Integer.parseInt(tokens[0]));
			 if (d.hasPathTo(Integer.parseInt(tokens[1]))) {
	            	
	                		System.out.printf("%.1f  ", d.distTo(Integer.parseInt(tokens[1])));
    
	            }
	            else {     

	            	 	System.out.println("No Path Found.");

	            	}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			// KruskalMST kmst = new KruskalMST(ewg);
   //      	System.out.format("%.5f", kmst.weight());
			String[] token = scan.nextLine().split(" ");
			DijkstraUndirectedSP di = new DijkstraUndirectedSP(ewg, Integer.parseInt(token[0]));
	            if (di.hasPathTo(Integer.parseInt(token[2]))) {
	            	
	                		System.out.printf("%.1f  ", di.distTo(Integer.parseInt(token[2])));
    
	            }
	            else {     

	            	 	System.out.println("No Path Found.");

	            	}

			break;

		default:
			break;
		}

	}
}