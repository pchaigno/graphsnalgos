package main;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

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
	public static Graph<Integer, DefaultEdge> getByPowers(Graph<Integer, DefaultEdge> graph) {
		int n = graph.vertexSet().size();
		Graph<Integer, DefaultEdge> closure = Tools.clone(graph);
		Graph<Integer, DefaultEdge> h;
		for(int i=2 ; i<=n ; i++) {
			h = Tools.clone(closure);
			closure = Operations.union(graph, Operations.composition(graph, closure));
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
	public static Graph<Integer, DefaultEdge> getByRoyMarshall(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> closure = Tools.clone(graph);
		for(int vertex: graph.vertexSet()) {
			teta(closure, vertex, graph.vertexSet());
		}
		return closure;
	}
	
	/**
	 * Teta function from the Roy-Marshall algorithm.
	 * Change the graph to a new graph.
	 * @param graph The graph to be changed.
	 * @param i The vertex on which compute the teta function.
	 */
	private static void teta(Graph<Integer, DefaultEdge> graph, int i, Set<Integer> vertices) {
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
	public static boolean isTauMinimal(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> closure = getByRoyMarshall(graph);
		for(int vertexX: graph.vertexSet()) {
			for(int vertexY: graph.vertexSet()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					graph.removeEdge(vertexX, vertexY);
					if(Tools.graphEquals(closure, getByRoyMarshall(graph))) {
						return false;
					}
					graph.addEdge(vertexX, vertexY);
				}
			}
		}
		return true;
	}
}