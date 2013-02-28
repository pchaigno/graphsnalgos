package main;

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
	 * Construct a copy of the graph.
	 * @param graph The graph to clone.
	 * @return The cloned graph.
	 */
	public static Graph<Integer, DefaultEdge> clone(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> clone = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(Integer vertex: graph.vertexSet()) {
			clone.addVertex(vertex);
		}
		for(Integer vertexX: graph.vertexSet()) {
			for(Integer vertexY: graph.vertexSet()) {
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
		if(!graph1.vertexSet().equals(graph2.vertexSet())) {
			return false;
		}
		for(Integer vertexX: graph1.vertexSet()) {
			for(Integer vertexY: graph1.vertexSet()) {
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
