package graph;

/**
 * Default implementation for WeightedGraph.
 * @author Paul Chaignon
 */
public class DefaultWeightedGraph extends AbstractGraph<DefaultWeightedEdge> implements WeightedGraph<DefaultWeightedEdge> {

	@Override
	public boolean addEdge(int x, int y, double value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public DefaultWeightedGraph clone() {
		DefaultWeightedGraph graph = new DefaultWeightedGraph();
        graph.vertices.addAll(this.vertices);
        graph.edges.addAll(this.edges);
        return graph;
    }
}