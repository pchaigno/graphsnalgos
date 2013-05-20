package graph;

/**
 * Factory of edges.
 * Build edges of type T.
 * @author Paul Chaignon
 * @param <T> The type of edges built.
 */
public class EdgeFactory<T extends Edge> {
	private Class<? extends Edge> edgeClass;
	
	/**
	 * Constructor
	 * @param edgeClass The class to build.
	 * @throws InstantiationException edgeClass must be a child of Edge that can be instantiate.
	 */
	public EdgeFactory(Class<? extends Edge> edgeClass) throws InstantiationException {
		if(this.edgeClass==DefaultEdge.class || this.edgeClass==DefaultDirectedEdge.class || this.edgeClass==DefaultWeightedEdge.class) {
			throw new InstantiationException("The edge factory must be initiate with a child of edge that can be instantiate.");
		}
		this.edgeClass = edgeClass;
	}

	/**
	 * Build an edge of type T. 
	 * @param x The first vertex.
	 * @param y The second vertex.
	 * @return The edge.
	 */
	@SuppressWarnings("unchecked")
	public T build(int x, int y) {
		if(this.edgeClass==DefaultEdge.class) {
			return (T)new DefaultEdge(x, y);
		} else if(this.edgeClass==DefaultDirectedEdge.class) {
			return (T)new DefaultDirectedEdge(x, y);
		} else {
			return (T)new DefaultWeightedEdge(x, y);
		}
	}

	/**
	 * Build a weighted edge.
	 * @param x The starting vertex.
	 * @param y The end vertex.
	 * @param value The value
	 * @return The weighted edge.
	 */
	public WeightedEdge build(int x, int y, double value) {
		if(this.edgeClass==DefaultWeightedEdge.class) {
			return new DefaultWeightedEdge(x, y, value);
		}
		throw new IllegalArgumentException("To use this method, the factory must have been initiate with a WeightedEdge.");
	}
	
	@Override
	public String toString() {
		return this.edgeClass.toString();
	}
}