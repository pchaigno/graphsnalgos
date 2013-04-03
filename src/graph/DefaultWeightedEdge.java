package graph;

public class DefaultWeightedEdge extends DefaultDirectedEdge implements WeightedEdge {
	private double value;
	
	public DefaultWeightedEdge(int x, int y) {
		super(x, y);
		this.value = DEFAULT_VALUE;
	}
	
	public DefaultWeightedEdge(int x, int y, double value) {
		super(x, y);
		this.value = value;
	}
	
	@Override
	public double getValue() {
		return this.value;
	}
	
	@Override
	public DefaultWeightedEdge clone() {
		return (DefaultWeightedEdge)super.clone();
	}
}