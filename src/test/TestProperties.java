package test;

import graph.DefaultDirectedGraph;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Properties.
 * @author Paul Chaignon
 */
public class TestProperties extends TestCase {
	private DefaultDirectedGraph graph1;
	private DefaultDirectedGraph graph2;
	private DefaultDirectedGraph graph3;
	private DefaultDirectedGraph graph4;
	private DefaultDirectedGraph graph5;
	private DefaultDirectedGraph graph6;
	private DefaultDirectedGraph graph7;
	private DefaultDirectedGraph graph8;
	private DefaultDirectedGraph graph9;
	private DefaultDirectedGraph graph10;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() {
		this.graph1 = new DefaultDirectedGraph();
		this.graph2 = new DefaultDirectedGraph();
		this.graph4 = new DefaultDirectedGraph();
		this.graph5 = new DefaultDirectedGraph();
		this.graph8 = new DefaultDirectedGraph();
		this.graph10 = new DefaultDirectedGraph();

		this.graph1.addVertices(5);
		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(2, 3);
		this.graph1.addEdge(2, 4);
		this.graph1.addEdge(3, 2);
		this.graph1.addEdge(5, 5);
		this.graph1.addEdge(1, 1);
		this.graph1.addEdge(2, 2);
		this.graph1.addEdge(3, 3);
		this.graph1.addEdge(4, 4);
		
		this.graph2.addVertices(5);
		this.graph2.addEdge(1, 3);
		this.graph2.addEdge(2, 1);
		this.graph2.addEdge(2, 3);
		this.graph2.addEdge(3, 4);
		this.graph2.addEdge(4, 5);
		this.graph2.addEdge(5, 2);
		
		this.graph3 = (DefaultDirectedGraph)this.graph2.clone();
		this.graph3.addEdge(3, 3);
		this.graph3.addEdge(2, 2);
		
		this.graph4.addVertices(3);
		this.graph4.addEdge(1, 1);
		this.graph4.addEdge(2, 3);
		this.graph4.addEdge(3, 2);
		
		this.graph5.addVertices(3);
		this.graph5.addEdge(1, 1);
		this.graph5.addEdge(1, 2);
		this.graph5.addEdge(1, 3);
		this.graph5.addEdge(2, 3);
	
		this.graph6 = (DefaultDirectedGraph)this.graph4.union(this.graph5);
		
		this.graph7 = this.graph6.clone();
		this.graph7.addEdge(2, 2);
		this.graph7.addEdge(3, 3);
		
		this.graph8.addVertices(3);
		this.graph8.addEdge(1, 2);
		this.graph8.addEdge(2, 3);
		this.graph8.addEdge(3, 2);

		this.graph9 = this.graph8.clone();
		this.graph9.addEdge(1, 1);
		this.graph9.addEdge(3, 3);
		
		this.graph10.addVertices(4);
		this.graph10.addEdge(1, 2);
		this.graph10.addEdge(2, 3);
		this.graph10.addEdge(4, 1);
		this.graph10.addEdge(4, 3);
	}
	
	/**
	 * Test the isReflexive method.
	 */
	public void testIsReflexive() {
		assertTrue(this.graph1.isReflexive());
		assertFalse(this.graph2.isReflexive());
		assertFalse(this.graph3.isReflexive());
	}
	
	/**
	 * Test the isAntiReflexive method.
	 */
	public void testIsAntiReflexive() {
		assertFalse(this.graph1.isAntiReflexive());
		assertTrue(this.graph2.isAntiReflexive());
		assertFalse(this.graph3.isAntiReflexive());
	}
	
	/**
	 * Test the isSymetric method.
	 */
	public void testIsSymetric() {
		assertTrue(this.graph4.isSymetric());
		assertFalse(this.graph5.isSymetric());
		assertFalse(this.graph6.isSymetric());
	}
	
	/**
	 * Test the isReflexive method.
	 */
	public void testIsAntiSymetricReflexive() {
		assertFalse(this.graph4.isAntiSymetric());
		assertTrue(this.graph5.isAntiSymetric());
		System.out.println(this.graph4);
		System.out.println(this.graph5);
		System.out.println(this.graph6);
		assertFalse(this.graph6.isAntiSymetric());
	}
	
	/**
	 * Test the isTransitive method.
	 */
	public void testIsTransitive() {
		assertTrue(this.graph7.isTransitive());
		assertFalse(this.graph8.isTransitive());
		assertFalse(this.graph9.isTransitive());
	}
	
	/**
	 * Test the isAntiTransitive method.
	 */
	public void testIsAntiTransitive() {
		assertFalse(this.graph7.isAntiTransitive());
		assertTrue(this.graph8.isAntiTransitive());
		assertFalse(this.graph9.isAntiTransitive());
	}
	
	/**
	 * Test the isStronglyAntiTransitive method.
	 */
	public void testIsStronglyAntiTransitive() {
		fail("Not implemented yet...");
		assertFalse(this.graph10.isStronglyAntiTransitive());
	}
}
