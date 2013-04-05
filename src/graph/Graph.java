package graph;

import java.util.List;
import java.util.Map;
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
	
	/**
	 * Remove an edge from the graph.
	 * @param x The starting vertex of the edge.
	 * @param y The end vertex of the edge.
	 * @return True if the graph contained the edge.
	 */
	public boolean removeEdge(int x, int y);
	
	/**
	 * Remove an edge from the graph.
	 * @param edge The edge to add.
	 * @return True if the graph contained the edge.
	 */
	public boolean removeEdge(Edge edge);
	
	/**
	 * The adjacency matrix is a square matrix of size (number of vertex).
	 * When there is an edge between i and j, m[i][j] = 1, 0 else.
	 * @return The adjacency matrix.
	 */
	public int[][] getAdjacencyMatrix();
	
	/**
	 * @return The list of targets vertices for each vertex.
	 */
	public Map<Integer, List<Integer>> getTargetsLists();
	
	/**
	 * @return The list of sources vertices for each vertex.
	 */
	public Map<Integer, List<Integer>> getSourcesLists();
	
	/**
	 * Build the graph from the adjacency matrix.
	 * @param matrix The adjacency matrix.
	 * @param vertices The vertices.
	 * @throws IllegalArgumentException If the adjacency matrix is empty or not a square matrix or if the graph isn't empty.
	 */
	public void buildGraphFromAdjacencyMatrix(int[][] matrix, int[] vertices);
	
	/**
	 * Build the graph from the list of targets vertices.
	 * @param targets The list of targets vertices for each vertex.
	 */
	public void buildGraphFromTargetsLists(Map<Integer, List<Integer>> targets);
	
	/**
	 * Build the graph from the list of sources vertices.
	 * @param sources The list of sources vertices for each vertex.
	 */
	public void buildGraphFromSourcesLists(Map<Integer, List<Integer>> sources);
	
	public Object clone();
}