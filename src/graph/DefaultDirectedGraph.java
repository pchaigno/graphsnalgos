package graph;

import java.util.Set;

public class DefaultDirectedGraph extends DefaultGraph implements DirectedGraph {
	protected Set<DirectedEdge> edges;

	@Override
	public boolean addEdge(int x, int y) {
		return this.edges.add(new DefaultDirectedEdge(x, y));
	}
	
	@Override
	public DefaultDirectedGraph clone() {
		return (DefaultDirectedGraph)super.clone();
	}
}