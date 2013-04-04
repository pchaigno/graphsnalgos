package main;

import graph.DefaultDirectedGraph;
import graph.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	public static Graph union(Graph graph1, Graph graph2) throws IllegalArgumentException {
		Graph graph = new DefaultDirectedGraph();
		if(!graph1.getVertices().equals(graph2.getVertices())) {
			throw new IllegalArgumentException("The two graphs must have the same vertices.");
		}
		for(int vertex: graph1.getVertices()) {
			graph.addVertex(vertex);
		}
		for(int vertexX: graph1.getVertices()) {
			for(int vertexY: graph1.getVertices()) {
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
	public static Graph composition(Graph graph1, Graph graph2) throws IllegalArgumentException {
		Graph graph = new DefaultDirectedGraph();
		if(!graph1.getVertices().equals(graph2.getVertices())) {
			throw new IllegalArgumentException("The two graphs must have the same vertices.");
		}
		for(int vertex: graph1.getVertices()) {
			graph.addVertex(vertex);
		}
		for(int vertexX: graph1.getVertices()) {
			for(int vertexY: graph1.getVertices()) {
				for(int vertexZ: graph1.getVertices()) {
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
	public static Graph power(Graph graph, int p) {
		if(p==1) {
			return (Graph)graph.clone();
		}
		return composition(power(graph, p-1), graph);
	}
	
	/**
	 * Construct the transposed graph.
	 * @param graph The graph.
	 * @return The transposed graph.
	 */
	public static Graph transpose(Graph graph) {
		Graph transposed = new DefaultDirectedGraph();
		for(int vertex: graph.getVertices()) {
			transposed.addVertex(vertex);
		}
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
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
	public static Graph complementary(Graph graph) {
		Graph complementary = new DefaultDirectedGraph();
		for(int vertex: graph.getVertices()) {
			complementary.addVertex(vertex);
		}
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
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
	public static Graph complementaryWithoutLoops(Graph graph) {
		Graph complementary = new DefaultDirectedGraph();
		for(int vertex: graph.getVertices()) {
			complementary.addVertex(vertex);
		}
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(vertexX!=vertexY && !graph.containsEdge(vertexX, vertexY)) {
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
	public static boolean isPartialGraph(Graph partialGraph, Graph graph) {
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
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
	public static Graph subgraphFrom(Graph graph, Set<Integer> a) {
		Graph subgraph = new DefaultDirectedGraph();
		for(int vertex: a) {
			subgraph.addVertex(vertex);
		}
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(a.contains(vertexX) && a.contains(vertexY) && graph.containsEdge(vertexX, vertexY)) {
					subgraph.addEdge(vertexX, vertexY);
				}
			}
		}
		return subgraph;
	}
	
	/**
	 * Construct the subgraph of graph without the vertices of a.
	 * @param graph The original graph.
	 * @param a The list of vertex to remove from graph.
	 * @return The subgraph of graph from a.
	 */
	public static Graph subgraphWithout(Graph graph, Set<Integer> a) {
		Graph subgraph = new DefaultDirectedGraph();
		for(int vertex: graph.getVertices()) {
			if(!a.contains(vertex)) {
				subgraph.addVertex(vertex);
			}
		}
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(!a.contains(vertexX) && !a.contains(vertexY) && graph.containsEdge(vertexX, vertexY)) {
					subgraph.addEdge(vertexX, vertexY);
				}
			}
		}
		return subgraph;
	}
	
	/**
	 * Build the edges graph of a graph.
	 * It must be an undirected 1-graph.
	 * @param graph The graph.
	 * @return The edges graph.
	 */
	public static Graph edgesGraph(Graph graph) {
		// Couple numbers to the edges and build the vertices of the edges graph:
		Graph edgesGraph = new DefaultDirectedGraph();
		Map<Integer, Integer[]> edges = new HashMap<Integer, Integer[]>();
		int num = 1;
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					edges.put(num, new Integer[] {vertexX, vertexY});
					edgesGraph.addVertex(num);
					num++;
				}
			}
		}
		
		// Link the vertices:
		Integer[] edgeX, edgeY;
		for(int vertexX: edges.keySet()) {
			edgeX = edges.get(vertexX);
			for(int vertexY: edges.keySet()) {
				if(vertexX!=vertexY) {
					edgeY = edges.get(vertexY);
					if(edgeX[0].equals(edgeY[0]) || edgeX[0].equals(edgeY[1]) || edgeX[1].equals(edgeY[0]) || edgeX[1].equals(edgeY[1])) {
						if(!edgesGraph.containsEdge(vertexY, vertexX)) {
							edgesGraph.addEdge(vertexX, vertexY);
						}
					}
				}
			}
		}
		
		return edgesGraph;
	}
}