package test;

import java.util.HashSet;
import java.util.Set;

import main.Operations;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Operations.
 * @author Paul Chaignon
 */
public class TestOperations extends TestCase {
	private Graph<Integer, DefaultEdge> graph1;
	private Graph<Integer, DefaultEdge> graph2;
	private Graph<Integer, DefaultEdge> graph3;

	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph1 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph2 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph3 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		this.graph1.addVertex(1);
		this.graph1.addVertex(2);
		this.graph1.addVertex(3);
		this.graph1.addVertex(4);
		this.graph1.addVertex(5);
		
		this.graph2.addVertex(1);
		this.graph2.addVertex(2);
		this.graph2.addVertex(3);
		this.graph2.addVertex(4);
		this.graph2.addVertex(5);
		
		this.graph3.addVertex(1);
		this.graph3.addVertex(2);
		this.graph3.addVertex(3);
		this.graph3.addVertex(4);

		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(2, 3);
		this.graph1.addEdge(2, 4);
		this.graph1.addEdge(3, 2);
		this.graph1.addEdge(5, 5);
		
		this.graph2.addEdge(1, 3);
		this.graph2.addEdge(2, 1);
		this.graph2.addEdge(2, 3);
		this.graph2.addEdge(3, 4);
		this.graph2.addEdge(4, 5);
		this.graph2.addEdge(5, 2);
		
		this.graph3.addEdge(1, 3);
		this.graph3.addEdge(2, 3);
		this.graph3.addEdge(3, 4);
		this.graph3.addEdge(4, 2);
	}
	
	/**
	 * Test the union method.
	 */
	public void testUnion() {
		Graph<Integer, DefaultEdge> graph = Operations.union(this.graph1, this.graph2);
		System.out.println("Union:");
		System.out.println(graph);
	}
	
	/**
	 * Test the composition method.
	 */
	public void testComposition() {
		Graph<Integer, DefaultEdge> graph = Operations.composition(this.graph1, this.graph2);
		System.out.println("Composition g1 o g2:");
		System.out.println(graph);
		graph = Operations.composition(this.graph2, this.graph1);
		System.out.println("Composition g2 o g1:");
		System.out.println(graph);
	}
	
	/**
	 * Test the power method.
	 */
	public void testPower() {
		Graph<Integer, DefaultEdge> graph = Operations.power(this.graph1, 2);
		System.out.println("g1 square:");
		System.out.println(graph);
	}
	
	/**
	 * Test the transpose method.
	 */
	public void testTransposed() {
		Graph<Integer, DefaultEdge> graph = Operations.transpose(this.graph1);
		System.out.println("g1 transposed:");
		System.out.println(graph);
		graph = Operations.transpose(this.graph2);
		System.out.println("g2 transposed:");
		System.out.println(graph);
	}
	
	/**
	 * Test the complementary method.
	 */
	public void testComplementary() {
		Graph<Integer, DefaultEdge> graph = Operations.complementary(this.graph1);
		System.out.println("g1 complementary:");
		System.out.println(graph);
		graph = Operations.complementary(this.graph2);
		System.out.println("g2 complementary:");
		System.out.println(graph);
	}
	
	/**
	 * Test the complementaryWithoutLoops method.
	 */
	public void testComplementaryWithoutLoops() {
		Graph<Integer, DefaultEdge> graph = Operations.complementaryWithoutLoops(this.graph1);
		System.out.println("g1 complementary without loops:");
		System.out.println(graph);
		graph = Operations.complementaryWithoutLoops(this.graph2);
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
		Graph<Integer, DefaultEdge> graph = Operations.subgraphFrom(this.graph1, a);
		System.out.println("g1 subgraph:");
		System.out.println(graph);
		graph = Operations.subgraphFrom(this.graph2, a);
		System.out.println("g2 subgraph:");
		System.out.println(graph);
	}
	
	/**
	 * Test the edgesGraph method.
	 */
	public void testEdgesGraph() {
		Graph<Integer, DefaultEdge> edgesGraph = Operations.edgesGraph(this.graph3);
		System.out.println("g3 edges graph:");
		System.out.println(edgesGraph);
	}
}