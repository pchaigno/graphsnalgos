package main;

import java.util.HashSet;
import java.util.Set;

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
				if(matrix[i][j]>=0) {
					str += " ";
				}
				str += matrix[i][j]+" ";
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * Build a copy of the graph.
	 * @param graph The graph to clone.
	 * @return The cloned graph.
	 */
	public static Graph<Integer, DefaultEdge> clone(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> clone = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(int vertex: graph.vertexSet()) {
			clone.addVertex(vertex);
		}
		for(int vertexX: graph.vertexSet()) {
			for(int vertexY: graph.vertexSet()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					clone.addEdge(vertexX, vertexY);
				}
			}
		}
		return clone;
	}
	
	/**
	 * Build a copy of a set of integers.
	 * @param set The set to clone.
	 * @return The cloned set.
	 */
	public static Set<Integer> clone(Set<Integer> set) {
		Set<Integer> result = new HashSet<Integer>();
		for(int el: set) {
			result.add(el);
		}
		return result;
	}
	
	/**
	 * Check if two graphs are the same.
	 * @param graph1 The first graph.
	 * @param graph2 The second graph.
	 * @return True if they are equals, false else.
	 */
	public static boolean graphEquals(Graph<Integer, DefaultEdge> graph1, Graph<Integer, DefaultEdge> graph2) {
		if(!graph1.vertexSet().equals(graph2.vertexSet())) {
			return false;
		}
		for(int vertexX: graph1.vertexSet()) {
			for(int vertexY: graph1.vertexSet()) {
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
