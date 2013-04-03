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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (x+y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DefaultEdge other = (DefaultEdge) obj;
		if (x!=other.x && x!=other.y) {
			return false;
		}
		if (y!=other.y && y!=other.x) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
}