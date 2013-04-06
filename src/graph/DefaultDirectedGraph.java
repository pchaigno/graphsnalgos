package graph;

/**
 * The default implementation for DirectedGraph.
 * @author Paul Chaignon
 */
public class DefaultDirectedGraph extends DefaultGraph implements DirectedGraph {


	@Override
	public boolean addEdge(int x, int y) {
		if(!this.vertices.contains(x) || (x!=y && !this.vertices.contains(y))) {
			throw new IllegalArgumentException();
		}
		return this.edges.add(new DefaultDirectedEdge(x, y));
	}

	@Override
	public boolean removeEdge(int x, int y) {
		return this.edges.remove(new DefaultDirectedEdge(x, y));
	}
	
	@Override
	public DefaultDirectedGraph clone() {
		return (DefaultDirectedGraph)super.clone();
	}

	@Override
	public boolean containsEdge(int x, int y) {
		return this.edges.contains(new DefaultDirectedEdge(x, y));
	}
}