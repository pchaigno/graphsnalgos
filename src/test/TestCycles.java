package test;

import java.util.Set;

import main.Cycles;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Cycles.
 * @author Paul Chaignon
 */
public class TestCycles extends TestCase {
	private Graph<Integer, DefaultEdge> graph;
	
	/**
	 * Initialiaze the tests with the graph from the handout.
	 */
	protected void setUp() {
		this.graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		this.graph.addVertex(1);
		this.graph.addVertex(2);
		this.graph.addVertex(3);
		this.graph.addVertex(4);
		this.graph.addVertex(5);
		this.graph.addVertex(6);
		this.graph.addVertex(7);
		this.graph.addVertex(8);
		this.graph.addVertex(9);
		this.graph.addVertex(10);
		this.graph.addVertex(11);
		this.graph.addVertex(12);

		this.graph.addEdge(1, 4);
		this.graph.addEdge(1, 5);
		this.graph.addEdge(2, 6);
		this.graph.addEdge(2, 7);
		this.graph.addEdge(2, 8);
		this.graph.addEdge(4, 2);
		this.graph.addEdge(5, 6);
		this.graph.addEdge(9, 2);
		this.graph.addEdge(9, 3);
		this.graph.addEdge(9, 5);
		this.graph.addEdge(10, 1);
		this.graph.addEdge(11, 1);
		this.graph.addEdge(12, 10);
		this.graph.addEdge(12, 11);
	}
	
	/**
	 * Test the getInputVertices method.
	 */
	public void testGetInputVertices() {
		Set<Integer> inputVertices = Cycles.getInputVertices(this.graph);
		assertEquals(2, inputVertices.size());
		assertTrue(inputVertices.contains(9));
		assertTrue(inputVertices.contains(12));
	}
	
	/**
	 * Test the getOutputVertices method.
	 */
	public void testGetOutputVertices() {
		Set<Integer> outputVertices = Cycles.getOutputVertices(this.graph);
		assertEquals(4, outputVertices.size());
		assertTrue(outputVertices.contains(3));
		assertTrue(outputVertices.contains(6));
		assertTrue(outputVertices.contains(7));
		assertTrue(outputVertices.contains(8));
	}
	
	/**
	 * Test the containsCycles method.
	 */
	public void testContainsCycles() {
		assertFalse(Cycles.containsCycles(this.graph));
		this.graph.addEdge(7, 11);
		assertTrue(Cycles.containsCycles(this.graph));
	}
}
