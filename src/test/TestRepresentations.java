package test;

import graph.DefaultDirectedGraph;

import java.util.List;
import java.util.Map;

import main.Tools;
import junit.framework.TestCase;

/**
 * Unit tests of the methods from Representations.
 * @author Paul Chaignon
 */
public class TestRepresentations extends TestCase {
	private DefaultDirectedGraph graph;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	@Override
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
		for(int i=0; i<adjacencyMatrix.length; i++) {
			assertEquals(adjacencyMatrix.length, adjacencyMatrix[i].length);
		}
		int sums[] = {1, 3, 1, 1, 2, 0, 1, 1};
		int sum;
		for(int i=0; i<adjacencyMatrix.length; i++) {
			sum = 0;
			for(int j=0; j<adjacencyMatrix.length; j++) {
				sum += adjacencyMatrix[i][j];
			}
			assertEquals(sums[i], sum);
		}
	}
	
	/**
	 *  Test the getSourcesLists method.
	 */
	public void testSourcesLists() {
		Map<Integer, List<Integer>> sources = this.graph.getSourcesLists();
		System.out.println("Sources lists:");
		System.out.println(sources);
		DefaultDirectedGraph graphBis = new DefaultDirectedGraph();
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
		DefaultDirectedGraph graphBis = new DefaultDirectedGraph(); 
		graphBis.buildGraphFromTargetsLists(targets);
		assertTrue(this.graph.equals(graphBis));
	}
}
