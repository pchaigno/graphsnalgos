package test;

import graph.DefaultDirectedGraph;
import graph.Graph;
import main.TransitiveClosure;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from TransitiveClosure.
 * @author Paul Chaignon
 */
public class TestTransitiveClosure extends TestCase {
	private Graph graph;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph = new DefaultDirectedGraph();

		this.graph.addVertices(5);
		this.graph.addEdge(1, 2);
		this.graph.addEdge(2, 3);
		this.graph.addEdge(3, 2);
		this.graph.addEdge(4, 2);
		this.graph.addEdge(5, 5);
	}
	
	/**
	 * Test the getByPowers method.
	 */
	public void testGetByPowers() {
		Graph closure = TransitiveClosure.getByPowers(this.graph);
		System.out.println("Transitive closure by powers:");
		System.out.println(closure);
	}
	
	/**
	 * Test the getByRoyMarshall method.
	 */
	public void testGetByRoyMarshall() {
		Graph closure = TransitiveClosure.getByRoyMarshall(this.graph);
		System.out.println("Transitive closure by Roy-Marshall:");
		System.out.println(closure);
	}
	
	/**
	 * Test the isTauMinimal method.
	 */
	public void testIsTauMinimal() {
		assertTrue(TransitiveClosure.isTauMinimal(this.graph));
		this.graph.addEdge(1, 3);
		assertFalse(TransitiveClosure.isTauMinimal(this.graph));
	}
}
