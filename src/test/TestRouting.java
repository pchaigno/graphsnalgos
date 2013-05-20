package test;

import graph.DefaultDirectedGraph;
import graph.DefaultWeightedGraph;

import java.util.List;

import main.Routing;
import main.Tools;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Routing.
 * @author Paul Chaignon
 */
public class TestRouting extends TestCase {
	private DefaultDirectedGraph graph1;
	private DefaultWeightedGraph graph2;
	private DefaultWeightedGraph graph3;
	
	/**
	 * Initalize the tests with the graph from the TD and the annals.
	 */
	@Override
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph();
		this.graph2 = new DefaultWeightedGraph();
		this.graph3 = new DefaultWeightedGraph();

		this.graph1.addVertices(7);
		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(1, 5);
		this.graph1.addEdge(2, 3);
		this.graph1.addEdge(3, 4);
		this.graph1.addEdge(4, 2);
		this.graph1.addEdge(4, 7);
		this.graph1.addEdge(5, 4);
		this.graph1.addEdge(5, 5);
		this.graph1.addEdge(6, 7);
		this.graph1.addEdge(7, 6);
		
		this.graph2.addVertices(5);
		this.graph2.addEdge(1, 2, 2);
		this.graph2.addEdge(1, 3, 8);
		this.graph2.addEdge(2, 3, 1);
		this.graph2.addEdge(2, 4, 3);
		this.graph2.addEdge(3, 4, 4);
		this.graph2.addEdge(3, 5, 2);
		this.graph2.addEdge(4, 5, 7);
		
