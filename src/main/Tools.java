package main;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Tools {

	/**
	 * @param matrix The matrix.
	 * @return A string representation of an integer matrix.
	 */
	public static String matrixToString(int[][] matrix) {
		String str = "";
		int length_matrix = matrix.length;
		int length_vector;
		for(int i=0 ; i<length_matrix ; i++) {
			length_vector = matrix[i].length;
			for(int j=0 ; j<length_vector ; j++) {
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
	 * Build a copy of a weighted graph.
	 * @param graph The graph to clone.
	 * @return The cloned graph.
	 */
	public static WeightedGraph<Integer, DefaultEdge> clone(WeightedGraph<Integer, DefaultEdge> graph) {
		WeightedGraph<Integer, DefaultEdge> clone = new DefaultDirectedWeightedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(int vertex: graph.vertexSet()) {
			clone.addVertex(vertex);
		}
		for(int vertexX: graph.vertexSet()) {
			for(int vertexY: graph.vertexSet()) {
				DefaultEdge edge = graph.getEdge(vertexX, vertexY);
				if(edge!=null) {
					clone.addEdge(vertexX, vertexY, edge);
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
	
	/**
	 * Add vertices to a graph.
	 * Start at vertex 1 and end on vertex nbVertices.
	 * @param graph An empty graph.
	 * @param nbVertices The number of vertices to add.
	 */
	public static void addVertices(Graph<Integer, DefaultEdge> graph, int nbVertices) {
		if(graph.vertexSet().size()!=0) {
			throw new IllegalArgumentException("The graph need to be empty.");
		}
		for(int i=1 ; i<=nbVertices ; i++) {
			graph.addVertex(i);
		}
	}
}
