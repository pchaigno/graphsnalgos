package test;

import graph.DefaultWeightedEdge;
import graph.DefaultWeightedGraph;
import junit.framework.TestCase;

/**
 * Unit tests of weighted graphs.
 * @author Paul Chaignon
 */
public class TestWeightedGraph extends TestCase {
	private DefaultWeightedGraph graph1;
	private DefaultWeightedGraph graph2;
	private DefaultWeightedGraph graph3;
	private DefaultWeightedGraph graph4;
	
	/**
	 * Initialize a few weighted graphs.
	 */
	@Override
	protected void setUp() {
		this.graph1 = new DefaultWeightedGraph();
		this.graph1.addVertex(1);
		this.graph1.addVertex(2);
		this.graph1.addEdge(1, 2, 1);
		
		this.graph2 = new DefaultWeightedGraph();
		this.graph2.addVertex(1);
		this.graph2.addVertex(2);
		this.graph2.addEdge(1, 2);
		
		this.graph3 = new DefaultWeightedGraph();
		this.graph3.addVertex(1);
		this.graph3.addVertex(2);
		this.graph3.addEdge(2, 1);
		
		this.graph4 = new DefaultWeightedGraph();
		this.graph4.addVertex(1);
		this.graph4.addVertex(2);
		this.graph4.addEdge(1, 2, 5);
	}
	
	/**
	 * Test the equals method.
	 */
	public void testEquals() {
		assertEquals(this.graph1, this.graph2);
		assertFalse(this.graph1.equals(this.graph3));
		assertEquals(this.graph1, this.graph4);
	}
	
	/**
	 * Test the clone method.
	 */
	public void testClone() {
		assertEquals(this.graph1, this.graph1.clone());
		assertEquals(this.graph3, this.graph3.clone());
		assertEquals(this.graph4, this.graph4.clone());
	}
	
	/**
	 * Test containsEdge methods.
	 */
	public void testContainsEdge() {
		assertTrue(this.graph4.containsEdge(1, 2));
		assertTrue(this.graph4.containsEdge(new DefaultWeightedEdge(1, 2)));
		assertFalse(this.graph4.containsEdge(2, 1));
		assertFalse(this.graph4.containsEdge(new DefaultWeightedEdge(2, 1)));
	}
	
	/**
	 * Test the errors returned if the edge param is incorrect.
	 */
	@SuppressWarnings("unused")
	public void testErrors() {
		try {
			this.graph4.containsEdge(new DefaultWeightedEdge(1, 3) { private int test; });
			fail();
		} catch(IllegalArgumentException e) {}
		try {
			this.graph4.removeEdge(new DefaultWeightedEdge(1, 3) { private int test; });
			fail();
		} catch(IllegalArgumentException e) {}
		try {
			this.graph4.addEdge(new DefaultWeightedEdge(1, 3) { private int test; });
			fail();
		} catch(IllegalArgumentException e) {}
	}
}