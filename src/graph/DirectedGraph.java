package graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represent a directed graph.
 * @author Paul Chaignon
 */
public interface DirectedGraph<T extends DirectedEdge> extends Graph<T> {
	
	/**
	 * @return The list of targets vertices for each vertex.
	 */
	public Map<Integer, List<Integer>> getTargetsLists();
	
	/**
	 * @param vertex A vertex.
	 * @return The list of targets for this vertex.
	 */
	public Set<Integer> getTargets(int vertex);
	
	/**
	 * @return The list of sources vertices for each vertex.
	 */
	public Map<Integer, List<Integer>> getSourcesLists();

	/**
	 * @param vertex A vertex.
	 * @return The list of sources for this vertex.
	 */
	public Set<Integer> getSources(int vertex);
}