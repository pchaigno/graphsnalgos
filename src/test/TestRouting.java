package test;

import graph.DefaultDirectedGraph;

import java.util.List;

import main.Routing;
import main.Tools;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Routing.
 * @author Paul Chaignon
 */
public class TestRouting extends TestCase {
	private DefaultDirectedGraph graph;
	
	/**
	 * Initalize the tests with the graph from the TD.
	 */
	protected void setUp() throws Exception {
		this.graph = new DefaultDirectedGraph();

		this.graph.addVertices(7);
		this.graph.addEdge(1, 2);
		this.graph.addEdge(1, 5);
		this.graph.addEdge(2, 3);
		this.graph.addEdge(3, 4);
		this.graph.addEdge(4, 2);
		this.graph.addEdge(4, 7);
		this.graph.addEdge(5, 4);
		this.graph.addEdge(5, 5);
		this.graph.addEdge(6, 7);
		this.graph.addEdge(7, 6);
	}
	
	/**
	 * Test the routing with successor methods.
	 */
	public void testRoutingByRoyMarshallWithSuccessor() {
		int[][] routingMatrix = Routing.routingByRoyMarshallWithSuccessor(this.graph);
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
		int[][] routingMatrix = Routing.routingByRoyMarshallWithPredecessor(this.graph);
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
		int[][] routingMatrix = Routing.routingByRoyMarshallWithAStep(this.graph);
		System.out.println("Routage matrix with a step:");
		System.out.println(Tools.matrixToString(routingMatrix));
		List<Integer> path = Routing.pathFromRoutingWithAStep(routingMatrix, 1, 6);
		System.out.println("Path:");
		System.out.println(path);
	}
}
