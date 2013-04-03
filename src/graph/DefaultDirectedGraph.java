package graph;

import java.util.HashSet;
import java.util.Set;

public class DefaultDirectedGraph extends DefaultGraph implements DirectedGraph {
	protected Set<DirectedEdge> edges;
	
	public DefaultDirectedGraph() {
		this.edges = new HashSet<DirectedEdge>();
	}

	@Override
	public boolean addEdge(int x, int y) {
		return this.edges.add(new DefaultDirectedEdge(x, y));
	}
	
	@Override
	public DefaultDirectedGraph clone() {
		return (DefaultDirectedGraph)super.clone();
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
		DefaultDirectedGraph other = (DefaultDirectedGraph) obj;
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