package test;

import graph.DefaultDirectedEdge;
import graph.DefaultDirectedGraph;
import graph.DefaultWeightedEdge;
import junit.framework.TestCase;

/**
 * Unit tests for directed graphs.
 * @author Paul Chaignon
 */
public class TestDirectedGraph extends TestCase {
	private DefaultDirectedGraph graph1;
	private DefaultDirectedGraph graph2;
	private DefaultDirectedGraph graph3;
	
	/**
	 * Initalize a few directed graphs.
	 */
	protected void setUp() {
		this.graph1 = new DefaultDirectedGraph();
		this.graph1.addVertex(1);
		this.graph1.addVertex(2);
		this.graph1.addEdge(1, 2);
		
		this.graph2 = new DefaultDirectedGraph();
		this.graph2.addVertex(1);
		this.graph2.addVertex(2);
		this.graph2.addEdge(1, 2);
		
		this.graph3 = new DefaultDirectedGraph();
		this.graph3.addVertex(1);
		this.graph3.addVertex(2);
		this.graph3.addEdge(2, 1);
	}
	
	/**
	 * Test the equals method.
	 */
	public void testEquals() {
		assertEquals(this.graph1, this.graph2);
		assertFalse(this.graph1.equals(this.graph3));
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
		assertTrue(this.graph1.containsEdge(1, 2));
		assertTrue(this.graph1.containsEdge(new DefaultDirectedEdge(1, 2)));
		assertFalse(this.graph1.containsEdge(2, 1));
		assertFalse(this.graph1.containsEdge(new DefaultDirectedEdge(2, 1)));
	}
	
	/**
	 * Test the errors returned if the edge param is incorrect.
	 */
	public void testErrors() {
		try {
			this.graph3.containsEdge(new DefaultWeightedEdge(1, 3));
			fail();
		} catch(IllegalArgumentException e) {}
		try {
			this.graph3.removeEdge(new DefaultWeightedEdge(3, 1));
			fail();
		} catch(IllegalArgumentException e) {}
		try {
			this.graph3.addEdge(new DefaultWeightedEdge(9, 9));
			fail();
		} catch(IllegalArgumentException e) {}
	}
}