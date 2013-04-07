package graph;

/**
 * Default implementation for WeightedGraph.
 * @author Paul Chaignon
 */
public class DefaultWeightedGraph extends AbstractGraph<DefaultWeightedEdge> implements WeightedGraph<DefaultWeightedEdge> {

	@Override
	public boolean addEdge(int x, int y, double value) {
		if(!this.vertices.contains(x) || (x!=y && !this.vertices.contains(y))) {
	            throw new IllegalArgumentException();
	    }
	    return this.edges.add(new DefaultWeightedEdge(x, y, value));
	}

	@Override
    public DefaultWeightedGraph clone() {
		DefaultWeightedGraph graph = new DefaultWeightedGraph();
        graph.vertices.addAll(this.vertices);
        graph.edges.addAll(this.edges);
        return graph;
    }
}