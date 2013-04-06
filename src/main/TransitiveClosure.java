package main;

import graph.DefaultDirectedGraph;

import java.util.Set;

/**
 * Different algorithms to get the transitive closure of a graph.
 * Works only with 1-graphs.
 * @author Paul Chaignon
 */
public class TransitiveClosure {

	/**
	 * Construct the transitive closure using the powers of the graph.
	 * @param graph The graph.
	 * @return The transitive closure.
	 */
	public static DefaultDirectedGraph getByPowers(DefaultDirectedGraph graph) {
		int n = graph.getVertices().size();
		DefaultDirectedGraph closure = (DefaultDirectedGraph)graph.clone();
		DefaultDirectedGraph h;
		for(int i=2 ; i<=n ; i++) {
			h = (DefaultDirectedGraph)closure.clone();
			closure = (DefaultDirectedGraph)graph.union(graph.composition(closure));
			if(closure.equals(h)) {
				break;
			}
		}
		return closure;
	}
	
	/**
	 * Construct the transitive closure using the Roy-Marshall algorithm.
	 * @param graph The graph.
	 * @return The transitive closure.
	 */
	public static DefaultDirectedGraph getByRoyMarshall(DefaultDirectedGraph graph) {
		DefaultDirectedGraph closure = (DefaultDirectedGraph)graph.clone();
		for(int vertex: graph.getVertices()) {
			teta(closure, vertex, graph.getVertices());
		}
		return closure;
	}
	
	/**
	 * Teta function from the Roy-Marshall algorithm.
	 * Change the graph to a new graph.
	 * @param graph The graph to be changed.
	 * @param i The vertex on which compute the teta function.
	 */
	private static void teta(DefaultDirectedGraph graph, int i, Set<Integer> vertices) {
		for(int vertexX: vertices) {
			if(graph.containsEdge(vertexX, i)) {
				for(int vertexY: vertices) {
					if(graph.containsEdge(i, vertexY) && !graph.containsEdge(vertexX, vertexY)) {
						graph.addEdge(vertexX, vertexY);
					}
				}
			}
		}
	}
	
	/**
	 * Check is a graph is tau-minimal.
	 * Remove each edge and check that the graph no longer have the same transitive closure.
	 * @param graph The graph.
	 * @return True if it's tau-minimal, false else.
	 */
	public static boolean isTauMinimal(DefaultDirectedGraph graph) {
		DefaultDirectedGraph closure = getByRoyMarshall(graph);
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					graph.removeEdge(vertexX, vertexY);
					if(closure.equals(getByRoyMarshall(graph))) {
						return false;
					}
					graph.addEdge(vertexX, vertexY);
				}
			}
		}
		return true;
	}
}