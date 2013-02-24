package main;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Tools {

	/**
	 * @param matrix The matrix.
	 * @return A string representation of an integer matrix.
	 */
	public static String matrixToString(int[][] matrix) {
		String str = "";
		for(int i=0 ; i<matrix.length ; i++) {
			for(int j=0 ; j<matrix[i].length ; j++) {
				str += matrix[i][j]+" ";
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * Compare two array of integers.
	 * @param a1 The first array.
	 * @param a2 The second array.
	 * @return True if the two array are equals, false else.
	 */
	public static boolean arrayEquals(Integer[] array1, Integer[] array2) {
		if(array1.length!=array2.length) {
			return false;
		}
		for(int i=0 ; i<array1.length ; i++) {
			if(!arrayContains(array1, array2[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if i is in array.
	 * @param array The array to search in.
	 * @param i The element to search for.
	 * @return True if i is in array.
	 */
	public static boolean arrayContains(Integer[] array, int i) {
		for(int integer: array) {
			if(integer==i) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Construct a copy of the graph.
	 * @param graph The graph to clone.
	 * @return The cloned graph.
	 */
	public static Graph<Integer, DefaultEdge> clone(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> clone = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		for(Integer vertex: vertexes) {
			clone.addVertex(vertex);
		}
		for(Integer vertexX: vertexes) {
			for(Integer vertexY: vertexes) {
				if(graph.containsEdge(vertexX, vertexY)) {
					clone.addEdge(vertexX, vertexY);
				}
			}
		}
		return clone;
	}
	
	/**
	 * Check if two graphs are the same.
	 * @param graph1 The first graph.
	 * @param graph2 The second graph.
	 * @return True if they are equals, false else.
	 */
	public static boolean graphEquals(Graph<Integer, DefaultEdge> graph1, Graph<Integer, DefaultEdge> graph2) {
		Integer[] vertexes1 = graph1.vertexSet().toArray(new Integer[0]);
		Integer[] vertexes2 = graph1.vertexSet().toArray(new Integer[0]);
		if(!arrayEquals(vertexes1, vertexes2)) {
			return false;
		}
		for(Integer vertexX: vertexes1) {
			for(Integer vertexY: vertexes1) {
				if(graph1.containsEdge(vertexX, vertexY) && !graph2.containsEdge(vertexX, vertexY)) {
					return false;
				}
				if(!graph1.containsEdge(vertexX, vertexY) && graph2.containsEdge(vertexX, vertexY)) {
					return false;
				}
			}
		}
		return true;
	}
}
