package graph;

/**
 * The default implementation for DirectedGraph.
 * @author Paul Chaignon
 */
public class DefaultDirectedGraph extends DefaultGraph implements DirectedGraph {

	@Override
	public boolean addEdge(int x, int y) {
		return this.edges.add(new DefaultDirectedEdge(x, y));
	}
	
	@Override
	public DefaultDirectedGraph clone() {
		return (DefaultDirectedGraph)super.clone();
	}
}