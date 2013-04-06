package graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represent an undirected graph.
 * @author Paul Chaignon
 */
public interface Graph<T extends Edge> extends Cloneable {

	/**
	 * @return The vertices.
	 */
	public Set<Integer> getVertices();
	
	/**
	 * @return The edges.
	 */
	public Set<T> getEdges();
	
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
	public boolean containsEdge(T edge);
	
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
	public boolean addEdge(T edge);
	
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
	public boolean removeEdge(T edge);
	
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
	
	/**
	 * Build the union of two graphs.
	 * The result graph is always a 1-graph.
	 * The two graph must have the same vertices.
	 * @param graph The second graph.
	 * @return The union of graph1 and graph2.
	 * @throws IllegalArgumentException If the two graphs don't have the same vertices.
	 */
	public Graph<T> union(Graph<T> graph);
	
	/**
	 * Build the composition of two graphs.
	 * The result graph is always a 1-graph.
	 * @param graph The second graph.
	 * @return The composition of graph1 and graph2, graph1 o graph2.
	 * @throws IllegalArgumentException If the two graphs don't have the same vertices.
	 */
	public Graph<T> composition(Graph<T> graph);
	
	/**
	 * Build the graph power p.
	 * The result graph is always a 1-graph.
	 * This method is recursive (non-tail-recursive).
	 * @param p The power.
	 * @return Power p of the graph.
	 */
	public Graph<T> power(int p);
	
	/**
	 * Build the transposed graph.
	 * @return The transposed graph.
	 */
	public Graph<T> transpose();
	
	/**
	 * Build the complementary graph.
	 * @return The complementary graph.
	 */
	public Graph<T> complementary();
	
	/**
	 * Build the complementary graph without the loops.
	 * @return The complementary graph whitout loops.
	 */
	public Graph<T> complementaryWithoutLoops();
	
	/**
	 * Check if a graph is a partial graph of this one.
	 * @param partialGraph The possible partial graph to test.
	 * @return True if partialGraph is a partial of this one.
	 */
	public boolean isPartialGraph(Graph<T> partialGraph);
	
	/**
	 * Construct the subgraph of graph from a.
	 * @param a The list of vertex for the subgraph.
	 * @return The subgraph of graph from a.
	 */
	public Graph<T> subgraphFrom(Set<Integer> a);
	
	/**
	 * Construct the subgraph of graph without the vertices of a.
	 * @param a The list of vertex to remove from graph.
	 * @return The subgraph of graph from a.
	 */
	public Graph<T> subgraphWithout(Set<Integer> a);
	
	/**
	 * Build the edges graph of a graph.
	 * It must be an undirected 1-graph.
	 * @return The edges graph.
	 */
	public Graph<T> edgesGraph();
	
	/**
	 * Check if a graph is reflexive.
	 * @return True if the graph is reflexive, false else.
	 */
	public boolean isReflexive();
	
	/**
	 * Check if a graph is anti-reflexive.
	 * @return True if the graph is anti-reflexive, false else.
	 */
	public boolean isAntiReflexive();
	
	/**
	 * Check if a graph is symetric.
	 * @return True if the graph is symetric, false else.
	 */
	public boolean isSymetric();
	
	/**
	 * Check if a graph is anti-symetric.
	 * @return True if the graph is anti-symetric, false else.
	 */
	public boolean isAntiSymetric();
	
	/**
	 * Check if a graph is transitive.
	 * @return True if the graph is transitive, false else.
	 */
	public boolean isTransitive();
	
	/**
	 * Check if a graph is anti-transitive.
	 * @return True if the graph is anti-transitive, false else.
	 */
	public boolean isAntiTransitive();
	
	/**
	 * Check if a graph is strongly anti-symetric.
	 * @return True if the graph is strongly anti-symetric, false else.
	 */
	public boolean isStronglyAntiTransitive();
	
	public Object clone();
}