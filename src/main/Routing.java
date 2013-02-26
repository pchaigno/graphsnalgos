package main;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Regroups the methods about Routing.
 * Everything that permits to find a path in a graph.
 * @author Paul Chaignon
 */
public class Routing {

	/**
	 * Constructs the routing matrix using a derivative of the Roy-Marshall algorithm.
	 * The routing matrix stores, for each path (x,y), the successor of x.
	 * @param graph The graph.
	 * @return The routing matrix.
	 */
	public static int[][] routingByRoyMarshallWithSuccessor(Graph<Integer, DefaultEdge> graph) {
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		int[][] r = new int[vertexes.length][vertexes.length];
		
		// Initialization:
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int j=0 ; j<vertexes.length ; j++) {
				if(graph.containsEdge(vertexes[i], vertexes[j])) {
					r[i][j] = vertexes[j];
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int x=0 ; x<vertexes.length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<vertexes.length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = r[x][i];
						}
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Constructs the routing matrix using a derivative of the Roy-Marshall algorithm.
	 * The routing matrix stores, for each path (x,y), the predecessor of x.
	 * @param graph The graph.
	 * @return The routage matrix.
	 */
	public static int[][] routingByRoyMarshallWithPredecessor(Graph<Integer, DefaultEdge> graph) {
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		int[][] r = new int[vertexes.length][vertexes.length];
		
		// Initialization:
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int j=0 ; j<vertexes.length ; j++) {
				if(graph.containsEdge(vertexes[i], vertexes[j])) {
					r[i][j] = vertexes[i];
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int x=0 ; x<vertexes.length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<vertexes.length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = r[i][y];
						}
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Constructs the routing matrix using a derivative of the Roy-Marshall algorithm.
	 * The routing matrix stores, for each path (x,y), a step of the path.
	 * @param graph The graph.
	 * @return The routage matrix.
	 */
	public static int[][] routingByRoyMarshallWithAStep(Graph<Integer, DefaultEdge> graph) {
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		int[][] r = new int[vertexes.length][vertexes.length];
		
		// Initialization:
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int j=0 ; j<vertexes.length ; j++) {
				if(graph.containsEdge(vertexes[i], vertexes[j])) {
					r[i][j] = 0;
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int x=0 ; x<vertexes.length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<vertexes.length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = vertexes[i];
						}
					}
				}
			}
		}
		
		return r;
	}
}
