package main;

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
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		Graph<Integer, DefaultEdge> closure = Tools.clone(graph);
		for(int i=0 ; i<vertexes.length ; i++) {
			teta(closure, vertexes[i], vertexes);
		}
		return closure;
	}
	
	/**
	 * Teta function from the Roy-Marshall algorithm.
	 * Change the graph to a new graph.
	 * @param graph The graph to be changed.
	 * @param i The vertex on which compute the teta function.
	 */
	private static void teta(Graph<Integer, DefaultEdge> graph, int i, Integer[] vertexes) {
		for(Integer vertexX: vertexes) {
			if(graph.containsEdge(vertexX, i)) {
				for(Integer vertexY: vertexes) {
					if(graph.containsEdge(i, vertexY) && !graph.containsEdge(vertexX, vertexY)) {
						graph.addEdge(vertexX, vertexY);
					}
				}
			}
		}
	}
}
