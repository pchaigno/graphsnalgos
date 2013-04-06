package main;

import graph.DefaultGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Different methods concerning the different properties of vertices sets.
 * @author Paul Chaignon
 */
public class VerticesSet {

	/**
	 * Check if a set of vertices is an independent set of a graph.
	 * TODO Does it need to be distinct vertices?
	 * @param graph The graph.
	 * @param s The set to test.
	 * @return True if the set s is an independent set of graph.
	 */
	public static boolean isIndependentSet(DefaultGraph graph, Set<Integer> s) {
		for(int x: s) {
			for(int y: s) {
				if(graph.containsEdge(x, y)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a set of vertices is a maximal independent set of a graph.
	 * @param graph The graph.
	 * @param s The set to test.
	 * @return True if the set s is a maximal independent set of graph.
	 */
	public static boolean isMaximalIndependentSet(DefaultGraph graph, Set<Integer> s) {
		for(Integer vertex: graph.getVertices()) {
			if(!s.contains(vertex)) {
				s.add(vertex);
				if(isIndependentSet(graph, s)) {
					return false;
				}
				s.remove(vertex);
			}
		}
		return true;
	}
	
	/**
	 * Get the maximum independent set of a graph.
	 * TODO Doesn't really work. Just get a maximal independent set.
	 * @param graph The graph.
	 * @return The maximum independent set of graph.
	 */
	public static Set<Integer> getMaximumIndependentSet(DefaultGraph graph) {
		Set<Integer> maximum = new HashSet<Integer>();
		Set<Integer> set;
		int firstVertex;
		boolean notAdjacent;
		List<Integer> unclassedVertices = new LinkedList<Integer>();
		for(int vertex: graph.getVertices()) {
			unclassedVertices.add(vertex);
		}
		while(unclassedVertices.size()>0) {
			// Build a new independent set:
			set = new HashSet<Integer>();
			firstVertex = unclassedVertices.remove(0);
			set.add(firstVertex);
			for(int vertex: graph.getVertices()) {
				if(firstVertex!=vertex) {
					notAdjacent = true;
					for(Integer classedVertices: set) {
						if(graph.containsEdge(classedVertices, vertex) || graph.containsEdge(vertex, classedVertices)) {
							notAdjacent = false;
							break;
						}
					}
					if(notAdjacent) {
						set.add(vertex);
						unclassedVertices.remove(vertex);
					}
				}
			}
			
			// Compare with the actual biggest independent set:
			if(set.size()>maximum.size()) {
				maximum = Tools.clone(set);
			}
		}
		return maximum;
	}
	
	/**
	 * Check if a set of vertices is a clique of a graph.
	 * TODO Does it need to be distinct vertices?
	 * @param graph The graph.
	 * @param s The set to test.
	 * @return True if the set s is a clique of graph.
	 */
	public static boolean isClique(DefaultGraph graph, Set<Integer> s) {
		for(int x: s) {
			for(int y: s) {
				if(x!=y && !graph.containsEdge(x, y) && !graph.containsEdge(y, x)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a set of vertices is a maximal clique of a graph.
	 * @param graph The graph.
	 * @param s The set to test.
	 * @return True if the set s is a maximal clique of graph.
	 */
	public static boolean isMaximalClique(DefaultGraph graph, Set<Integer> s) {
		for(int vertex: graph.getVertices()) {
			if(!s.contains(vertex)) {
				s.add(vertex);
				if(isClique(graph, s)) {
					return false;
				}
				s.remove(vertex);
			}
		}
		return true;
	}
	
	/**
	 * Check if a set is a dominating set in a graph.
	 * @param graph The graph.
	 * @param s The set to test.
	 * @return True if the set is an dominating set.
	 */
	public static boolean isDominatingSet(DefaultGraph graph, Set<Integer> s) {
		boolean isAdjacent;
		for(int vertex: graph.getVertices()) {
			if(!s.contains(vertex)) {
				isAdjacent = false;
				for(int succ: s) {
					if(graph.containsEdge(vertex, succ)) {
						isAdjacent = true;
						break;
					}
				}
				if(!isAdjacent) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if a set of vertices is a minimal dominating set.
	 * @param graph The graph.
	 * @param s The set to check.
	 * @return True if the set is minimal.
	 */
	public static boolean isMinimalDominatingSet(DefaultGraph graph, Set<Integer> s) {
		Set<Integer> copiedSet = Tools.clone(s);
		for(int vertex: s) {
			copiedSet.remove(vertex);
			if(isDominatingSet(graph, copiedSet)) {
				return false;
			}
			copiedSet.add(vertex);
		}
		return true;
	}
}