package graph;

/**
 * Represent a weighted directed edge.
 * @author Paul Chaignon
 */
public interface WeightedEdge extends DirectedEdge {

	/**
	 * @return The edge's value.
	 */
	public double getValue();
}