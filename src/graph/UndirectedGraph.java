package graph;

public interface UndirectedGraph<T extends Edge> extends Graph<T> {
	
	/**
	 * Build the edges graph of a graph.
	 * It must be an undirected 1-graph.
	 * @return The edges graph.
	 */
	public Graph<DefaultEdge> edgesGraph();
}
