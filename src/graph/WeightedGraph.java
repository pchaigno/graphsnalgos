package graph;

/**
 * Represent a weighted directed graph.
 * @author Paul Chaignon
 */
public interface WeightedGraph<T extends WeightedEdge> extends DirectedGraph<T> {
	
	/**
	 * Add an edge to the graph.
	 * @param x The starting vertex of the edge.
	 * @param y The end vertex of the edge.
	 * @param value The value of the edge.
	 * @return True if the edge wasn't already in the graph.
	 * @throw IllegalArgumentException If one of the vertices in not contained in the graph.
	 */
	public boolean addEdge(int x, int y, double value);
}