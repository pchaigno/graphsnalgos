package graph;

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
	public DefaultWeightedGraph clone() {
		return (DefaultWeightedGraph)super.clone();
	}
}