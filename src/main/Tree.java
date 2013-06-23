package main;

import graph.DefaultDirectedGraph;

/**
 * Regroups the methods about the trees.
 * @author Paul Chaignon
 */
public class Tree {

	/**
	 * Get a root of a graph.
	 * @param graph The graph.
	 * @return The first root found or -1 if there is no root.
	 */
	public static int getRoot(DefaultDirectedGraph graph) {
		int[][] routingMatrix = Routing.routingByRoyMarshallWithSuccessor(graph);
		boolean isRoot;
		int length = routingMatrix.length;
		for(int a=0; a<length; a++) {
			isRoot = true;
			for(int x=0; x<length; x++) {
				if(a!=x && routingMatrix[a][x]==-1) {
					isRoot = false;
					break;
				}
			}
			if(isRoot) {
				Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
				return vertices[a];
			}
		}
		return -1;
	}
	
	/**
	 * Check if a graph is a rooted tree.
	 * @param graph The graph.
	 * @return True if it is.
	 */
	public static boolean isRootedTree(DefaultDirectedGraph graph) {
		if(graph.getEdges().size()+1!=graph.getVertices().size()) {
			return false;
		}
		if(Connectivity.isAlmostStronglyConnected(graph)) {
			return true;
		}
		return false;
	}
}