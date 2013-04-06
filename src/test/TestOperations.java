package test;

import graph.DefaultDirectedEdge;
import graph.DefaultDirectedGraph;
import graph.Graph;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Operations.
 * @author Paul Chaignon
 */
public class TestOperations extends TestCase {
	private Graph<DefaultDirectedEdge> graph1;
	private Graph<DefaultDirectedEdge> graph2;
	private Graph<DefaultDirectedEdge> graph3;

	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph();
		this.graph2 = new DefaultDirectedGraph();
		this.graph3 = new DefaultDirectedGraph();

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
		Graph<DefaultDirectedEdge> graph = this.graph1.union(this.graph2);
		System.out.println("Union:");
		System.out.println(graph);
	}
	
	/**
	 * Test the composition method.
	 */
	public void testComposition() {
		Graph<DefaultDirectedEdge> graph = this.graph1.composition(this.graph2);
		System.out.println("Composition g1 o g2:");
		System.out.println(graph);
		graph = this.graph2.composition(this.graph1);
		System.out.println("Composition g2 o g1:");
		System.out.println(graph);
	}
	
	/**
	 * Test the power method.
	 */
	public void testPower() {
		Graph<DefaultDirectedEdge> graph = this.graph1.power(2);
		System.out.println("g1 square:");
		System.out.println(graph);
	}
	
	/**
	 * Test the transpose method.
	 */
	public void testTransposed() {
		Graph<DefaultDirectedEdge> graph = this.graph1.transpose();
		System.out.println("g1 transposed:");
		System.out.println(graph);
		graph = this.graph2.transpose();
		System.out.println("g2 transposed:");
		System.out.println(graph);
	}
	
	/**
	 * Test the complementary method.
	 */
	public void testComplementary() {
		Graph<DefaultDirectedEdge> graph = this.graph1.complementary();
		System.out.println("g1 complementary:");
		System.out.println(graph);
		graph = this.graph2.complementary();
		System.out.println("g2 complementary:");
		System.out.println(graph);
	}
	
	/**
	 * Test the complementaryWithoutLoops method.
	 */
	public void testComplementaryWithoutLoops() {
		Graph<DefaultDirectedEdge> graph = this.graph1.complementaryWithoutLoops();
		System.out.println("g1 complementary without loops:");
		System.out.println(graph);
		graph = this.graph2.complementaryWithoutLoops();
		System.out.println("g2 complementary without loops:");
		System.out.println(graph);
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
		a.add(4);
		Graph<DefaultDirectedEdge> graph = this.graph1.subgraphFrom(a);
		System.out.println("g1 subgraph:");
		System.out.println(graph);
		graph = this.graph2.subgraphFrom(a);
		System.out.println("g2 subgraph:");
		System.out.println(graph);
	}
	
	/**
	 * Test the edgesGraph method.
	 */
	public void testEdgesGraph() {
		Graph<DefaultDirectedEdge> edgesGraph = this.graph3.edgesGraph();
		System.out.println("g3 edges graph:");
		System.out.println(edgesGraph);
	}
}