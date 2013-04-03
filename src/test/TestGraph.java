package test;

import graph.DefaultGraph;
import graph.Graph;
import junit.framework.TestCase;

public class TestGraph extends TestCase {
	private Graph graph1;
	private Graph graph2;
	private Graph graph3;
	
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
		this.graph3.addVertex(3);
		this.graph3.addEdge(1, 2);
		this.graph3.addEdge(2, 3);
		this.graph3.addEdge(3, 1);
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