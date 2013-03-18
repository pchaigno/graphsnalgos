package main;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Regroups the methods about the cycles.
 * @author Paul Chaignon
 */
public class Cycles {

	/**
	 * Check if a graph contains cycles using the Marimont algorithm.
	 * @param graph The graph.
	 * @return True if the graph contains cycles.
	 */
	public static boolean containsCycles(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> subGraph0, subGraph = Tools.clone(graph);
		Set<Integer> inputVertices, outputVertices;
		System.err.println(subGraph);
		while(subGraph.vertexSet().size()!=0) {
			inputVertices = getInputVertices(subGraph);
			outputVertices = getOutputVertices(subGraph);
			if(inputVertices.size()==0 || outputVertices.size()==0) {
				return true;
			}
			inputVertices.addAll(outputVertices);
			subGraph0 = Tools.clone(subGraph);
			subGraph = Operations.subgraphWithout(subGraph, inputVertices);
			if(!Tools.graphEquals(subGraph, subGraph0)) {
				System.err.println(subGraph);
			}
		}
		return false;
	}
	
	/**
	 * Get all input vertices (The vertices that have no predecessor.).
	 * @param graph The graph.
	 * @return The input vertices.
	 */
	public static Set<Integer> getInputVertices(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> inputVertices = new HashSet<Integer>();
		boolean hasPredecessor;
		for(int vertexX: graph.vertexSet()) {
			hasPredecessor = false;
			for(int vertexY: graph.vertexSet()) {
				if(graph.containsEdge(vertexY, vertexX)) {
					hasPredecessor = true;
					break;
				}
			}
			if(!hasPredecessor) {
				inputVertices.add(vertexX);
			}
		}
		return inputVertices;
	}
	
	/**
	 * Get all output vertices (The vertices that have no successor.).
	 * @param graph The graph.
	 * @return The output vertices.
	 */
	public static Set<Integer> getOutputVertices(Graph<Integer, DefaultEdge> graph) {
		Set<Integer> outputVertices = new HashSet<Integer>();
		boolean hasSuccessor;
		for(int vertexX: graph.vertexSet()) {
			hasSuccessor = false;
			for(int vertexY: graph.vertexSet()) {
				if(graph.containsEdge(vertexX, vertexY)) {
					hasSuccessor = true;
					break;
				}
			}
			if(!hasSuccessor) {
				outputVertices.add(vertexX);
			}
		}
		return outputVertices;
	}
}
