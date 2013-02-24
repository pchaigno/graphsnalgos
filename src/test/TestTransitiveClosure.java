package test;

import main.TransitiveClosure;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from TransitiveClosure.
 * @author Paul Chaignon
 */
public class TestTransitiveClosure extends TestCase {
	private Graph<Integer, DefaultEdge> graph;
	
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

		this.graph.addEdge(1, 2);
		this.graph.addEdge(2, 3);
		this.graph.addEdge(3, 2);
		this.graph.addEdge(4, 2);
		this.graph.addEdge(5, 5);
	}
	
	/**
	 * Test the getByPowers method.
	 */
	public void testGetByPowers() {
		Graph<Integer, DefaultEdge> closure = TransitiveClosure.getByPowers(this.graph);
		System.out.println("Transitive closure by powers:");
		System.out.println(closure);
	}
	
	/**
	 * Test the getByRoyMarshall method.
	 */
	public void test() {
		Graph<Integer, DefaultEdge> closure = TransitiveClosure.getByRoyMarshall(this.graph);
		System.out.println("Transitive closure by Roy-Marshall:");
		System.out.println(closure);
	}
}
