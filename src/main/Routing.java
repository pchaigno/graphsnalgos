package main;

import java.util.LinkedList;
import java.util.List;

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
		Integer[] vertices = graph.vertexSet().toArray(new Integer[0]);
		int[][] r = new int[vertices.length][vertices.length];
		
		// Initialization:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int j=0 ; j<vertices.length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					r[i][j] = vertices[j];
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int x=0 ; x<vertices.length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<vertices.length ; y++) {
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
		Integer[] vertices = graph.vertexSet().toArray(new Integer[0]);
		int[][] r = new int[vertices.length][vertices.length];
		
		// Initialization:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int j=0 ; j<vertices.length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					r[i][j] = vertices[i];
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int x=0 ; x<vertices.length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<vertices.length ; y++) {
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
		Integer[] vertices = graph.vertexSet().toArray(new Integer[0]);
		int[][] r = new int[vertices.length][vertices.length];
		
		// Initialization:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int j=0 ; j<vertices.length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					r[i][j] = 0;
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int x=0 ; x<vertices.length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<vertices.length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = vertices[i];
						}
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Build, using a recursive method and the routing matrix, the path from x to y.
	 * @param r The routing matrix with the successors.
	 * @param x The beginning vertex.
	 * @param y The final vertex.
	 * @return The path from x to y as a list of vertex or null if there is no path.
	 */
	public static List<Integer> pathFromRoutingWithSuccessor(int[][] r, int x, int y) {
		if(r[x-1][y-1]==-1) {
			return null;
		}
		List<Integer> path = new LinkedList<Integer>();
		buildPathFromRoutingWithSuccessor(r, path, x, y);
		return path;
	}
	
	/**
	 * Build recursively the path from i to y using the routing matrix.
	 * The parameter path is changed by the method.
	 * This method is tail-recursive.
	 * @param r The routing matrix with the successors.
	 * @param path The path to complete.
	 * @param i The current vertex.
	 * @param y The final vertex.
	 */
	private static void buildPathFromRoutingWithSuccessor(int[][] r, List<Integer> path, int i, int y) {
		path.add(i);
		if(i!=y) {
			buildPathFromRoutingWithSuccessor(r, path, r[i-1][y-1], y);
		}
	}
	
	/**
	 * Build, using a recursive method and the routing matrix, the path from x to y.
	 * @param r The routing matrix with the predecessors.
	 * @param x The beginning vertex.
	 * @param y The final vertex.
	 * @return The path from x to y as a list of vertex or null if there is no path.
	 */
	public static List<Integer> pathFromRoutingWithPredecessor(int[][] r, int x, int y) {
		if(r[x-1][y-1]==-1) {
			return null;
		}
		List<Integer> path = new LinkedList<Integer>();
		buildPathFromRoutingWithPredecessor(r, path, x, y);
		return path;
	}
	
	/**
	 * Build recursively the path from x to j using the routing matrix.
	 * The parameter path is changed by the method.
	 * This method is tail-recursive.
	 * @param r The routing matrix with predecessors.
	 * @param path The path to complete.
	 * @param x The beginning vertex.
	 * @param j The current vertex.
	 */
	private static void buildPathFromRoutingWithPredecessor(int[][] r, List<Integer> path, int x, int j) {
		if(x!=j) {
			buildPathFromRoutingWithPredecessor(r, path, x, r[x-1][j-1]);
		}
		path.add(j);
	}
	
	/**
	 * Build, using a recursive method and the routing matrix, the path from x to y.
	 * @param r The routing matrix with a step between x and y.
	 * @param x The beginning vertex.
	 * @param y The final vertex.
	 * @return The path from x to y as a list of vertex or null if there is no path.
	 */
	public static List<Integer> pathFromRoutingWithAStep(int[][] r, int x, int y) {
		if(r[x-1][y-1]==-1) {
			return null;
		}
		List<Integer> path = new LinkedList<Integer>();
		path.add(x);
		buildPathFromRoutingWithAStep(r, path, x, y);
		return path;
	}
	
	/**
	 * Build recursively the path from i to j using the routing matrix.
	 * The parameter path is changed by the method.
	 * This method is recursive.
	 * TODO Is it tail-recursive? (if not remove last line)
	 * @param r The routing matrix.
	 * @param path The path to complete.
	 * @param i The current vertex (start).
	 * @param y The current vertex (end).
	 */
	private static void buildPathFromRoutingWithAStep(int[][] r, List<Integer> path, int i, int j) {
		if(r[i-1][j-1]==0) {
			path.add(j);
		} else {
			buildPathFromRoutingWithAStep(r, path, i, r[i-1][j-1]);
			buildPathFromRoutingWithAStep(r, path, r[i-1][j-1], j);
		}
	}
}