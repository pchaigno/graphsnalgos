package main;

import graph.Graph;

/**
 * Methods to check the different properties of graphs.
 * Works only with 1-graphs.
 * @author Paul Chaignon
 */
public class Properties {

	/**
	 * Check if a graph is reflexive.
	 * @param graph The graph.
	 * @return True if the graph is reflexive, false else.
	 */
	public static boolean isReflexive(Graph graph) {
		for(int vertex: graph.getVertices()) {
			if(!graph.containsEdge(vertex, vertex)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if a graph is anti-reflexive.
	 * @param graph The graph.
	 * @return True if the graph is anti-reflexive, false else.
	 */
	public static boolean isAntiReflexive(Graph graph) {
		for(int vertex: graph.getVertices()) {
			if(graph.containsEdge(vertex, vertex)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if a graph is symetric.
	 * @param graph The graph.
	 * @return True if the graph is symetric, false else.
	 */
	public static boolean isSymetric(Graph graph) {
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					if(vertexX!=vertexY && !graph.containsEdge(vertexY, vertexX)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a graph is anti-symetric.
	 * @param graph The graph.
	 * @return True if the graph is anti-symetric, false else.
	 */
	public static boolean isAntiSymetric(Graph graph) {
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					if(vertexX!=vertexY && graph.containsEdge(vertexY, vertexX)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a graph is transitive.
	 * @param graph The graph.
	 * @return True if the graph is transitive, false else.
	 */
	public static boolean isTransitive(Graph graph) {
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				for(int vertexZ: graph.getVertices()) {
					if(graph.containsEdge(vertexX, vertexY) && graph.containsEdge(vertexY, vertexZ)) {
						if(!graph.containsEdge(vertexX, vertexZ)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a graph is anti-transitive.
	 * @param graph The graph.
	 * @return True if the graph is anti-transitive, false else.
	 */
	public static boolean isAntiTransitive(Graph graph) {
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				for(int vertexZ: graph.getVertices()) {
					if(graph.containsEdge(vertexX, vertexY) && graph.containsEdge(vertexY, vertexZ)) {
						if(graph.containsEdge(vertexX, vertexZ)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a graph is strongly anti-symetric.
	 * @param graph The graph.
	 * @return True if the graph is strongly anti-symetric, false else.
	 */
	public static boolean isStronglyAntiTransitive(Graph graph) {
		// TODO No idea on how to check this...
		return true;
	}
}
