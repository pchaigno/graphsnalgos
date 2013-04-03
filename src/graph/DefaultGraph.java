package graph;

import java.util.HashSet;
import java.util.Set;

/**
 * The default implementation for a Graph.
 * @author Paul Chaignon
 */
public class DefaultGraph implements Graph {
	protected Set<Integer> vertices;
	protected Set<Edge> edges;
	
	/**
	 * Constructor
	 */
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
	public void addVertices(int nbVertices) {
		if(this.vertices.size()!=0) {
			throw new IllegalArgumentException();
		}
		for(int i=1 ; i<=nbVertices ; i++) {
			this.addVertex(i);
		}
	}

	@Override
	public boolean addEdge(Edge edge) {
		if(!this.vertices.contains(edge.getX()) || (edge.getX()!=edge.getY() && !this.vertices.contains(edge.getY()))) {
			throw new IllegalArgumentException();
		}
		return this.edges.add(edge);
	}

	@Override
	public boolean addEdge(int x, int y) {
		if(!this.vertices.contains(x) || (x!=y && !this.vertices.contains(y))) {
			throw new IllegalArgumentException();
		}
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

	@Override
	public String toString() {
		return vertices.toString()+"\n"+this.edges.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result
				+ ((vertices == null) ? 0 : vertices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DefaultGraph other = (DefaultGraph) obj;
		if (edges == null) {
			if (other.edges != null) {
				return false;
			}
		} else if (!edges.equals(other.edges)) {
			return false;
		}
		if (vertices == null) {
			if (other.vertices != null) {
				return false;
			}
		} else if (!vertices.equals(other.vertices)) {
			return false;
		}
		return true;
	}
}