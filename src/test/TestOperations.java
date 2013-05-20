package test;

import graph.DefaultDirectedGraph;
import graph.DefaultGraph;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Operations.
 * @author Paul Chaignon
 */
public class TestOperations extends TestCase {
	private DefaultDirectedGraph graph1;
	private DefaultDirectedGraph graph2;
	private DefaultGraph graph3;

	/**
	 * Initalize the tests with the graph from the handout.
	 */
	@Override
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph();
		this.graph2 = new DefaultDirectedGraph();
		this.graph3 = new DefaultGraph();

		this.graph1.addVertices(5);
		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(2, 3);
		this.graph1.addEdge(2, 4);
		this.graph1.addEdge(3, 2);
		this.graph1.addEdge(5, 5);
		
		this.graph2.addVertices(5);
		this.graph2.addEdge(1, 3);
		this.graph2.addEdge(2, 1);
		this.graph2.addEdge(2, 3);
		this.graph2.addEdge(3, 4);
		this.graph2.addEdge(4, 5);
		this.graph2.addEdge(5, 2);
		
		this.graph3.addVertices(4);
		this.graph3.addEdge(1, 3);
		this.graph3.addEdge(2, 3);
		this.graph3.addEdge(3, 4);
		this.graph3.addEdge(4, 2);
	}
	
	/**
	 * Test the union method.
	 */
	public void testUnion() {
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.union(this.graph2);
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 2);
		expected.addEdge(1, 3);
		expected.addEdge(2, 1);
		expected.addEdge(2, 3);
		expected.addEdge(2, 4);
		expected.addEdge(3, 2);
		expected.addEdge(3, 4);
		expected.addEdge(4, 5);
		expected.addEdge(5, 2);
		expected.addEdge(5, 5);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the composition method.
	 */
	public void testComposition() {
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.composition(this.graph2);
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 2);
		expected.addEdge(2, 2);
		expected.addEdge(4, 5);
		expected.addEdge(5, 3);
		expected.addEdge(5, 4);
		assertEquals(expected, graph);
		
		graph = (DefaultDirectedGraph)this.graph2.composition(this.graph1);
		expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 1);
		expected.addEdge(1, 3);
		expected.addEdge(2, 4);
		expected.addEdge(2, 5);
		expected.addEdge(3, 1);
		expected.addEdge(3, 3);
		expected.addEdge(5, 2);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the power method.
	 */
	public void testPower() {
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.power(2);
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 3);
		expected.addEdge(1, 4);
		expected.addEdge(2, 2);
		expected.addEdge(3, 3);
		expected.addEdge(3, 4);
		expected.addEdge(5, 5);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the transpose method.
	 */
	public void testTransposed() {
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.transpose();
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(2, 1);
		expected.addEdge(3, 2);
		expected.addEdge(4, 2);
		expected.addEdge(2, 3);
		expected.addEdge(5, 5);
		assertEquals(expected, graph);
		
		graph = (DefaultDirectedGraph)this.graph2.transpose();
		expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 2);
		expected.addEdge(3, 1);
		expected.addEdge(3, 2);
		expected.addEdge(4, 3);
		expected.addEdge(5, 4);
		expected.addEdge(2, 5);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the complementary method.
	 */
	public void testComplementary() {
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.complementary();
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 1);
		expected.addEdge(1, 3);
		expected.addEdge(1, 4);
		expected.addEdge(1, 5);
		expected.addEdge(2, 2);
		expected.addEdge(2, 1);
		expected.addEdge(2, 5);
		expected.addEdge(3, 3);
		expected.addEdge(3, 1);
		expected.addEdge(3, 4);
		expected.addEdge(3, 5);
		expected.addEdge(4, 4);
		expected.addEdge(4, 1);
		expected.addEdge(4, 2);
		expected.addEdge(4, 3);
		expected.addEdge(4, 5);
		expected.addEdge(5, 1);
		expected.addEdge(5, 2);
		expected.addEdge(5, 3);
		expected.addEdge(5, 4);
		assertEquals(expected, graph);
		
		graph = (DefaultDirectedGraph)this.graph2.complementary();
		expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 1);
		expected.addEdge(1, 2);
		expected.addEdge(1, 4);
		expected.addEdge(1, 5);
		expected.addEdge(2, 2);
		expected.addEdge(2, 4);
		expected.addEdge(2, 5);
		expected.addEdge(3, 3);
		expected.addEdge(3, 1);
		expected.addEdge(3, 2);
		expected.addEdge(3, 5);
		expected.addEdge(4, 4);
		expected.addEdge(4, 3);
		expected.addEdge(4, 1);
		expected.addEdge(4, 2);
		expected.addEdge(5, 5);
		expected.addEdge(5, 4);
		expected.addEdge(5, 1);
		expected.addEdge(5, 3);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the complementaryWithoutLoops method.
	 */
	public void testComplementaryWithoutLoops() {
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.complementaryWithoutLoops();
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 3);
		expected.addEdge(1, 4);
		expected.addEdge(1, 5);
		expected.addEdge(2, 1);
		expected.addEdge(2, 5);
		expected.addEdge(3, 1);
		expected.addEdge(3, 4);
		expected.addEdge(3, 5);
		expected.addEdge(4, 1);
		expected.addEdge(4, 2);
		expected.addEdge(4, 3);
		expected.addEdge(4, 5);
		expected.addEdge(5, 1);
		expected.addEdge(5, 2);
		expected.addEdge(5, 3);
		expected.addEdge(5, 4);
		assertEquals(expected, graph);
		
		graph = (DefaultDirectedGraph)this.graph2.complementaryWithoutLoops();
		expected = new DefaultDirectedGraph();
		expected.addVertices(5);
		expected.addEdge(1, 2);
		expected.addEdge(1, 4);
		expected.addEdge(1, 5);
		expected.addEdge(2, 4);
		expected.addEdge(2, 5);
		expected.addEdge(3, 1);
		expected.addEdge(3, 2);
		expected.addEdge(3, 5);
		expected.addEdge(4, 3);
		expected.addEdge(4, 1);
		expected.addEdge(4, 2);
		expected.addEdge(5, 4);
		expected.addEdge(5, 1);
		expected.addEdge(5, 3);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the isPartialGraph method.
	 */
	public void testIsPartialGraph() {
		// TODO
	}
	
	/**
	 * Test the subgraphFrom method.
	 */
	public void testSubgraphFrom() {
		Set<Integer> a = new HashSet<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		DefaultDirectedGraph graph = (DefaultDirectedGraph)this.graph1.subgraphFrom(a);
		DefaultDirectedGraph expected = new DefaultDirectedGraph();
		expected.addVertices(3);
		expected.addEdge(1, 2);
		expected.addEdge(2, 3);
		expected.addEdge(3, 2);
		assertEquals(expected, graph);
		
		graph = (DefaultDirectedGraph)this.graph2.subgraphFrom(a);
		expected = new DefaultDirectedGraph();
		expected.addVertices(3);
		expected.addEdge(1, 3);
		expected.addEdge(2, 1);
		expected.addEdge(2, 3);
		assertEquals(expected, graph);
	}
	
	/**
	 * Test the edgesGraph method.
	 */
	public void testEdgesGraph() {
		DefaultGraph edgesGraph = (DefaultGraph)this.graph3.edgesGraph();
		DefaultGraph expected = new DefaultGraph();
		expected.addVertices(4);
		expected.addVertex(1);
		expected.addVertex(2);
		expected.addVertex(3);
		expected.addVertex(4);
		expected.addEdge(1, 4);
		expected.addEdge(1, 2);
		expected.addEdge(2, 3);
		expected.addEdge(2, 4);
		expected.addEdge(3, 4);
		assertEquals(expected, edgesGraph);
	}
}