package main;

import graph.Graph;

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
	public static Graph getByPowers(Graph graph) {
		int n = graph.getVertices().size();
		Graph closure = (Graph)graph.clone();
		Graph h;
		for(int i=2 ; i<=n ; i++) {
			h = (Graph)closure.clone();
			closure = graph.union(graph.composition(closure));
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
	public static Graph getByRoyMarshall(Graph graph) {
		Graph closure = (Graph)graph.clone();
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
	private static void teta(Graph graph, int i, Set<Integer> vertices) {
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
	public static boolean isTauMinimal(Graph graph) {
		Graph closure = getByRoyMarshall(graph);
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