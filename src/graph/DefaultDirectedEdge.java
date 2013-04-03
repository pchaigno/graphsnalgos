package graph;

public class DefaultDirectedEdge extends DefaultEdge implements DirectedEdge {

	public DefaultDirectedEdge(int x, int y) {
		super(x, y);
	}
	
	@Override
	public DefaultDirectedEdge clone() {
		return (DefaultDirectedEdge)super.clone();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		DefaultDirectedEdge other = (DefaultDirectedEdge) obj;
		if (x!=other.x) {
			return false;
		}
		if (y!=other.y) {
			return false;
		}
		return true;
	}
}