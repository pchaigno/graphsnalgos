package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

/**
 * Methods to get the different representations of graphs.
 * Works only with 1-graphs.
 * @author Paul Chaignon
 */
public class Representation {

	/**
	 * The adjacency matrix is a square matrix of size (number of vertex)².
	 * When there is an edge between i and j, m[i][j] = 1, 0 else.
	 * @param graph The graph.
	 * @return The adjacency matrix.
	 */
	public static int[][] getAdjacencyMatrix(Graph<Integer, DefaultEdge> graph) {
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		int[][] adjacencyMatrix = new int[vertexes.length][vertexes.length];
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int j=0 ; j<vertexes.length ; j++) {
				if(graph.containsEdge(vertexes[i], vertexes[j])) {
					adjacencyMatrix[i][j] = 1;
				}
			}
		}
		return adjacencyMatrix;
	}
	
	/**
	 * @param graph The graph.
	 * @return The list of targets vertexes for each vertex.
	 */
	public static Map<Integer, List<Integer>> getTargetsLists(Graph<Integer, DefaultEdge> graph) {
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer source;
		for(int i=0 ; i<vertexes.length ; i++) {
			source = vertexes[i];
			map.put(source, new LinkedList<Integer>());
			for(Integer vertex: vertexes) {
				if(graph.containsEdge(source, vertex)) {
					map.get(source).add(vertex);
				}
			}
		}
		return map;
	}
	
	/**
	 * @param graph The graph.
	 * @return The list of sources vertexes for each vertex.
	 */
	public static Map<Integer, List<Integer>> getSourcesLists(Graph<Integer, DefaultEdge> graph) {
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer target;
		for(int i=0 ; i<vertexes.length ; i++) {
			target = vertexes[i];
			map.put(target, new LinkedList<Integer>());
			for(Integer vertex: vertexes) {
				if(graph.containsEdge(vertex, target)) {
					map.get(target).add(vertex);
				}
			}
		}
		return map;
	}
	
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
}
