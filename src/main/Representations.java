package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

/**
 * Methods to get the different representations of graphs.
 * Works only with 1-graphs.
 * @author Paul Chaignon
 */
public class Representations {

	/**
	 * The adjacency matrix is a square matrix of size (number of vertex).
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
	 * Build a graph from the adjacency matrix.
	 * @param matrix The adjacency matrix.
	 * @param vertexes The vertexes.
	 * @return The graph corresponding.
	 * @throws IllegalArgumentException
	 */
	public static Graph<Integer, DefaultEdge> getGraphFromAdjacencyMatrix(int[][] matrix, int[] vertexes) throws IllegalArgumentException {
		if(matrix.length<1 || matrix.length!=matrix[0].length) {
			throw new IllegalArgumentException();
		}
		Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(int vertex: vertexes) {
			graph.addVertex(vertex);
		}
		for(int i=0 ; i<matrix.length ; i++) {
			for(int j=0 ; j<matrix.length ; j++) {
				if(matrix[i][j]==1) {
					graph.addEdge(vertexes[i], vertexes[j]);
				}
			}
		}
		return graph;
	}
	
	/**
	 * Build the graph from the list of targets vertexes.
	 * @param targets The list of targets vertexes for each vertex.
	 * @return The graph built.
	 */
	public static Graph<Integer, DefaultEdge> getGraphFromTargetsLists(Map<Integer, List<Integer>> targets) {
		Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(int source: targets.keySet()) {
			if(!graph.containsVertex(source)) {
				graph.addVertex(source);
			}
			for(int target: targets.get(source)) {
				if(!graph.containsVertex(target)) {
					graph.addVertex(target);
				}
				graph.addEdge(source, target);
			}
		}
		return graph;
	}
	
	/**
	 * Build the graph from the list of sources vertexes.
	 * @param sources The list of sources vertexes for each vertex.
	 * @return The graph built.
	 */
	public static Graph<Integer, DefaultEdge> getGraphFromSourcesLists(Map<Integer, List<Integer>> sources) {
		Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(int target: sources.keySet()) {
			if(!graph.containsVertex(target)) {
				graph.addVertex(target);
			}
			for(int source: sources.get(target)) {
				if(!graph.containsVertex(source)) {
					graph.addVertex(source);
				}
				graph.addEdge(source, target);
			}
		}
		return graph;
	}
}