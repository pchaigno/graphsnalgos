package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	 * Use the version of the algorithm with the input and output vertices.
	 * @param graph The graph.
	 * @return True if the graph contains cycles.
	 */
	public static boolean containsCyclesByInputOutput(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> subGraph = Tools.clone(graph);
		Set<Integer> inputVertices, outputVertices;
		while(subGraph.vertexSet().size()!=0) {
			inputVertices = getInputVertices(subGraph);
			outputVertices = getOutputVertices(subGraph);
			if(inputVertices.size()==0 || outputVertices.size()==0) {
				return true;
			}
			inputVertices.addAll(outputVertices);
			subGraph = Operations.subgraphWithout(subGraph, inputVertices);
		}
		return false;
	}
	
	/**
	 * Check if a graph contains cycles using the Marimont algorithm.
	 * Use the version of the algorithm with only the input vertices.
	 * @param graph The graph.
	 * @return True if the graph contains cycles.
	 */
	public static boolean containsCyclesByInput(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> subGraph = Tools.clone(graph);
		Set<Integer> inputVertices;
		while(subGraph.vertexSet().size()!=0) {
			inputVertices = getInputVertices(subGraph);
			if(inputVertices.size()==0) {
				return true;
			}
			subGraph = Operations.subgraphWithout(subGraph, inputVertices);
		}
		return false;
	}
	
	/**
	 * Check if a graph contains cycles using the Marimont algorithm.
	 * Use the version of the algorithm with only the output vertices.
	 * @param graph The graph.
	 * @return True if the graph contains cycles.
	 */
	public static boolean containsCyclesByOutput(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> subGraph = Tools.clone(graph);
		Set<Integer> outputVertices;
		while(subGraph.vertexSet().size()!=0) {
			outputVertices = getOutputVertices(subGraph);
			if(outputVertices.size()==0) {
				return true;
			}
			subGraph = Operations.subgraphWithout(subGraph, outputVertices);
		}
		return false;
	}
	
	/**
	 * Get the rank of all vertices of the graph using the Marimont algorithm with the input vertices.
	 * Will return null if the graph contains cycles.
	 * @param graph The graph.
	 * @return A map with the rank as key and the vertices corresponding as value or null if the graph contains cycle.
	 */
	public static Map<Integer, Set<Integer>> getRanksOfVertices(Graph<Integer, DefaultEdge> graph) {
		Map<Integer, Set<Integer>> levels = new HashMap<Integer, Set<Integer>>();
		Graph<Integer, DefaultEdge> subGraph = Tools.clone(graph);
		Set<Integer> inputVertices;
		int rank = 0;
		while(subGraph.vertexSet().size()!=0) {
			inputVertices = getInputVertices(subGraph);
			if(inputVertices.size()==0) {
				return null;
			}
			levels.put(rank, inputVertices);
			rank++;
			subGraph = Operations.subgraphWithout(subGraph, inputVertices);
		}
		return levels;
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
