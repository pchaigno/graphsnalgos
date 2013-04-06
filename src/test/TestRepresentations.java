package test;

import graph.DefaultDirectedEdge;
import graph.DefaultDirectedGraph;
import graph.Graph;

import java.util.List;
import java.util.Map;

import main.Tools;
import junit.framework.TestCase;

/**
 * Unit tests of the methods from Representations.
 * @author Paul Chaignon
 */
public class TestRepresentations extends TestCase {
	private Graph<DefaultDirectedEdge> graph;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph = new DefaultDirectedGraph();

		this.graph.addVertices(8);
		this.graph.addEdge(1, 3);
		this.graph.addEdge(2, 1);
		this.graph.addEdge(2, 2);
		this.graph.addEdge(2, 3);
		this.graph.addEdge(3, 1);
		this.graph.addEdge(4, 3);
		this.graph.addEdge(5, 3);
		this.graph.addEdge(5, 4);
		this.graph.addEdge(7, 8);
		this.graph.addEdge(8, 7);
	}
	
	/**
	 * Test the getAdjacencyMatrix method.
	 */
	public void testAdjacencyMatrix() {        
		int[][] adjacencyMatrix = this.graph.getAdjacencyMatrix();
		System.out.println(Tools.matrixToString(adjacencyMatrix));
	}
	
	/**
	 *  Test the getSourcesLists method.
	 */
	public void testSourcesLists() {
		Map<Integer, List<Integer>> sources = this.graph.getSourcesLists();
		System.out.println("Sources lists:");
		System.out.println(sources);
		Graph<DefaultDirectedEdge> graphBis = new DefaultDirectedGraph();
		graphBis.buildGraphFromSourcesLists(sources);
		assertTrue(this.graph.equals(graphBis));
	}
	
	/**
	 * Test the getTargetsLists method.
	 */
	public void testTargetsLists() {
		Map<Integer, List<Integer>> targets = this.graph.getTargetsLists();
		System.out.println("Targets lists:");
		System.out.println(targets);
		Graph<DefaultDirectedEdge> graphBis = new DefaultDirectedGraph(); 
		graphBis.buildGraphFromTargetsLists(targets);
		assertTrue(this.graph.equals(graphBis));
	}
}
