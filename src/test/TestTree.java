package test;

import main.Tree;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

public class TestTree extends TestCase {
	private Graph<Integer, DefaultEdge> graph1;
	private Graph<Integer, DefaultEdge> graph3;
	private Graph<Integer, DefaultEdge> graph5;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph3 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph5 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		
		this.graph1.addVertex(1);
		this.graph1.addVertex(2);
		this.graph1.addVertex(3);
		this.graph1.addVertex(4);
		this.graph1.addVertex(5);
		this.graph1.addVertex(6);
		
		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(1, 3);
		this.graph1.addEdge(1, 4);
		this.graph1.addEdge(1, 6);
		this.graph1.addEdge(2, 4);
		this.graph1.addEdge(6, 4);
		this.graph1.addEdge(4, 5);

		this.graph3.addVertex(1);
		this.graph3.addVertex(2);
		this.graph3.addVertex(3);
		this.graph3.addVertex(4);

		this.graph3.addEdge(1, 2);
		this.graph3.addEdge(2, 3);
		this.graph3.addEdge(2, 4);
		this.graph3.addEdge(4, 3);
		this.graph3.addEdge(3, 1);

		this.graph5.addVertex(1);
		this.graph5.addVertex(2);
		this.graph5.addVertex(3);

		this.graph5.addEdge(2, 1);
		this.graph5.addEdge(3, 1);
	}
	
	/**
	 * Test the getRoot method.
	 */
	public void testGetRoot() {
		assertEquals(1, Tree.getRoot(this.graph3));
		assertEquals(1, Tree.getRoot(this.graph1));
		assertEquals(-1, Tree.getRoot(this.graph5));
	}
}
