package test;

import graph.DefaultWeightedGraph;
import graph.WeightedGraph;
import junit.framework.TestCase;

/**
 * Unit tests of weighted graphs.
 * @author Paul Chaignon
 */
public class TestWeightedGraph extends TestCase {
	private WeightedGraph graph1;
	private WeightedGraph graph2;
	private WeightedGraph graph3;
	private WeightedGraph graph4;
	
	/**
	 * Initialize a few weighted graphs.
	 */
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
		assertFalse(this.graph1.equals(this.graph4));
	}
	
	/**
	 * Test the clone method.
	 */
	public void testClone() {
		assertEquals(this.graph1, this.graph1.clone());
		assertEquals(this.graph3, this.graph3.clone());
		assertEquals(this.graph4, this.graph4.clone());
	}
}