package graph;

/**
 * Default implementation for WeightedGraph.
 * @author Paul Chaignon
 */
public class DefaultWeightedGraph extends DefaultDirectedGraph implements WeightedGraph {
	
	@Override
	public boolean addEdge(int x, int y) {
		return this.addEdge(new DefaultWeightedEdge(x, y));
	}

	@Override
	public boolean addEdge(int x, int y, double value) {
		return this.addEdge(new DefaultWeightedEdge(x, y, value));
	}

	@Override
	public boolean removeEdge(int x, int y) {
		return this.edges.remove(new DefaultWeightedEdge(x, y));
	}
	
	@Override
	public DefaultWeightedGraph clone() {
		return (DefaultWeightedGraph)super.clone();
	}

	@Override
	public boolean containsEdge(int x, int y) {
		return this.edges.contains(new DefaultWeightedEdge(x, y));
	}
}