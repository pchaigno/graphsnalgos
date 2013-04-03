package graph;

import java.util.Set;

public interface Graph extends Cloneable {

	public Set<Integer> getVertices();
	
	public Set<Edge> getEdges();
	
	public boolean containsVertex(int vertex);
	
	public boolean containsEdge(Edge edge);
	
	public boolean addVertex(int vertex);
	
	public boolean addEdge(Edge edge);
	
	public boolean addEdge(int x, int y);
	
	public Object clone();
}