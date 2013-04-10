package graph;

import java.util.Iterator;

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

	@Override
	public double getValue(int x, int y) {
		return this.getValue(new DefaultWeightedEdge(x, y));
	}

	@Override
	public double getValue(DefaultWeightedEdge edge) {
		Iterator<DefaultWeightedEdge> iterator = this.edges.iterator();
		while(iterator.hasNext()) {
			DefaultWeightedEdge e = iterator.next();
			if(edge.equals(e)) {
				return e.getValue();
			}
		}
		throw new IllegalArgumentException("The edge must be in the graph.");
	}

	@Override
	protected void checkEdge(DefaultWeightedEdge edge) {
		if(edge.getClass()!=DefaultWeightedEdge.class) {
			throw new IllegalArgumentException("The edge must be a of class DefaultWeightedEdge.");
		}
	}
}