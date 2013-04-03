package graph;

public class DefaultEdge implements Edge {
	protected int x;
	protected int y;
	
	public DefaultEdge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public DefaultEdge clone() {
		DefaultEdge edge = null;
	    try {
	    	edge = (DefaultEdge)super.clone();
	    } catch(CloneNotSupportedException e) {
	      	System.err.println(e.getMessage());
	    }
	    edge.x = this.x;
	    edge.y = this.y;
	    return edge;
	}
}