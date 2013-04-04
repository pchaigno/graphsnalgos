package graph;

import java.util.Set;

/**
 * Represent an undirected graph.
 * @author Paul Chaignon
 */
public interface Graph extends Cloneable {

	/**
	 * @return The vertices.
	 */
	public Set<Integer> getVertices();
	
	/**
	 * @return The edges.
	 */
	public Set<Edge> getEdges();
	
	/**
	 * Check if a vertex is contained in the graph.
	 * @param vertex The vertex.
	 * @return True if the graph contains the vertex.
	 */
	public boolean containsVertex(int vertex);
	
	/**
	 * Check if an edge is contained in the graph.
	 * @param edge The edge.
	 * @return True if the graph contains the edge.
	 */
	public boolean containsEdge(Edge edge);
	
	/**
	 * Check if an edge is contained in the graph.
	 * @param x The starting vertex of the edge.
	 * @param y The end vertex of the edge.
	 * @return True if the graph contains the edge.
	 */
	public boolean containsEdge(int x, int y);
	
	/**
	 * Add a vertex to the graph.
	 * @param vertex The vertex to add.
	 * @return True if the vertex wasn't already in the graph.
	 */
	public boolean addVertex(int vertex);
	
	/**
	 * Add vertices to the graph starting with vertex 1.
	 * @param nbVertices The number of vertices to add.
	 * @throw IllegalArgumentException If the graph is not empty.
	 */
	public void addVertices(int nbVertices);
	
	/**
	 * Add an edge to the graph.
	 * @param edge The edge to add.
	 * @return True if the edge wasn't already in the graph.
	 * @throw IllegalArgumentException If one of the vertices in not contained in the graph.
	 */
	public boolean addEdge(Edge edge);
	
	/**
	 * Add an edge to the graph.
	 * @param x The starting vertex of the edge.
	 * @param y The end vertex of the edge.
	 * @return True if the edge wasn't already in the graph.
	 * @throw IllegalArgumentException If one of the vertices in not contained in the graph.
	 */
	public boolean addEdge(int x, int y);
	
	public Object clone();
}