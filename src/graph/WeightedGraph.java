package graph;

public interface WeightedGraph extends DirectedGraph {
	
	public boolean addEdge(int x, int y, double value);
}