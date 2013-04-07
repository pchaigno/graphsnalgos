package graph;

/**
 * Factory of graphs.
 * @author Paul Chaignon
 *
 */
@SuppressWarnings("rawtypes")
public class GraphFactory<T extends Edge> {
	private Class<? extends Graph> graphClass;
	
	/**
	 * Constructor
	 * @param graphClass The class to build.
	 * @throws InstantiationException graphClass must be a child of Graph that can be instantiate.
	 */
	public GraphFactory(Class<? extends Graph> graphClass) throws InstantiationException {
		if(this.graphClass==DefaultGraph.class || this.graphClass==DefaultDirectedGraph.class || this.graphClass==DefaultWeightedGraph.class) {
			throw new InstantiationException("The graph factory must be initiate with a child of graph that can be instantiate.");
		}
		this.graphClass = graphClass;
	}
	
	/**
	 * Build a graph of type T.
	 * @return The graph.
	 */
	@SuppressWarnings("unchecked")
	public Graph<T> build() {
		if(this.graphClass==DefaultGraph.class) {
			return (Graph<T>)new DefaultGraph();
		} else if(this.graphClass==DefaultDirectedGraph.class) {
			return (Graph<T>)new DefaultDirectedGraph();
		} else {
			return (Graph<T>)new DefaultWeightedGraph();
		}
	}
	
	@Override
	public String toString() {
		return this.graphClass.toString();
	}
}