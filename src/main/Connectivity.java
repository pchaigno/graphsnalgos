package main;

import graph.DefaultDirectedGraph;
import graph.DefaultGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public static DefaultGraph getConnectedComposantByTarjan(DefaultGraph graph, Integer a) {
		Map<Integer, List<Integer>> map = getAdjacencyVertexes(graph);
		
		// Initialization:
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		int length = vertices.length;
		int[] p = new int[length];
		int[] d = new int[length];
		int[] n = new int[length];
		int[] num = new int[length];
		int numA = -1;
		for(int i=0; i<length; i++) {
			p[i] = -1;
			d[i] = map.get(i).size();
			n[i] = -1;
			if(vertices[i]==a) {
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
		if(k+1==length) {
			return graph.clone();
		}
		
		// Construct the subgraph which is the connected composant:
		Set<Integer> connectedVertexes = new HashSet<Integer>();
		connectedVertexes.add(a);
		for(i=0; i<length; i++) {
			if(num[i]<=k && num[i]!=0) {
				connectedVertexes.add(vertices[i]);
			}
		}
		DefaultGraph subgraph = (DefaultGraph)graph.subgraphFrom(connectedVertexes);
		return subgraph;
	}
	
	/**
	 * Construct a map of all adjacency vertices for each vertex.
	 * @param graph The graph.
	 * @return The map of all adjacency vertices.
	 */
	private static Map<Integer, List<Integer>> getAdjacencyVertexes(DefaultGraph graph) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		int length = vertices.length;
		for(int i=0; i<length; i++) {
			map.put(i, new LinkedList<Integer>());
		}
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				if(graph.containsEdge(vertices[i], vertices[j]) || graph.containsEdge(vertices[j], vertices[i])) {
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
	public static List<DefaultGraph> getConnectedComposantsByTarjan(DefaultGraph graph) {
		List<DefaultGraph> connectedComposants = new ArrayList<DefaultGraph>();
		List<Integer> vertices = new ArrayList<Integer>();
		for(int vertex: graph.getVertices()) {
			vertices.add(vertex);
		}
		do {
			connectedComposants.add(getConnectedComposantByTarjan(graph, vertices.get(0)));
			vertices.removeAll(connectedComposants.get(connectedComposants.size()-1).getVertices());
		} while(vertices.size()>0);
		return connectedComposants;
	}

	/**
	 * Check if a graph is connected.
	 * @param graph The graph.
	 * @return True if the graph is connected, false else.
	 */
	public static boolean isStronglyConnected(DefaultDirectedGraph graph) {
		DefaultDirectedGraph closure = TransitiveClosure.getByRoyMarshall(graph);
		Integer[] vertices = closure.getVertices().toArray(new Integer[0]);
		for(int vertexX: vertices) {
			for(int vertexY: vertices) {
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
	public static List<DefaultDirectedGraph> getStronglyConnectedComposantsByFoulkes(DefaultDirectedGraph graph) {
		List<DefaultDirectedGraph> composants = new LinkedList<DefaultDirectedGraph>();
		DefaultDirectedGraph closure = TransitiveClosure.getByRoyMarshall(graph);
		Set<Integer> nc = new HashSet<Integer>();
		for(int vertex: graph.getVertices()) {
			nc.add(vertex);
		}
		
		// Foulkes' algorithm:
		for(int i: graph.getVertices()) {
			if(nc.contains(i)) {
				Set<Integer> cfc = new HashSet<Integer>();
				cfc.add(i);
				nc.remove(i);
				if(closure.containsEdge(i, i)) {
					boolean jSupI = false;
					for(int j: graph.getVertices()) {
						if(jSupI) {
							if(nc.contains(j) && closure.containsEdge(i, j) && closure.containsEdge(j, i)) {
								cfc.add(j);
								nc.remove(j);
							}
						} else if(j==i) {
							jSupI = true;
						}
					}
				}
				
				// Construct the graph:
				DefaultDirectedGraph subgraph = (DefaultDirectedGraph)graph.subgraphFrom(cfc);
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
	public static List<DefaultDirectedGraph> getStronglyConnectedComposantsByAscendantDescendant(DefaultDirectedGraph graph) {
		List<DefaultDirectedGraph> composants = new LinkedList<DefaultDirectedGraph>();
		Set<Integer> nc = new HashSet<Integer>();
		for(int vertex: graph.getVertices()) {
			nc.add(vertex);
		}
		
		// Ascending-descending algorithm:
		for(int i: graph.getVertices()) {
			if(nc.contains(i)) {
				Set<Integer> a = ascendantsUnclassified(graph, nc, i);
				Set<Integer> d = descendantsUnclassified(graph, nc, i);
				Set<Integer> cfc = new HashSet<Integer>();
				a.retainAll(d); // Intersection between two sets.
				cfc.addAll(a);
				nc.removeAll(cfc);
				
				// Construct the graph:
				DefaultDirectedGraph subgraph = (DefaultDirectedGraph)graph.subgraphFrom(cfc);
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
	private static Set<Integer> ascendantsUnclassified(DefaultDirectedGraph graph, Set<Integer> nc, Integer x) {
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
	 * @param nc The vertices not rated.
	 * @param graph The graph.
	 */
	private static void ancestors(Set<Integer> a, Integer y, Set<Integer> nc, DefaultDirectedGraph graph) {
		a.add(y);
		
		// Get unclassified predecessors of y:
		Set<Integer> lPred = new HashSet<Integer>();
		for(int notRated: nc) {
			if(graph.containsEdge(notRated, y)) {
				lPred.add(notRated);
			}
		}
		
		for(int z: lPred) {
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
	private static Set<Integer> descendantsUnclassified(DefaultDirectedGraph graph, Set<Integer> nc, Integer x) {
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
	 * @param nc The vertices not rated.
	 * @param graph The graph.
	 */
	private static void sons(Set<Integer> d, Integer y, Set<Integer> nc, DefaultDirectedGraph graph) {
		d.add(y);
		
		// Get unclassified successors of y. 
		Set<Integer> lSuc = new HashSet<Integer>();
		for(int notRated: nc) {
			if(graph.containsEdge(y, notRated)) {
				lSuc.add(notRated);
			}
		}
		
		for(int z: lSuc) {
			if(!d.contains(z)) {
				sons(d, z, nc, graph);
			}
		}
	}
	
	/**
	 * Check if a graph is almost strongly connected.
	 * @param graph The graph.
	 * @return True if it's almost strongly connected, false else.
	 */
	public static boolean isAlmostStronglyConnected(DefaultDirectedGraph graph) {
		int[][] routingMatrix = Routing.routingByRoyMarshallWithSuccessor(graph);
		return isAlmostStronglyConnected(routingMatrix);
	}
	
	/**
	 * Check if a graph is almost strongly connected.
	 * @param graph The routing matrix of the graph.
	 * @return True if it's almost strongly connected, false else.
	 */
	public static boolean isAlmostStronglyConnected(int[][] routingMatrix) {
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
				return true;
			}
		}
		return false;
	}
}