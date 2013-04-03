package graph;

public class DefaultDirectedEdge extends DefaultEdge implements DirectedEdge {

	public DefaultDirectedEdge(int x, int y) {
		super(x, y);
	}
	
	@Override
	public DefaultDirectedEdge clone() {
		return (DefaultDirectedEdge)super.clone();
	}
}