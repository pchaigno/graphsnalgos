package test;

import main.Routing;
import main.Tools;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Routing.
 * @author Paul Chaignon
 */
public class TestRouting extends TestCase {
	private Graph<Integer, DefaultEdge> graph;
	
	/**
	 * Initalize the tests with the graph from the TD.
	 */
	protected void setUp() throws Exception {
		this.graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		this.graph.addVertex(1);
		this.graph.addVertex(2);
		this.graph.addVertex(3);
		this.graph.addVertex(4);
		this.graph.addVertex(5);
		this.graph.addVertex(6);
		this.graph.addVertex(7);

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
	 * Test the routingByRoyMarshallWithSuccessor method.
	 */
	public void testRoutingByRoyMarshallWithSuccessor() {
		int[][] routageMatrix = Routing.routingByRoyMarshallWithSuccessor(this.graph);
		System.out.println("Routage matrix with successors:");
		System.out.println(Tools.matrixToString(routageMatrix));
	}
	
	/**
	 * Test the routingByRoyMarshallWithPredecessor method.
	 */
	public void testRoutingByRoyMarshallWithPredecessor() {
		int[][] routageMatrix = Routing.routingByRoyMarshallWithPredecessor(this.graph);
		System.out.println("Routage matrix with predecessors:");
		System.out.println(Tools.matrixToString(routageMatrix));
	}
	
	/**
	 * Test the routingByRoyMarshallWithAStep method.
	 */
	public void testRoutingByRoyMarshallWithAStep() {
		int[][] routageMatrix = Routing.routingByRoyMarshallWithAStep(this.graph);
		System.out.println("Routage matrix with a step:");
		System.out.println(Tools.matrixToString(routageMatrix));
	}
}
