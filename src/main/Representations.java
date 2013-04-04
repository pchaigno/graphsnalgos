package main;

import graph.DefaultDirectedGraph;
import graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public static int[][] getAdjacencyMatrix(Graph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		int length = vertices.length;
		int[][] adjacencyMatrix = new int[length][length];
		for(int i=0 ; i<length ; i++) {
			for(int j=0 ; j<length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					adjacencyMatrix[i][j] = 1;
				}
			}
		}
		return adjacencyMatrix;
	}
	
	/**
	 * @param graph The graph.
	 * @return The list of targets vertices for each vertex.
	 */
	public static Map<Integer, List<Integer>> getTargetsLists(Graph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer source;
		int length = vertices.length;
		for(int i=0 ; i<length ; i++) {
			source = vertices[i];
			map.put(source, new LinkedList<Integer>());
			for(int vertex: vertices) {
				if(graph.containsEdge(source, vertex)) {
					map.get(source).add(vertex);
				}
			}
		}
		return map;
	}
	
	/**
	 * @param graph The graph.
	 * @return The list of sources vertices for each vertex.
	 */
	public static Map<Integer, List<Integer>> getSourcesLists(Graph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer target;
		int length = vertices.length;
		for(int i=0 ; i<length ; i++) {
			target = vertices[i];
			map.put(target, new LinkedList<Integer>());
			for(int vertex: vertices) {
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
	 * @param vertices The vertices.
	 * @return The graph corresponding.
	 * @throws IllegalArgumentException
	 */
	public static Graph getGraphFromAdjacencyMatrix(int[][] matrix, int[] vertices) throws IllegalArgumentException {
		int length = matrix.length;
		if(length<1 || length!=matrix[0].length) {
			throw new IllegalArgumentException();
		}
		Graph graph = new DefaultDirectedGraph();
		for(int vertex: vertices) {
			graph.addVertex(vertex);
		}
		for(int i=0 ; i<length ; i++) {
			for(int j=0 ; j<length ; j++) {
				if(matrix[i][j]==1) {
					graph.addEdge(vertices[i], vertices[j]);
				}
			}
		}
		return graph;
	}
	
	/**
	 * Build the graph from the list of targets vertices.
	 * @param targets The list of targets vertices for each vertex.
	 * @return The graph built.
	 */
	public static Graph getGraphFromTargetsLists(Map<Integer, List<Integer>> targets) {
		Graph graph = new DefaultDirectedGraph();
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
	 * Build the graph from the list of sources vertices.
	 * @param sources The list of sources vertices for each vertex.
	 * @return The graph built.
	 */
	public static Graph getGraphFromSourcesLists(Map<Integer, List<Integer>> sources) {
		Graph graph = new DefaultDirectedGraph();
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