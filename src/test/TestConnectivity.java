package test;

import java.util.List;

import main.Connectivity;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from Connectivity.
 * @author Paul Chaignon
 */
public class TestConnectivity extends TestCase {
	Graph<Integer, DefaultEdge> graph1;
	Graph<Integer, DefaultEdge> graph2;
	Graph<Integer, DefaultEdge> graph3;
	Graph<Integer, DefaultEdge> graph4;
	
	/**
	 * Initalize the tests with the graph from the handout.
	 */
	protected void setUp() {
		this.graph1 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph2 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph3 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph4 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		this.graph2.addVertex(1);
		this.graph2.addVertex(2);
		this.graph2.addVertex(3);
		this.graph2.addVertex(4);
		this.graph2.addVertex(5);
		this.graph2.addVertex(6);
		this.graph2.addVertex(7);
		this.graph2.addVertex(8);
		this.graph2.addVertex(9);
		this.graph2.addVertex(10);
		this.graph2.addVertex(11);
		this.graph2.addVertex(12);
		this.graph2.addVertex(13);
		this.graph2.addVertex(14);

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

		this.graph4.addVertex(1);
		this.graph4.addVertex(2);
		this.graph4.addVertex(3);
		this.graph4.addVertex(4);
		this.graph4.addVertex(5);
		this.graph4.addVertex(6);
		this.graph4.addVertex(7);
		this.graph4.addVertex(8);
		this.graph4.addVertex(9);
		this.graph4.addVertex(10);
		this.graph4.addVertex(11);
		this.graph4.addVertex(12);
		this.graph4.addVertex(13);
		this.graph4.addVertex(14);
		this.graph4.addVertex(15);
		this.graph4.addVertex(16);
		this.graph4.addVertex(17);
		this.graph4.addVertex(18);
		
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
		Graph<Integer, DefaultEdge> subgraph = Connectivity.getConnectedComposantByTarjan(this.graph1, 1);
		System.out.println(subgraph);
		List<Graph<Integer, DefaultEdge>> subgraphs = Connectivity.getConnectedComposantsByTarjan(this.graph2);
		System.out.println("Connected Composants By Tarjan:");
		for(Graph<Integer, DefaultEdge> graph: subgraphs) {
			System.out.println(graph);
		}
	}
	
	/**
	 * Test the getStronglyConnectedComposantsByFoulkes method.
	 */
	public void testGetStronglyConnectedComposantByFoulkes() {
		List<Graph<Integer, DefaultEdge>> subgraphs = Connectivity.getStronglyConnectedComposantsByFoulkes(this.graph4);
		System.out.println("Strongly Connected Composants By Foulkes:");
		for(Graph<Integer, DefaultEdge> graph: subgraphs) {
			System.out.println(graph);
		}
	}
}