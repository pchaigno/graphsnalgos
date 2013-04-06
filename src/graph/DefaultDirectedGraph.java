package graph;

/**
 * The default implementation for DirectedGraph.
 * @author Paul Chaignon
 */
public class DefaultDirectedGraph extends AbstractGraph<DefaultDirectedEdge> implements DirectedGraph<DefaultDirectedEdge> {

	@Override
    public DefaultDirectedGraph clone() {
		DefaultDirectedGraph graph = new DefaultDirectedGraph();
        graph.vertices.addAll(this.vertices);
        graph.edges.addAll(this.edges);
        return graph;
    }
}