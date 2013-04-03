package test;

import main.Operations;
import main.Properties;
import main.Tools;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Properties.
 * @author Paul Chaignon
 */
public class TestProperties extends TestCase {
	private Graph<Integer, DefaultEdge> graph1;
	private Graph<Integer, DefaultEdge> graph2;
	private Graph<Integer, DefaultEdge> graph3;
	private Graph<Integer, DefaultEdge> graph4;
	private Graph<Integer, DefaultEdge> graph5;
	private Graph<Integer, DefaultEdge> graph6;
	private Graph<Integer, DefaultEdge> graph7;
	private Graph<Integer, DefaultEdge> graph8;
	private Graph<Integer, DefaultEdge> graph9;
	private Graph<Integer, DefaultEdge> graph10;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() {
		this.graph1 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph2 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph4 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph5 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph8 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph10 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		Tools.addVertices(this.graph1, 5);
		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(2, 3);
		this.graph1.addEdge(2, 4);
		this.graph1.addEdge(3, 2);
		this.graph1.addEdge(5, 5);
		this.graph1.addEdge(1, 1);
		this.graph1.addEdge(2, 2);
		this.graph1.addEdge(3, 3);
		this.graph1.addEdge(4, 4);
		
		Tools.addVertices(this.graph2, 5);
		this.graph2.addEdge(1, 3);
		this.graph2.addEdge(2, 1);
		this.graph2.addEdge(2, 3);
		this.graph2.addEdge(3, 4);
		this.graph2.addEdge(4, 5);
		this.graph2.addEdge(5, 2);
		
		this.graph3 = Tools.clone(this.graph2);
		this.graph3.addEdge(3, 3);
		this.graph3.addEdge(2, 2);
		
		Tools.addVertices(this.graph4, 3);
		this.graph4.addEdge(1, 1);
		this.graph4.addEdge(2, 3);
		this.graph4.addEdge(3, 2);
		
		Tools.addVertices(this.graph5, 3);
		this.graph5.addEdge(1, 1);
		this.graph5.addEdge(1, 2);
		this.graph5.addEdge(1, 3);
		this.graph5.addEdge(2, 3);
	
		this.graph6 = Operations.union(this.graph4, this.graph5);
		
		this.graph7 = Tools.clone(this.graph6);
		this.graph7.addEdge(2, 2);
		this.graph7.addEdge(3, 3);
		
		Tools.addVertices(this.graph8, 3);
		this.graph8.addEdge(1, 2);
		this.graph8.addEdge(2, 3);
		this.graph8.addEdge(3, 2);

		this.graph9 = Tools.clone(this.graph8);
		this.graph9.addEdge(1, 1);
		this.graph9.addEdge(3, 3);
		
		Tools.addVertices(this.graph10, 4);
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