		this.graph3.addVertices(6);
		this.graph3.addEdge(1, 3, 6);
		this.graph3.addEdge(2, 1, 10);
		this.graph3.addEdge(2, 3, 8);
		this.graph3.addEdge(2, 4, 6);
		this.graph3.addEdge(3, 4, 8);
		this.graph3.addEdge(3, 5, 6);
		this.graph3.addEdge(3, 6, 10);
		this.graph3.addEdge(4, 5, 12);
		this.graph3.addEdge(4, 6, 9);
		this.graph3.addEdge(5, 6, 4);
	}
	
	/**
	 * Test the routing with successor methods.
	 */
	public void testRoutingByRoyMarshallWithSuccessor() {
		int[][] routingMatrix = Routing.routingByRoyMarshallWithSuccessor(this.graph1);
		System.out.println("Routage matrix with successors:");
		System.out.println(Tools.matrixToString(routingMatrix));
		List<Integer> path = Routing.pathFromRoutingWithSuccessor(routingMatrix, 1, 6);
		System.out.println("Path:");
		System.out.println(path);
	}
	
	/**
	 * Test the routing with predecessor methods.
	 */
	public void testRoutingByRoyMarshallWithPredecessor() {
		int[][] routingMatrix = Routing.routingByRoyMarshallWithPredecessor(this.graph1);
		System.out.println("Routage matrix with predecessors:");
		System.out.println(Tools.matrixToString(routingMatrix));
		List<Integer> path = Routing.pathFromRoutingWithPredecessor(routingMatrix, 1, 6);
		System.out.println("Path:");
		System.out.println(path);
	}
	
	/**
	 * Test the routing with "a step" methods.
	 */
	public void testRoutingByRoyMarshallWithAStep() {
		int[][] routingMatrix = Routing.routingByRoyMarshallWithAStep(this.graph1);
		System.out.println("Routage matrix with a step:");
		System.out.println(Tools.matrixToString(routingMatrix));
		List<Integer> path = Routing.pathFromRoutingWithAStep(routingMatrix, 1, 6);
		System.out.println("Path:");
		System.out.println(path);
	}
	
	/**
	 * Test the method to get the paths with best cost by the Roy-Marshall algorithm.
	 */
	public void testBestCostRoutingByRoyMarshallWithSuccessor() {
		Routing.bestCostRoutingByRoyMarshallWithSuccessor(this.graph2);
		assertFalse(Routing.containsCycles());
		int[][] routes = Routing.getRoutes();
		int[] routes_total = {7, 8, 6, 1, -5};
		for(int i=0 ; i<routes.length ; i++) {
			int total = 0;
			for(int j=0 ; j<routes.length ; j++) {
				total += routes[i][j];
			}
			assertEquals(routes_total[i], total);
		}
		double[][] values = Routing.getValues();
		double[] values_total = {15, 7, 6, 7, 0};
		for(int i=0 ; i<values.length ; i++) {
			double total = 0;
			for(int j=0 ; j<values.length ; j++) {
				if(values[i][j]<Double.MAX_VALUE) {
					total += values[i][j];
				}
			}
			assertEquals(values_total[i], total);
		}
	}
	
	/**
	 * Test the method to get the paths with best cost by the Roy-Marshall algorithm.
	 */
	public void testBestCostRoutingByRoyMarshallWithPredecessor() {
		Routing.bestCostRoutingByRoyMarshallWithPredecessor(this.graph2);
		assertFalse(Routing.containsCycles());
		int[][] routes = Routing.getRoutes();
		int[] routes_total = {7, 5, 3, 0, -5};
		for(int i=0 ; i<routes.length ; i++) {
			int total = 0;
			for(int j=0 ; j<routes.length ; j++) {
				total += routes[i][j];
			}
			assertEquals(routes_total[i], total);
		}
		double[][] values = Routing.getValues();
		double[] values_total = {15, 7, 6, 7, 0};
		for(int i=0 ; i<values.length ; i++) {
			double total = 0;
			for(int j=0 ; j<values.length ; j++) {
				if(values[i][j]<Double.MAX_VALUE) {
					total += values[i][j];
				}
			}
			assertEquals(values_total[i], total);
		}
	}
	
	/**
	 * Test the method to get the paths with best cost on a graph with a dominating cycle.
	 */
	public void testBestCostRoutingByRoyMarshallWithSuccessorWithDominatingCycle() {
		this.graph2.addEdge(4, 2, -4);
		Routing.bestCostRoutingByRoyMarshallWithSuccessor(this.graph2);
		assertTrue(Routing.containsCycles());
	}
	
	/**
	 * Tets the method to get the paths with the best cost by the Moor-Dijkstra algorithm.
	 */
	public void testBestCostRoutingByMoorDijkstra() {
		Routing.bestCostRoutingByMoorDijkstra(this.graph2);
		assertFalse(Routing.containsCycles());
		int[][] routes = Routing.getRoutes();
		int[] routes_total = {7, 5, 3, 0, -5};
		for(int i=0 ; i<routes.length ; i++) {
			int total = 0;
			for(int j=0 ; j<routes.length ; j++) {
				total += routes[i][j];
			}
			assertEquals(routes_total[i], total);
		}
		double[][] values = Routing.getValues();
		double[] values_total = {15, 7, 6, 7, 0};
		for(int i=0 ; i<values.length ; i++) {
			double total = 0;
			for(int j=0 ; j<values.length ; j++) {
				if(values[i][j]<Double.MAX_VALUE) {
					total += values[i][j];
				}
			}
			assertEquals(values_total[i], total);
		}
	}
	
	/**
	 * Test the method to get the number of maximum paths by Roy-Marshall.
	 */
	public void testNumberMaximumPathsByRoyMarshall() {
		Routing.numberMaximumPathsByRoyMarshall(this.graph3);
		assertFalse(Routing.containsCycles());
		int[][] nbPaths = Routing.getNbPaths();
		int[][] routes = Routing.getRoutes();
		double[][] values = Routing.getValues();
		System.out.println(Tools.matrixToString(routes));
		System.out.println(Tools.matrixToString(nbPaths));
		System.out.println(Tools.matrixToString(values));
	}
}
