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
	
	/**
	 * Get the value of an edge.
	 * @param x The starting vertex.
	 * @param y The end vertex.
	 * @return The value of the edge.
	 * @throws IllegalArgumentException The edge must be in the graph.
	 */
	public double getValue(int x, int y);
	
	/**
	 * Get the value of an edge.
	 * @param edge The edge.
	 * @return The value of the edge.
	 * @throws IllegalArgumentException The edge must be in the graph.
	 */
	public double getValue(T edge);
}