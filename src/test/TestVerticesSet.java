package test;

import java.util.HashSet;
import java.util.Set;

import main.VerticesSet;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

/**
 * Unit tests of the methods from VerticesSet.
 * @author Paul Chaignon
 */
public class TestVerticesSet extends TestCase {
	private Graph<Integer, DefaultEdge> graph1;
	private Graph<Integer, DefaultEdge> graph2;
	
	@Override
	protected void setUp() {
		this.graph1 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		this.graph2 = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		this.graph1.addVertex(1);
		this.graph1.addVertex(2);
		this.graph1.addVertex(3);
		this.graph1.addVertex(4);

		this.graph1.addEdge(1, 2);
		this.graph1.addEdge(2, 3);
		this.graph1.addEdge(3, 4);
		this.graph1.addEdge(4, 2);

		this.graph2.addVertex(1);
		this.graph2.addVertex(2);
		this.graph2.addVertex(3);
		this.graph2.addVertex(4);
		this.graph2.addVertex(5);
		this.graph2.addVertex(6);
		
		this.graph2.addEdge(1, 6);
		this.graph2.addEdge(2, 1);
		this.graph2.addEdge(2, 3);
		this.graph2.addEdge(3, 4);
		this.graph2.addEdge(4, 6);
		this.graph2.addEdge(5, 4);
		this.graph2.addEdge(6, 2);
		this.graph2.addEdge(6, 3);
		this.graph2.addEdge(6, 5);
	}
	
	/**
	 * Test the isIndependentSet method.
	 */
	public void testIsIndependentSet() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(3);
		assertTrue(VerticesSet.isIndependentSet(this.graph1, set));
		set.add(2);
		assertFalse(VerticesSet.isIndependentSet(this.graph1, set));
	}
	
	/**
	 * Test the isMaximalIndependentSet method.
	 */
	public void testIsMaximalIndependentSet() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		assertFalse(VerticesSet.isMaximalIndependentSet(this.graph1, set));
		set.add(3);
		assertTrue(VerticesSet.isMaximalIndependentSet(this.graph1, set));
	}
	
	/**
	 * Test the isClique method.
	 */
	public void testIsClique() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(2);
		set.add(3);
		set.add(4);
		assertTrue(VerticesSet.isClique(this.graph1, set));
		set.add(1);
		set.remove(3);
		assertFalse(VerticesSet.isClique(this.graph1, set));
	}
	
	/**
	 * Test the isMaximalClique method.
	 */
	public void testIsMaximalClique() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(2);
		set.add(3);
		assertFalse(VerticesSet.isMaximalClique(this.graph1, set));
		set.add(4);
		assertTrue(VerticesSet.isMaximalClique(this.graph1, set));
	}
}