package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Regroups the methods about the connectivity.
 * @author Paul Chaignon
 */
public class Connectivity {
	
	/**
	 * Construct the connected composant of a graph from the vertex a using the Tarjan algorithm.
	 * @param graph The graph.
	 * @param a The vertex to start on.
	 * @return The connected composant of a graph from the vertex a.
	 */
	public static Graph<Integer, DefaultEdge> getConnectedComposantByTarjan(Graph<Integer, DefaultEdge> graph, Integer a) {
		Map<Integer, List<Integer>> map = getAdjacencyVertexes(graph);
		
		// Initialization:
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		int[] p = new int[vertexes.length];
		int[] d = new int[vertexes.length];
		int[] n = new int[vertexes.length];
		int[] num = new int[vertexes.length];
		int numA = -1;
		for(int i=0 ; i<vertexes.length ; i++) {
			p[i] = -1;
			d[i] = map.get(i).size();
			n[i] = -1;
			if(vertexes[i]==a) {
				numA = i;
			}
		}
		int i = numA;
		int k = 0;
		num[numA] = 0;
		p[numA] = numA;
		int j;
		
		// Core of the algorithm:
		while(n[i]+1!=d[i] || i!=numA) {
			if(n[i]+1==d[i]) {
				i = p[i];
			} else {
				n[i]++;
				j = map.get(i).get(n[i]);
				if(p[j]==-1) {
					p[j] = i;
					i = j;
					k++;
					num[i] = k;
				}
			}
		}
		
		// Check if the graph is connected:
		if(k+1==vertexes.length) {
			return Tools.clone(graph);
		}
		
		// Construct the subgraph which is the connected composant:
		Set<Integer> connectedVertexes = new HashSet<Integer>();
		connectedVertexes.add(a);
		for(i=0 ; i<vertexes.length ; i++) {
			if(num[i]<=k && num[i]!=0) {
				connectedVertexes.add(vertexes[i]);
			}
		}
		Graph<Integer, DefaultEdge> subgraph = Operations.subgraph(graph, connectedVertexes);
		return subgraph;
	}
	
