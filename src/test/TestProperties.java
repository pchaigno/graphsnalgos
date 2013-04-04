package test;

import graph.DefaultDirectedGraph;
import graph.Graph;
import main.Operations;
import main.Properties;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Properties.
 * @author Paul Chaignon
 */
public class TestProperties extends TestCase {
	private Graph graph1;
	private Graph graph2;
	private Graph graph3;
	private Graph graph4;
	private Graph graph5;
	private Graph graph6;
	private Graph graph7;
	private Graph graph8;
	private Graph graph9;
	private Graph graph10;
	
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
		
		this.graph3 = (Graph)this.graph2.clone();
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
	
		this.graph6 = Operations.union(this.graph4, this.graph5);
		
		this.graph7 = (Graph)this.graph6.clone();
		this.graph7.addEdge(2, 2);
		this.graph7.addEdge(3, 3);
		
		this.graph8.addVertices(3);
		this.graph8.addEdge(1, 2);
		this.graph8.addEdge(2, 3);
		this.graph8.addEdge(3, 2);

		this.graph9 = (Graph)this.graph8.clone();
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
		assertTrue(Properties.isReflexive(this.graph1));
		assertFalse(Properties.isReflexive(this.graph2));
		assertFalse(Properties.isReflexive(this.graph3));
	}
	
	/**
	 * Test the isAntiReflexive method.
	 */
	public void testIsAntiReflexive() {
		assertFalse(Properties.isAntiReflexive(this.graph1));
		assertTrue(Properties.isAntiReflexive(this.graph2));
		assertFalse(Properties.isAntiReflexive(this.graph3));
	}
	
	/**
	 * Test the isSymetric method.
	 */
	public void testIsSymetric() {
		assertTrue(Properties.isSymetric(this.graph4));
		assertFalse(Properties.isSymetric(this.graph5));
		assertFalse(Properties.isSymetric(this.graph6));
	}
	
	/**
	 * Test the isReflexive method.
	 */
	public void testIsAntiSymetricReflexive() {
		assertFalse(Properties.isAntiSymetric(this.graph4));
		assertTrue(Properties.isAntiSymetric(this.graph5));
		assertFalse(Properties.isAntiSymetric(this.graph6));
	}
	
	/**
	 * Test the isTransitive method.
	 */
	public void testIsTransitive() {
		assertTrue(Properties.isTransitive(this.graph7));
		assertFalse(Properties.isTransitive(this.graph8));
		assertFalse(Properties.isTransitive(this.graph9));
	}
	
	/**
	 * Test the isAntiTransitive method.
	 */
	public void testIsAntiTransitive() {
		assertFalse(Properties.isAntiTransitive(this.graph7));
		assertTrue(Properties.isAntiTransitive(this.graph8));
		assertFalse(Properties.isAntiTransitive(this.graph9));
	}
	
	/**
	 * Test the isStronglyAntiTransitive method.
	 */
	public void testIsStronglyAntiTransitive() {
		fail("Not implemented yet...");
		assertFalse(Properties.isStronglyAntiTransitive(this.graph10));
	}
}
