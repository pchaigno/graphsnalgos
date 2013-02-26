package main;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

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
	public static boolean isReflexive(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> vertexes = graph.vertexSet();
		for(Integer vertex: vertexes) {
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
	public static boolean isAntiReflexive(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> vertexes = graph.vertexSet();
		for(Integer vertex: vertexes) {
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
	public static boolean isSymetric(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> vertexes = graph.vertexSet();
		for(Integer vertexX: vertexes) {
			for(Integer vertexY: vertexes) {
				if(graph.containsEdge(vertexX, vertexY)) {
					if(!vertexX.equals(vertexY) && !graph.containsEdge(vertexY, vertexX)) {
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
	public static boolean isAntiSymetric(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> vertexes = graph.vertexSet();
		for(Integer vertexX: vertexes) {
			for(Integer vertexY: vertexes) {
				if(graph.containsEdge(vertexX, vertexY)) {
					if(!vertexX.equals(vertexY) && graph.containsEdge(vertexY, vertexX)) {
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
	public static boolean isTransitive(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> vertexes = graph.vertexSet();
		for(Integer vertexX: vertexes) {
			for(Integer vertexY: vertexes) {
				for(Integer vertexZ: vertexes) {
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
	public static boolean isAntiTransitive(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> vertexes = graph.vertexSet();
		for(Integer vertexX: vertexes) {
			for(Integer vertexY: vertexes) {
				for(Integer vertexZ: vertexes) {
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
	public static boolean isStronglyAntiTransitive(Graph<Integer, DefaultEdge> graph) {
		// TODO No idea on how to check this...
		return true;
	}
}
