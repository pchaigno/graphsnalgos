package test;

import graph.DefaultDirectedGraph;

import java.util.Map;
import java.util.Set;

import main.Cycles;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Cycles.
 * @author Paul Chaignon
 */
public class TestCycles extends TestCase {
	private DefaultDirectedGraph graph;
	
	/**
	 * Initialiaze the tests with the graph from the handout.
	 */
	protected void setUp() {
		this.graph = new DefaultDirectedGraph();

		this.graph.addVertices(12);
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
	public void testContainsCyclesWithInputOutput() {
		assertFalse(Cycles.containsCyclesByInputOutput(this.graph));
		this.graph.addEdge(7, 11);
		assertTrue(Cycles.containsCyclesByInputOutput(this.graph));
	}
	
	/**
	 * Test the containsCycles method.
	 */
	public void testContainsCyclesWithInput() {
		assertFalse(Cycles.containsCyclesByInput(this.graph));
		this.graph.addEdge(7, 11);
		assertTrue(Cycles.containsCyclesByInput(this.graph));
	}
	
	/**
	 * Test the containsCycles method.
	 */
	public void testContainsCyclesWithOutput() {
		assertFalse(Cycles.containsCyclesByOutput(this.graph));
		this.graph.addEdge(7, 11);
		assertTrue(Cycles.containsCyclesByOutput(this.graph));
	}
	
	/**
	 * Test the getRanksOfVertices method.
	 */
	public void testGetRanksOfVertices() {
		Map<Integer, Set<Integer>> levels = Cycles.getRanksOfVertices(this.graph);
		assertEquals(2, levels.get(0).size());
		assertTrue(levels.get(0).contains(9));
		assertTrue(levels.get(0).contains(12));
		assertEquals(3, levels.get(1).size());
		assertTrue(levels.get(1).contains(3));
		assertTrue(levels.get(1).contains(10));
		assertTrue(levels.get(1).contains(11));
		assertEquals(1, levels.get(2).size());
		assertTrue(levels.get(2).contains(1));
		assertEquals(2, levels.get(3).size());
		assertTrue(levels.get(3).contains(4));
		assertTrue(levels.get(3).contains(5));
		assertEquals(1, levels.get(4).size());
		assertTrue(levels.get(4).contains(2));
		assertEquals(3, levels.get(5).size());
		assertTrue(levels.get(5).contains(6));
		assertTrue(levels.get(5).contains(7));
		assertTrue(levels.get(5).contains(8));
	}
}