	/**
	 * Construct a map of all adjacency vertexes for each vertex.
	 * @param graph The graph.
	 * @return The map of all adjacency vertexes.
	 */
	private static Map<Integer, List<Integer>> getAdjacencyVertexes(Graph<Integer, DefaultEdge> graph) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer[] vertexes = graph.vertexSet().toArray(new Integer[0]);
		for(int i=0 ; i<vertexes.length ; i++) {
			map.put(i, new LinkedList<Integer>());
		}
		for(int i=0 ; i<vertexes.length ; i++) {
			for(int j=0 ; j<vertexes.length ; j++) {
				if(graph.containsEdge(vertexes[i], vertexes[j]) || graph.containsEdge(vertexes[j], vertexes[i])) {
					if(!map.get(i).contains(j)) {
						map.get(i).add(j);
					}
					if(!map.get(j).contains(i)) {
						map.get(j).add(i);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * Constructs all the connected composants of a graph using the Tarjan algorithm.
	 * @param graph The graph.
	 * @return A list of all connected composants (subgraphs of graph).
	 */
	public static List<Graph<Integer, DefaultEdge>> getConnectedComposantsByTarjan(Graph<Integer, DefaultEdge> graph) {
		List<Graph<Integer, DefaultEdge>> connectedComposants = new ArrayList<Graph<Integer, DefaultEdge>>();
		List<Integer> vertexes = new ArrayList<Integer>();
		for(Integer vertex: graph.vertexSet()) {
			vertexes.add(vertex);
		}
		do {
			connectedComposants.add(getConnectedComposantByTarjan(graph, vertexes.get(0)));
			vertexes.removeAll(connectedComposants.get(connectedComposants.size()-1).vertexSet());
		} while(vertexes.size()>0);
		return connectedComposants;
	}

	/**
	 * Check if a graph is connected.
	 * @param graph The graph.
	 * @return True if the graph is connected, false else.
	 */
	public static boolean isStronglyConnected(Graph<Integer, DefaultEdge> graph) {
		Graph<Integer, DefaultEdge> closure = TransitiveClosure.getByRoyMarshall(graph);
		Integer[] vertexes = closure.vertexSet().toArray(new Integer[0]);
		for(Integer vertexX: vertexes) {
			for(Integer vertexY: vertexes) {
				if(!closure.containsEdge(vertexX, vertexY)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Construct the strongly connected composants of a graph using the Foulkes algorithm.
	 * @param graph The graph.
	 * @return The strongly connected composants of a graph.
	 */
	public static List<Graph<Integer, DefaultEdge>> getStronglyConnectedComposantsByFoulkes(Graph<Integer, DefaultEdge> graph) {
		List<Graph<Integer, DefaultEdge>> composants = new LinkedList<Graph<Integer, DefaultEdge>>();
		Graph<Integer, DefaultEdge> closure = TransitiveClosure.getByRoyMarshall(graph);
		Set<Integer> nc = new HashSet<Integer>();
		for(Integer vertex: graph.vertexSet()) {
			nc.add(vertex);
		}
		
		// Foulkes' algorithm:
		for(Integer i: graph.vertexSet()) {
			if(nc.contains(i)) {
				Set<Integer> cfc = new HashSet<Integer>();
				cfc.add(i);
				nc.remove(i);
				if(closure.containsEdge(i, i)) {
					boolean jSupI = false;
					for(Integer j: graph.vertexSet()) {
						if(jSupI) {
							if(nc.contains(j) && closure.containsEdge(i, j) && closure.containsEdge(j, i)) {
								cfc.add(j);
								nc.remove(j);
							}
						} else if(j.equals(i)) {
							jSupI = true;
						}
					}
				}
				
				// Construct the graph:
				Graph<Integer, DefaultEdge> subgraph = Operations.subgraph(graph, cfc);
				composants.add(subgraph);
			}
		}
		
		return composants;
	}
	
	/**
	 * Construct the strongly connected composants of a graph using the ascendant-descendant algorithm.
	 * @param graph The graph.
	 * @return The strongly connected composants of a graph.
	 */
	public static List<Graph<Integer, DefaultEdge>> getStronglyConnectedComposantsByAscendantDescendant(Graph<Integer, DefaultEdge> graph) {
		List<Graph<Integer, DefaultEdge>> composants = new LinkedList<Graph<Integer, DefaultEdge>>();
		Set<Integer> nc = new HashSet<Integer>();
		for(Integer vertex: graph.vertexSet()) {
			nc.add(vertex);
		}
		
		// Ascending-descending algorithm:
		for(Integer i: graph.vertexSet()) {
			if(nc.contains(i)) {
				Set<Integer> a = ascendantsUnclassified(graph, nc, i);
				Set<Integer> d = descendantsUnclassified(graph, nc, i);
				Set<Integer> cfc = new HashSet<Integer>();
				a.retainAll(d); // Intersection between two sets.
				cfc.addAll(a);
				nc.removeAll(cfc);
				
				// Construct the graph:
				Graph<Integer, DefaultEdge> subgraph = Operations.subgraph(graph, cfc);
				composants.add(subgraph);
			}
		}
		
		return composants;
	}
	
	/**
	 * Construct the list of unclassified ascendants of x using a recursive method.
	 * This method is not recursive.
	 * @param graph The graph.
	 * @param x The vertex.
	 * @return Unclassified ascendants of x.
	 */
	private static Set<Integer> ascendantsUnclassified(Graph<Integer, DefaultEdge> graph, Set<Integer> nc, Integer x) {
		Set<Integer> a = new HashSet<Integer>();
		ancestors(a, x, nc, graph);
		return a;
	}
	
	/**
	 * Get all the unclassified ancestors (ie not in nc) of y.
	 * The parameters a is changed by the function.
	 * This method is recursive.
	 * TODO Is it tail-recursive?
	 * @param a The set where to add the new ancestors found.
	 * @param y The vertex.
	 * @param nc The vertexes not rated.
	 * @param graph The graph.
	 */
	private static void ancestors(Set<Integer> a, Integer y, Set<Integer> nc, Graph<Integer, DefaultEdge> graph) {
		a.add(y);
		
		// Get unclassified predecessors of y:
		Set<Integer> lPred = new HashSet<Integer>();
		for(Integer notRated: nc) {
			if(graph.containsEdge(notRated, y)) {
				lPred.add(notRated);
			}
		}
		
		for(Integer z: lPred) {
			if(!a.contains(z)) {
				ancestors(a, z, nc, graph);
			}
		}
	}
	
	/**
	 * Construct the list of unclassified descendants of x using a recursive method.
	 * This method is not recursive.
	 * @param graph The graph.
	 * @param x The vertex.
	 * @return Unclassified descendants of x.
	 */
	private static Set<Integer> descendantsUnclassified(Graph<Integer, DefaultEdge> graph, Set<Integer> nc, Integer x) {
		Set<Integer> d = new HashSet<Integer>();
		sons(d, x, nc, graph);
		return d;
	}
	
	/**
	 * Get all the unclassified sons (ie not in nc) of y.
	 * The parameters d is changed by the function.
	 * This method is recursive.
	 * TODO Is it tail-recursive?
	 * @param d The set where to add the new sons found.
	 * @param y The vertex.
	 * @param nc The vertexes not rated.
	 * @param graph The graph.
	 */
	private static void sons(Set<Integer> d, Integer y, Set<Integer> nc, Graph<Integer, DefaultEdge> graph) {
		d.add(y);
		
		// Get unclassified successors of y. 
		Set<Integer> lSuc = new HashSet<Integer>();
		for(Integer notRated: nc) {
			if(graph.containsEdge(y, notRated)) {
				lSuc.add(notRated);
			}
		}
		
		for(Integer z: lSuc) {
			if(!d.contains(z)) {
				sons(d, z, nc, graph);
			}
		}
	}
}