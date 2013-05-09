package main;

import graph.DefaultDirectedGraph;
import graph.DefaultWeightedEdge;
import graph.DefaultWeightedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Regroups the methods about Routing.
 * Everything that permits to find a path in a graph.
 * @author Paul Chaignon
 */
public class Routing {
	private static int[][] routes;
	private static double[][] values;
	private static int[][] nbPaths;
	
	/**
	 * Constructs the routing matrix using a derivative of the Roy-Marshall algorithm.
	 * The routing matrix stores, for each path (x,y), the successor of x.
	 * @param graph The graph.
	 * @return The routing matrix.
	 */
	public static int[][] routingByRoyMarshallWithSuccessor(DefaultDirectedGraph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		int length = vertices.length;
		int[][] r = new int[length][length];
		
		// Initialization:
		for(int i=0 ; i<length ; i++) {
			for(int j=0 ; j<length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					r[i][j] = vertices[j];
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<length ; i++) {
			for(int x=0 ; x<length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = r[x][i];
						}
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Constructs the routing matrix using a derivative of the Roy-Marshall algorithm.
	 * The routing matrix stores, for each path (x,y), the predecessor of x.
	 * @param graph The graph.
	 * @return The routage matrix.
	 */
	public static int[][] routingByRoyMarshallWithPredecessor(DefaultDirectedGraph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		int length = vertices.length;
		int[][] r = new int[length][length];
		
		// Initialization:
		for(int i=0 ; i<length ; i++) {
			for(int j=0 ; j<length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					r[i][j] = vertices[i];
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<length ; i++) {
			for(int x=0 ; x<length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = r[i][y];
						}
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Constructs the routing matrix using a derivative of the Roy-Marshall algorithm.
	 * The routing matrix stores, for each path (x,y), a step of the path.
	 * @param graph The graph.
	 * @return The routage matrix.
	 */
	public static int[][] routingByRoyMarshallWithAStep(DefaultDirectedGraph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		int length = vertices.length;
		int[][] r = new int[length][length];
		
		// Initialization:
		for(int i=0 ; i<length ; i++) {
			for(int j=0 ; j<length ; j++) {
				if(graph.containsEdge(vertices[i], vertices[j])) {
					r[i][j] = 0;
				} else {
					r[i][j] = -1;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<length ; i++) {
			for(int x=0 ; x<length ; x++) {
				if(r[x][i]!=-1) {
					for(int y=0 ; y<length ; y++) {
						if(r[i][y]!=-1 && r[x][y]==-1) {
							r[x][y] = vertices[i];
						}
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Build, using a recursive method and the routing matrix, the path from x to y.
	 * @param r The routing matrix with the successors.
	 * @param x The beginning vertex.
	 * @param y The final vertex.
	 * @return The path from x to y as a list of vertex or null if there is no path.
	 */
	public static List<Integer> pathFromRoutingWithSuccessor(int[][] r, int x, int y) {
		if(r[x-1][y-1]==-1) {
			return null;
		}
		List<Integer> path = new LinkedList<Integer>();
		buildPathFromRoutingWithSuccessor(r, path, x, y);
		return path;
	}
	
	/**
	 * Build recursively the path from i to y using the routing matrix.
	 * The parameter path is changed by the method.
	 * This method is tail-recursive.
	 * @param r The routing matrix with the successors.
	 * @param path The path to complete.
	 * @param i The current vertex.
	 * @param y The final vertex.
	 */
	private static void buildPathFromRoutingWithSuccessor(int[][] r, List<Integer> path, int i, int y) {
		path.add(i);
		if(i!=y) {
			buildPathFromRoutingWithSuccessor(r, path, r[i-1][y-1], y);
		}
	}
	
	/**
	 * Build, using a recursive method and the routing matrix, the path from x to y.
	 * @param r The routing matrix with the predecessors.
	 * @param x The beginning vertex.
	 * @param y The final vertex.
	 * @return The path from x to y as a list of vertex or null if there is no path.
	 */
	public static List<Integer> pathFromRoutingWithPredecessor(int[][] r, int x, int y) {
		if(r[x-1][y-1]==-1) {
			return null;
		}
		List<Integer> path = new LinkedList<Integer>();
		buildPathFromRoutingWithPredecessor(r, path, x, y);
		return path;
	}
	
	/**
	 * Build recursively the path from x to j using the routing matrix.
	 * The parameter path is changed by the method.
	 * This method is tail-recursive.
	 * @param r The routing matrix with predecessors.
	 * @param path The path to complete.
	 * @param x The beginning vertex.
	 * @param j The current vertex.
	 */
	private static void buildPathFromRoutingWithPredecessor(int[][] r, List<Integer> path, int x, int j) {
		if(x!=j) {
			buildPathFromRoutingWithPredecessor(r, path, x, r[x-1][j-1]);
		}
		path.add(j);
	}
	
	/**
	 * Build, using a recursive method and the routing matrix, the path from x to y.
	 * @param r The routing matrix with a step between x and y.
	 * @param x The beginning vertex.
	 * @param y The final vertex.
	 * @return The path from x to y as a list of vertex or null if there is no path.
	 */
	public static List<Integer> pathFromRoutingWithAStep(int[][] r, int x, int y) {
		if(r[x-1][y-1]==-1) {
			return null;
		}
		List<Integer> path = new LinkedList<Integer>();
		path.add(x);
		buildPathFromRoutingWithAStep(r, path, x, y);
		return path;
	}
	
	/**
	 * Build recursively the path from i to j using the routing matrix.
	 * The parameter path is changed by the method.
	 * This method is recursive.
	 * TODO Is it tail-recursive? (if not remove last line)
	 * @param r The routing matrix.
	 * @param path The path to complete.
	 * @param i The current vertex (start).
	 * @param y The current vertex (end).
	 */
	private static void buildPathFromRoutingWithAStep(int[][] r, List<Integer> path, int i, int j) {
		if(r[i-1][j-1]==0) {
			path.add(j);
		} else {
			buildPathFromRoutingWithAStep(r, path, i, r[i-1][j-1]);
			buildPathFromRoutingWithAStep(r, path, r[i-1][j-1], j);
		}
	}
	
	/**
	 * Build all minimal-cost paths from a vertex to another using the Roy-Marshall algorithm.
	 * Fill the routing and values matrices.
	 * The value matrix stores, for each path, its cost.
	 * The routing matrix stores, for each path (x,y), the successor of x.
	 * @param graph The graph.
	 */
	public static void bestCostRoutingByRoyMarshallWithSuccessor(DefaultWeightedGraph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		routes = new int[vertices.length][vertices.length];
		values = new double[vertices.length][vertices.length];
		
		// Initialization:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int j=0 ; j<vertices.length ; j++) {
				DefaultWeightedEdge edge = new DefaultWeightedEdge(vertices[i], vertices[j]);
				if(graph.containsEdge(edge)) {
					routes[i][j] = vertices[j];
					values[i][j] = graph.getValue(edge);
				} else {
					routes[i][j] = -1;
					values[i][j] = Double.MAX_VALUE;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int x=0 ; x<vertices.length ; x++) {
				if(routes[x][i]!=-1) {
					for(int y=0 ; y<vertices.length ; y++) {
						if(routes[i][y]!=-1) {
							if(values[x][y]>values[x][i]+values[i][y]) {
								values[x][y] = values[x][i]+values[i][y];
								routes[x][y] = routes[x][i];
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Build all minimal-cost paths from a vertex to another using the Roy-Marshall algorithm.
	 * Fill the routing and values matrices.
	 * The value matrix stores, for each path, its cost.
	 * The routing matrix stores, for each path (x,y), the predecessor of y.
	 * @param graph The graph.
	 */
	public static void bestCostRoutingByRoyMarshallWithPredecessor(DefaultWeightedGraph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		routes = new int[vertices.length][vertices.length];
		values = new double[vertices.length][vertices.length];
		
		// Initialization:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int j=0 ; j<vertices.length ; j++) {
				DefaultWeightedEdge edge = new DefaultWeightedEdge(vertices[i], vertices[j]);
				if(graph.containsEdge(edge)) {
					routes[i][j] = vertices[i];
					values[i][j] = graph.getValue(edge);
				} else {
					routes[i][j] = -1;
					values[i][j] = Double.MAX_VALUE;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int x=0 ; x<vertices.length ; x++) {
				if(routes[x][i]!=-1) {
					for(int y=0 ; y<vertices.length ; y++) {
						if(routes[i][y]!=-1) {
							if(values[x][y]>values[x][i]+values[i][y]) {
								values[x][y] = values[x][i]+values[i][y];
								routes[x][y] = routes[i][y];
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Build all minimal-cost paths from a vertex to another using the Moor-Dijkstra algorithm.
	 * Compute this algorithm for each vertex of the graph.
	 * All values must be positive.
	 * @param graph The graph.
	 * @throw IllegalArgumentException If a value from the graph is not positive.
	 */
	public static void bestCostRoutingByMoorDijkstra(DefaultWeightedGraph graph) {
		for(int vertexX: graph.getVertices()) {
			for(int vertexY: graph.getVertices()) {
				if(graph.getValue(vertexX, vertexY)<0) {
					throw new IllegalArgumentException("All values of the weighted graph must be positive.");
				}
			}
		}
		routes = new int[graph.getOrder()][graph.getOrder()];
		values = new double[graph.getOrder()][graph.getOrder()];
		for(int vertex: graph.getVertices()) {
			bestCostRoutingByMoorDijkstra(graph, vertex);
		}
	}
	
	/**
	 * Build minimal-cost paths from the vertex in parameter to all other vertices.
	 * Use the Moor-Dijkstra algorithm.
	 * @param graph The graph.
	 * @param x0 The starting vertex.
	 */
	private static void bestCostRoutingByMoorDijkstra(DefaultWeightedGraph graph, int x0) {
		// Initialization:
		boolean end = false;
		Set<Integer> done = new HashSet<Integer>();
		Set<Integer> todo = new HashSet<Integer>();
		done.add(x0);
		todo.addAll(graph.getVertices());
		todo.remove(x0);
		Map<Integer, Integer> predecessors = new HashMap<Integer, Integer>();
		Map<Integer, Double> costs = new HashMap<Integer, Double>();
		costs.put(x0, 0.0);
		predecessors.put(x0, -1);
		for(int vertex: todo) {
			double value = graph.getValue(x0, vertex);
			costs.put(vertex, value);
			if(value!=Double.MAX_VALUE) {
				predecessors.put(vertex, x0);
			}
		}
		
		// Algorithm:
		while(!end) {
			if(min(costs, todo).getValue().equals(Double.MAX_VALUE)) {
				end = true;
			} else {
				int i = min(costs, todo).getKey();
				done.add(i);
				todo.remove(i);
				if(todo.size()==0) {
					end = true;
				} else {
					Set<Integer> targets = graph.getTargets(i);
					targets.retainAll(todo);
					for(int j: targets) {
						double totalCost = costs.get(i)+graph.getValue(i, j);
						if(costs.get(j)>totalCost) {
							costs.put(j, totalCost);
							predecessors.put(j, i);
						}
					}
				}
			}
		}
		
		// Store the results:
		for(int vertex: graph.getVertices()) {
			if(done.contains(vertex) && !costs.get(vertex).equals(Double.MAX_VALUE)) {
				routes[x0-1][vertex-1] = predecessors.get(vertex);
				values[x0-1][vertex-1] = costs.get(vertex);
			} else {
				routes[x0-1][vertex-1] = -1;
				values[x0-1][vertex-1] = Double.MAX_VALUE;
			}
		}
	}
	
	/**
	 * Search for the minimal value in costs among the elements from todo.
	 * Return null if there is no element of todo in costs.
	 * @param costs The map of costs.
	 * @param todo The element to search for.
	 * @return The minimal cost or null.
	 */
	private static Entry<Integer, Double> min(Map<Integer, Double> costs, Set<Integer> todo) {
		Entry<Integer, Double> min = null;
		for(Entry<Integer, Double> cost: costs.entrySet()) {
			if(todo.contains(cost.getKey()) && (min==null || cost.getValue()<min.getValue())) {
				min = cost;
			}
		}
		return min;
	}
	
	/**
	 * Build all minimal-cost paths from a vertex to another using the Bellman algorithm.
	 * Compute this algorithm for each vertex of the graph.
	 * @param graph The graph.
	 */
	public static void bestCostRoutingByBellman(DefaultWeightedGraph graph) {
		routes = new int[graph.getOrder()][graph.getOrder()];
		values = new double[graph.getOrder()][graph.getOrder()];
		for(int vertex: graph.getVertices()) {
			bestCostRoutingByBellman(graph, vertex);
		}
	}
	
	/**
	 * Build minimal-cost paths from the vertex in parameter to all other vertices.
	 * Use the Bellman algorithm.
	 * @param graph The graph.
	 * @param x0 The starting vertex.
	 * @return TODO
	 */
	// TODO
	private static boolean bestCostRoutingByBellman(DefaultWeightedGraph graph, int x0) {
		/*// Initialization:
		boolean end = false;
		Map<Integer, List<Double>> costs = new HashMap<Integer, List<Double>>();
		Map<Integer, Integer> predecessors = new HashMap<Integer, Integer>();
		for(int vertex: graph.getVertices()) {
			costs.put(vertex, new LinkedList<Double>());
		}
		int k = 1;
		int n = graph.getOrder();
		
		// Algorithm:
		while(k<n && !end) {
			costs.get(1).add(0.0);
			for(int i=2 ; i<=n ; i++) {
				List<Double> lambda = costs.get(i);
				lambda.add();
				if(!lambda.get(lambda.size()-1).equals(lambda.get(lambda.size()-2))) {
					predecessors.put(i, );
				}
			}
			if() {
				end = true;
			} else {
				k++;
			}
		}
		
		// 
		if(k==n+1) {
			return true;
		}
		*/
		return false;
	}
	
	/**
	 * Compute the number of paths of maximum value in a capacity graph for each vertices.
	 * @param graph The capacity graph.
	 */
	public static void numberMaximumPathsByRoyMarshall(DefaultWeightedGraph graph) {
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		routes = new int[vertices.length][vertices.length];
		values = new double[vertices.length][vertices.length];
		nbPaths = new int[vertices.length][vertices.length];
		
		// Initialization:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int j=0 ; j<vertices.length ; j++) {
				DefaultWeightedEdge edge = new DefaultWeightedEdge(vertices[i], vertices[j]);
				if(graph.containsEdge(edge)) {
					routes[i][j] = vertices[j];
					values[i][j] = graph.getValue(edge);
					nbPaths[i][j] = 1;
				} else {
					routes[i][j] = -1;
					values[i][j] = Double.MIN_VALUE;
					nbPaths[i][j] = 0;
				}
			}
		}
		
		// Roy-Marshall's algorithm:
		for(int i=0 ; i<vertices.length ; i++) {
			for(int x=0 ; x<vertices.length ; x++) {
				if(routes[x][i]!=-1) {
					for(int y=0 ; y<vertices.length ; y++) {
						if(routes[i][y]!=-1) {
							if(values[x][y]<Math.min(values[x][i], values[i][y])) {
								values[x][y] = Math.min(values[x][i], values[i][y]);
								routes[x][y] = routes[x][i];
								nbPaths[x][y] = 1;
							} else if(values[x][y]==Math.min(values[x][i], values[i][y])) {
								nbPaths[x][y]++;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * @return The last routing matrix computed.
	 */
	public static int[][] getRoutes() {
		return routes;
	}
	
	/**
	 * @return The last matrix of values computed.
	 */
	public static double[][] getValues() {
		return values;
	}
	
	/**
	 * @return The last matrix of number of paths computed.
	 */
	public static int[][] getNbPaths() {
		return nbPaths;
	}

	/**
	 * Check the values matrix to determine if there are cycles in the last graph computed.
	 * @return True if the last graph computed contains cycles.
	 */
	public static boolean containsCycles() {
		for(int i=0 ; i<values.length ; i++) {
			if(values[i][i]<0) {
				return true;
			}
		}
		return false;
	}
}