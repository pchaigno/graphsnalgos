package graph;

import java.util.Set;

public class DefaultWeightedGraph extends DefaultDirectedGraph implements WeightedGraph {
	protected Set<WeightedEdge> edges;
	
	@Override
	public boolean addEdge(int x, int y) {
		return this.addEdge(new DefaultWeightedEdge(x, y));
	}

	@Override
	public boolean addEdge(int x, int y, double value) {
		return this.edges.add(new DefaultWeightedEdge(x, y, value));
	}
	
	@Override
	public DefaultWeightedGraph clone() {
		return (DefaultWeightedGraph)super.clone();
	}
}