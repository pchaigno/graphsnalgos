package test;

import java.util.List;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import main.Representations;
import main.Tools;
import junit.framework.TestCase;

/**
 * Unit tests of the methods from Representations.
 * @author Paul Chaignon
 */
public class TestRepresentations extends TestCase {
	private DirectedGraph<Integer, DefaultEdge> graph;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() throws Exception {
		this.graph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		this.graph.addVertex(1);
		this.graph.addVertex(2);
		this.graph.addVertex(3);
		this.graph.addVertex(4);
		this.graph.addVertex(5);
		this.graph.addVertex(6);
		this.graph.addVertex(7);
		this.graph.addVertex(8);

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
		int[][] adjacencyMatrix = Representations.getAdjacencyMatrix(this.graph);
		System.out.println(Tools.matrixToString(adjacencyMatrix));
	}
	
	/**
	 *  Test the getSourcesLists method.
	 */
	public void testSourcesLists() {
		Map<Integer, List<Integer>> sources = Representations.getSourcesLists(this.graph);
		System.out.println("Sources lists:");
		System.out.println(sources);
		Graph<Integer,DefaultEdge> graphBis = Representations.getGraphFromSourcesLists(sources);
		assertTrue(Tools.graphEquals(this.graph, graphBis));
	}
	
	/**
	 * Test the getTargetsLists method.
	 */
	public void testTargetsLists() {
		Map<Integer, List<Integer>> targets = Representations.getTargetsLists(this.graph);
		System.out.println("Targets lists:");
		System.out.println(targets);
		Graph<Integer,DefaultEdge> graphBis = Representations.getGraphFromTargetsLists(targets);
		assertTrue(Tools.graphEquals(this.graph, graphBis));
	}
}
