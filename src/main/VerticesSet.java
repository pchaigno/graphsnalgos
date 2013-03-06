package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

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
	public static boolean isIndependentSet(Graph<Integer, DefaultEdge> graph, Set<Integer> s) {
		for(Integer x: s) {
			for(Integer y: s) {
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
	public static boolean isMaximalIndependentSet(Graph<Integer, DefaultEdge> graph, Set<Integer> s) {
		for(Integer vertex: graph.vertexSet()) {
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
	public static Set<Integer> getMaximumIndependentSet(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> maximum = new HashSet<Integer>();
		Set<Integer> set;
		int firstVertex;
		boolean notAdjacent;
		List<Integer> unclassedVertices = new LinkedList<Integer>();
		for(Integer vertex: graph.vertexSet()) {
			unclassedVertices.add(vertex);
		}
		while(unclassedVertices.size()>0) {
			// Build a new independent set:
			set = new HashSet<Integer>();
			firstVertex = unclassedVertices.remove(0);
			set.add(firstVertex);
			for(Integer vertex: graph.vertexSet()) {
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
}