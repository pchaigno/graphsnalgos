package test;

import graph.DefaultWeightedEdge;
import graph.DefaultWeightedGraph;
import graph.WeightedGraph;
import junit.framework.TestCase;

public class TestWeightedGraph extends TestCase {
	private WeightedGraph graph1;
	private WeightedGraph graph2;
	private WeightedGraph graph3;
	private WeightedGraph graph4;
	
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
	
	public void testEquals() {
		assertEquals(this.graph1, this.graph2);
		assertFalse(new DefaultWeightedEdge(2, 1, 5).equals(new DefaultWeightedEdge(1, 2, 5)));
		System.out.println(this.graph1);
		System.out.println(this.graph2);
		System.out.println(this.graph3);
		System.out.println(this.graph4);
		assertFalse(this.graph1.equals(this.graph3));
		assertFalse(this.graph1.equals(this.graph4));
	}
	
	public void testClone() {
		assertEquals(this.graph1, this.graph1.clone());
		assertEquals(this.graph3, this.graph3.clone());
		assertEquals(this.graph4, this.graph4.clone());
	}
}