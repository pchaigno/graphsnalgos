package graph;

import java.util.HashSet;
import java.util.Set;

public class DefaultGraph implements Graph {
	protected Set<Integer> vertices;
	protected Set<Edge> edges;
	
	public DefaultGraph() {
		this.vertices = new HashSet<Integer>();
		this.edges = new HashSet<Edge>();
	}
	
	@Override
	public Set<Integer> getVertices() {
		return this.vertices;
	}

	@Override
	public Set<Edge> getEdges() {
		return this.edges;
	}

	@Override
	public boolean containsVertex(int vertex) {
		return this.vertices.contains(vertex);
	}

	@Override
	public boolean containsEdge(Edge edge) {
		return this.edges.contains(edge);
	}

	@Override
	public boolean addVertex(int vertex) {
		return this.vertices.add(vertex);
	}

	@Override
	public boolean addEdge(Edge edge) {
		return this.edges.add(edge);
	}

	@Override
	public boolean addEdge(int x, int y) {
		return this.edges.add(new DefaultEdge(x, y));
	}
	
	@Override
	public DefaultGraph clone() {
		DefaultGraph graph = null;
	    try {
	      	graph = (DefaultGraph)super.clone();
	    } catch(CloneNotSupportedException e) {
	      	System.err.println(e.getMessage());
	    }
	    graph.vertices.addAll(this.vertices);
	    graph.edges.addAll(this.edges);
	    return graph;
	}
}