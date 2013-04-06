package test;

import graph.DefaultEdge;
import graph.DefaultGraph;
import graph.Graph;
import junit.framework.TestCase;

/**
 * Unit tests for undirected graph.
 * @author Paul Chaignon
 */
public class TestGraph extends TestCase {
	private Graph graph1;
	private Graph graph2;
	private Graph graph3;
	private Graph graph4;
	
	/**
	 * Initialize a few undirected graphs.
	 */
	protected void setUp() {
		this.graph1 = new DefaultGraph();
		this.graph1.addVertex(1);
		this.graph1.addVertex(2);
		this.graph1.addEdge(1, 2);
		
		this.graph2 = new DefaultGraph();
		this.graph2.addVertex(1);
		this.graph2.addVertex(2);
		this.graph2.addEdge(1, 2);
		
		this.graph3 = new DefaultGraph();
		this.graph3.addVertex(1);
		this.graph3.addVertex(2);
		this.graph3.addEdge(2, 1);
		
		this.graph4 = new DefaultGraph();
		this.graph4.addVertex(1);
		this.graph4.addVertex(2);
		this.graph4.addVertex(3);
		this.graph4.addEdge(1, 2);
		this.graph4.addEdge(2, 3);
		this.graph4.addEdge(3, 1);
	}
	
	/**
	 * Test the equals method.
	 */
	public void testEquals() {
		assertEquals(this.graph1, this.graph2);
		assertEquals(this.graph1, this.graph3);
		assertFalse(this.graph1.equals(this.graph4));
	}
	
	/**
	 * Test the clone method.
	 */
	public void testClone() {
		assertEquals(this.graph1, this.graph1.clone());
		assertEquals(this.graph3, this.graph3.clone());
	}
	
	/**
	 * Test containsEdge methods.
	 */
	public void testContainsEdge() {
		assertTrue(this.graph4.containsEdge(1, 3));
		assertTrue(this.graph4.containsEdge(new DefaultEdge(3, 1)));
		assertFalse(this.graph4.containsEdge(1, 1));
		assertFalse(this.graph4.containsEdge(new DefaultEdge(1, 1)));
	}
}