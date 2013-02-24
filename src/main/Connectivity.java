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
 * Regroups the methods about the conn
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
		//System.err.println(map);
		
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
		//System.err.println();
		
		// Core of the algorithm:
		while(n[i]+1!=d[i] || i!=numA) {
			//System.err.println("Nouveau tour: i="+i);
			if(n[i]+1==d[i]) {
				//System.err.println("n["+i+"]+1==d["+i+"]=="+n[i]);
				i = p[i];
				//System.err.println("i=p[i]="+i);
			} else {
				//System.err.println("n["+i+"]+1!=d["+i+"];n="+n[i]+";d="+d[i]);
				n[i]++;
				j = map.get(i).get(n[i]);
				//System.err.println("n["+i+"]="+n[i]+"; j="+j);
				if(p[j]==-1) {
					//System.err.println("p["+j+"]==-1");
					p[j] = i;
					i = j;
					k++;
					num[i] = k;
					//System.err.println("p["+j+"]="+p[j]+"; k="+k+"; num["+i+"]="+k);
				}
			}
			//System.err.println();
		}
		
		// Check if the graph is connected:
		if(k+1==vertexes.length) {
			//System.err.println("Is connected!");
			return Tools.clone(graph);
		}
		
		// Construct the subgraph which is the connected composant:
		Set<Integer> connectedVertexes = new HashSet<Integer>();
		//System.err.println("k="+k);
		connectedVertexes.add(a);
		for(i=0 ; i<vertexes.length ; i++) {
			//System.err.print(i+"=>"+num[i]+" ");
			if(num[i]<=k && num[i]!=0) {
				connectedVertexes.add(vertexes[i]);
			}
		}
		//System.err.println();
		//System.err.println(connectedVertexes);
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
	 * Construct the strongly connected composant of a graph from the vertex a using the Foulkes algorithm.
	 * @param graph The graph.
	 * @param a The vertex to start on.
	 * @return The connected composant of a graph from the vertex a.
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
				Graph<Integer, DefaultEdge> subgraph = Operations.subgraph(closure, cfc);
				composants.add(subgraph);
			}
		}
		
		return composants;
	}
}