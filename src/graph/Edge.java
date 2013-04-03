package graph;

public interface Edge extends Cloneable {
	public static final double DEFAULT_VALUE = 1;
	
	public int getX();
	
	public int getY();
	
	public Object clone();
}