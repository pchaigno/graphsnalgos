package test;

import graph.DefaultDirectedGraph;
import main.Tree;

import junit.framework.TestCase;

public class TestTree extends TestCase {
	private DefaultDirectedGraph graph1;
	private DefaultDirectedGraph graph2;
	private DefaultDirectedGraph graph3;
	private DefaultDirectedGraph graph4;
	private DefaultDirectedGraph graph5;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph();
		this.graph2 = new DefaultDirectedGraph();
		this.graph3 = new DefaultDirectedGraph();
		this.graph4 = new DefaultDirectedGraph();
		this.graph5 = new DefaultDirectedGraph();
		
		this.graph1.addVertices(6);
		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(1, 3);
		this.graph1.addEdge(1, 4);
		this.graph1.addEdge(1, 6);
		this.graph1.addEdge(2, 4);
		this.graph1.addEdge(6, 4);
		this.graph1.addEdge(4, 5);

		this.graph2.addVertices(4);
		this.graph2.addEdge(1, 2);
		this.graph2.addEdge(2, 3);
		this.graph2.addEdge(2, 4);
		this.graph2.addEdge(4, 3);
		this.graph2.addEdge(3, 1);

		this.graph3.addVertices(3);
		this.graph3.addEdge(2, 1);
		this.graph3.addEdge(3, 1);

		this.graph4.addVertices(8);
		this.graph4.addEdge(2, 1);
		this.graph4.addEdge(2, 4);
		this.graph4.addEdge(3, 1);
		this.graph4.addEdge(3, 6);
		this.graph4.addEdge(4, 5);
		this.graph4.addEdge(4, 7);
		this.graph4.addEdge(6, 8);
		
		this.graph5.addVertices(8);
		this.graph5.addEdge(1, 2);
		this.graph5.addEdge(1, 3);
		this.graph5.addEdge(2, 4);
		this.graph5.addEdge(3, 6);
		this.graph5.addEdge(4, 5);
		this.graph5.addEdge(4, 7);
		this.graph5.addEdge(6, 8);
	}
	
	/**
	 * Test the getRoot method.
	 */
	public void testGetRoot() {
		assertEquals(1, Tree.getRoot(this.graph2));
		assertEquals(1, Tree.getRoot(this.graph1));
		assertEquals(-1, Tree.getRoot(this.graph3));
		assertEquals(-1, Tree.getRoot(this.graph4));
		assertEquals(1, Tree.getRoot(this.graph5));
	}
	
	/**
	 * Test the isRootedTree method.
	 */
	public void testIsRootedTree() {
		assertFalse(Tree.isRootedTree(this.graph4));
		assertTrue(Tree.isRootedTree(this.graph5));
	}
}