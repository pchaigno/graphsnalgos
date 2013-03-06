package main;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Methods to get the different operations on graphs.
 * Works only with 1-graphs.
 * @author Paul Chaignon
 */
public class Operations {

	/**
	 * Construct the union of two graphs.
	 * The result graph is always a 1-graph.
	 * The two graph must have the same vertices.
	 * @param graph1 The first graph.
	 * @param graph2 The second graph.
	 * @return The union of graph1 and graph2.
	 * @throws IllegalArgumentException
	 */
	public static Graph<Integer, DefaultEdge> union(Graph<Integer, DefaultEdge> graph1, Graph<Integer, DefaultEdge> graph2) throws IllegalArgumentException {
		Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		if(!graph1.vertexSet().equals(graph2.vertexSet())) {
			throw new IllegalArgumentException("The two graphs must have the same vertices.");
		}
		for(Integer vertex: graph1.vertexSet()) {
			graph.addVertex(vertex);
		}
		for(Integer vertexX: graph1.vertexSet()) {
			for(Integer vertexY: graph1.vertexSet()) {
				if(graph1.containsEdge(vertexX, vertexY) || graph2.containsEdge(vertexX, vertexY)) {
					graph.addEdge(vertexX, vertexY);
				}
			}
		}
		return graph;
	}
	
	/**
	 * Construct the composition of two graphs.
	 * The result graph is always a 1-graph.
	 * @param graph1 The first graph.
	 * @param graph2 The second graph.
	 * @return The composition of graph1 and graph2, graph1 o graph2.
	 * @throws IllegalArgumentException
	 */
	public static Graph<Integer, DefaultEdge> composition(Graph<Integer, DefaultEdge> graph1, Graph<Integer, DefaultEdge> graph2) throws IllegalArgumentException {
		Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		if(!graph1.vertexSet().equals(graph2.vertexSet())) {
			throw new IllegalArgumentException("The two graphs must have the same vertices.");
		}
		for(Integer vertex: graph1.vertexSet()) {
			graph.addVertex(vertex);
		}
		for(Integer vertexX: graph1.vertexSet()) {
			for(Integer vertexY: graph1.vertexSet()) {
				for(Integer vertexZ: graph1.vertexSet()) {
					if(graph2.containsEdge(vertexX, vertexZ) && graph1.containsEdge(vertexZ, vertexY)) {
						graph.addEdge(vertexX, vertexY);
					}
				}
			}
		}
		return graph;
	}
	
	/**
	 * Construct the graph power p.
	 * The result graph is always a 1-graph.
	 * This method is recursive (non-tail-recursive).
	 * @param graph The graph.
	 * @param p The power.
	 * @return Power p of the graph
	 */
	public static Graph<Integer, DefaultEdge> power(Graph<Integer, DefaultEdge> graph, int p) {
		if(p==1) {
			return Tools.clone(graph);
		}
		return composition(power(graph, p-1), graph);
	}
	
	/**
	 * Construct the transposed graph.
	 * @param graph The graph.
	 * @return The transposed graph.
	 */
	public static Graph<Integer, DefaultEdge> transpose(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> transposed = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(Integer vertex: graph.vertexSet()) {
			transposed.addVertex(vertex);
		}
		for(Integer vertexX: graph.vertexSet()) {
			for(Integer vertexY: graph.vertexSet()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					transposed.addEdge(vertexY, vertexX);
				}
			}
		}
		return transposed;
	}
	
	/**
	 * Construct the complementary graph.
	 * @param graph The graph.
	 * @return The complementary graph.
	 */
	public static Graph<Integer, DefaultEdge> complementary(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> complementary = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(Integer vertex: graph.vertexSet()) {
			complementary.addVertex(vertex);
		}
		for(Integer vertexX: graph.vertexSet()) {
			for(Integer vertexY: graph.vertexSet()) {
				if(!graph.containsEdge(vertexX, vertexY)) {
					complementary.addEdge(vertexX, vertexY);
				}
			}
		}
		return complementary;
	}
	
	/**
	 * Construct the complementary graph without the loops.
	 * @param graph The graph.
	 * @return The complementary graph whitout loops.
	 */
	public static Graph<Integer, DefaultEdge> complementaryWithoutLoops(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> complementary = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(Integer vertex: graph.vertexSet()) {
			complementary.addVertex(vertex);
		}
		for(Integer vertexX: graph.vertexSet()) {
			for(Integer vertexY: graph.vertexSet()) {
				if(!vertexX.equals(vertexY) && !graph.containsEdge(vertexX, vertexY)) {
					complementary.addEdge(vertexX, vertexY);
				}
			}
		}
		return complementary;
	}
	
	/**
	 * Check if a graph is the partial graph of another.
	 * @param partialGraph The possible partial graph to test.
	 * @param graph The graph.
	 * @return True if partialGraph is the partial graph of graph.
	 */
	public static boolean isPartialGraph(Graph<Integer, DefaultEdge> partialGraph, Graph<Integer, DefaultEdge> graph) {
		for(Integer vertexX: graph.vertexSet()) {
			for(Integer vertexY: graph.vertexSet()) {
				if(partialGraph.containsEdge(vertexX, vertexY) && !graph.containsEdge(vertexX, vertexY)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Construct the subgraph of graph from a.
	 * @param graph The original graph.
	 * @param a The list of vertex for the subgraph.
	 * @return The subgraph of graph from a.
	 */
	public static Graph<Integer, DefaultEdge> subgraph(Graph<Integer, DefaultEdge> graph, Set<Integer> a) {
		Graph<Integer, DefaultEdge> subgraph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(Integer vertex: a) {
			subgraph.addVertex(vertex);
		}
		for(Integer vertexX: graph.vertexSet()) {
			for(Integer vertexY: graph.vertexSet()) {
				if(a.contains(vertexX) && a.contains(vertexY) && graph.containsEdge(vertexX, vertexY)) {
					subgraph.addEdge(vertexX, vertexY);
				}
			}
		}
		return subgraph;
	}
}
