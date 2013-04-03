package graph;

/**
 * Represent an undirected edge.
 * @author Paul Chaignon
 */
public interface Edge extends Cloneable {
	public static final double DEFAULT_VALUE = 1;
	
	/**
	 * @return The first vertex.
	 */
	public int getX();
	
	/**
	 * @return The second vertex.
	 */
	public int getY();
	
	public Object clone();
}