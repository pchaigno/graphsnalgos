package graph;

/**
 * The default implementation for a Graph.
 * @author Paul Chaignon
 */
public class DefaultGraph extends AbstractGraph<DefaultEdge> {

	@Override
    public DefaultGraph clone() {
        DefaultGraph graph = new DefaultGraph();
        graph.vertices.addAll(this.vertices);
        graph.edges.addAll(this.edges);
        return graph;
    }
}