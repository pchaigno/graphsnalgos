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
	
	/**
	 * Initalize the tests with the graph from the TD.
	 */
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph();
		this.graph2 = new DefaultWeightedGraph();

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
	 * Test the method to get the paths with best cost.
	 */
	public void testBestCostRoutingByRoyMarshallWithSuccessor() {
		Routing.bestCostRoutingByRoyMarshallWithSuccessor(this.graph2);
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
}
