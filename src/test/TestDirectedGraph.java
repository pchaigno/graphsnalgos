package test;

import graph.DefaultDirectedGraph;
import graph.DirectedGraph;
import junit.framework.TestCase;

public class TestDirectedGraph extends TestCase {
	private DirectedGraph graph1;
	private DirectedGraph graph2;
	private DirectedGraph graph3;
	
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
	
	public void testEquals() {
		assertEquals(this.graph1, this.graph2);
		assertFalse(this.graph1.equals(this.graph3));
	}
	
	public void testClone() {
		assertEquals(this.graph1, this.graph1.clone());
		assertEquals(this.graph3, this.graph3.clone());
	}
}
