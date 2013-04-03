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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DefaultWeightedEdge other = (DefaultWeightedEdge) obj;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "("+this.x+", "+this.y+", "+this.value+")";
	}
}