package test;

import graph.DefaultDirectedGraph;
import graph.Graph;

import java.util.List;

import main.Connectivity;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Connectivity.
 * @author Paul Chaignon
 */
public class TestConnectivity extends TestCase {
	private Graph graph1;
	private Graph graph2;
	private Graph graph3;
	private Graph graph4;
	private Graph graph5;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() {
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


		this.graph2.addVertices(14);
		this.graph2.addEdge(1, 2);
		this.graph2.addEdge(1, 3);
		this.graph2.addEdge(2, 4);
		this.graph2.addEdge(3, 2);
		this.graph2.addEdge(3, 5);
		this.graph2.addEdge(4, 5);
		this.graph2.addEdge(6, 7);
		this.graph2.addEdge(6, 8);
		this.graph2.addEdge(6, 9);
		this.graph2.addEdge(9, 8);
		this.graph2.addEdge(9, 7);
		this.graph2.addEdge(11, 12);
		this.graph2.addEdge(11, 14);
		this.graph2.addEdge(12, 13);
		this.graph2.addEdge(13, 12);
		this.graph2.addEdge(13, 11);

		this.graph3.addVertices(4);
		this.graph3.addEdge(1, 2);
		this.graph3.addEdge(2, 3);
		this.graph3.addEdge(2, 4);
		this.graph3.addEdge(4, 3);
		this.graph3.addEdge(3, 1);


		this.graph4.addVertices(18);
		this.graph4.addEdge(1, 2);
		this.graph4.addEdge(2, 3);
		this.graph4.addEdge(2, 4);
		this.graph4.addEdge(2, 11);
		this.graph4.addEdge(3, 1);
		this.graph4.addEdge(4, 3);
		this.graph4.addEdge(4, 16);
		this.graph4.addEdge(5, 6);
		this.graph4.addEdge(5, 7);
		this.graph4.addEdge(6, 5);
		this.graph4.addEdge(6, 8);
		this.graph4.addEdge(7, 5);
		this.graph4.addEdge(7, 9);
		this.graph4.addEdge(7, 10);
		this.graph4.addEdge(8, 9);
		this.graph4.addEdge(8, 17);
		this.graph4.addEdge(9, 8);
		this.graph4.addEdge(9, 10);
		this.graph4.addEdge(10, 17);
		this.graph4.addEdge(11, 13);
		this.graph4.addEdge(11, 14);
		this.graph4.addEdge(12, 11);
		this.graph4.addEdge(13, 12);
		this.graph4.addEdge(13, 15);
		this.graph4.addEdge(13, 16);
		this.graph4.addEdge(14, 15);
		this.graph4.addEdge(15, 14);
		this.graph4.addEdge(16, 18);
		this.graph4.addEdge(16, 17);
		this.graph4.addEdge(17, 16);
		this.graph4.addEdge(17, 18);
		this.graph4.addEdge(18, 17);


		this.graph5.addVertices(3);
		this.graph5.addEdge(2, 1);
		this.graph5.addEdge(3, 1);
	}
	
	/**
	 * Test the isStronglyConnected method.
	 */
	public void testIsStronglyConnected() {
		assertTrue(Connectivity.isStronglyConnected(this.graph3));
		this.graph3.addVertex(5);
		this.graph3.addEdge(5, 1);
		assertFalse(Connectivity.isStronglyConnected(this.graph3));
	}
	
	/**
	 * Test the getConnectedComposantByTarjan method.
	 */
	public void testGetConnectedComposantByTarjan() {
		Graph subgraph = Connectivity.getConnectedComposantByTarjan(this.graph1, 1);
		System.out.println("Connected composant of 1 by Tarjan:");
		System.out.println(subgraph);
		List<Graph> subgraphs = Connectivity.getConnectedComposantsByTarjan(this.graph2);
		System.out.println("Connected composants by Tarjan:");
		for(Graph graph: subgraphs) {
			System.out.println(graph);
		}
	}
	
	/**
	 * Test the getStronglyConnectedComposantsByFoulkes method.
	 */
	public void testGetStronglyConnectedComposantsByFoulkes() {
		List<Graph> subgraphs = Connectivity.getStronglyConnectedComposantsByFoulkes(this.graph4);
		System.out.println("Strongly connected composants by Foulkes:");
		for(Graph graph: subgraphs) {
			System.out.println(graph);
		}
	}
	
	/**
	 * Test the getStronglyConnectedComposantsByAscendantDescendant method.
	 */
	public void testGetStronglyConnectedComposantsByAscendantDescendant() {
		List<Graph> subgraphs = Connectivity.getStronglyConnectedComposantsByAscendantDescendant(this.graph4);
		System.out.println("Strongly connected composants by ascending-descending:");
		for(Graph graph: subgraphs) {
			System.out.println(graph);
		}
	}
	
	/**
	 * Test the isAlmostStronglyConnected method.
	 */
	public void testIsAlmostStronglyConnected() {
		assertTrue(Connectivity.isAlmostStronglyConnected(this.graph3));
		assertTrue(Connectivity.isAlmostStronglyConnected(this.graph1));
		assertFalse(Connectivity.isAlmostStronglyConnected(this.graph5));
	}
}