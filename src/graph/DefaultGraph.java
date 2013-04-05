package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The default implementation for a Graph.
 * @author Paul Chaignon
 */
public class DefaultGraph implements Graph {
	protected Set<Integer> vertices;
	protected Set<Edge> edges;
	
	/**
	 * Constructor
	 */
	public DefaultGraph() {
		this.vertices = new HashSet<Integer>();
		this.edges = new HashSet<Edge>();
	}
	
	@Override
	public Set<Integer> getVertices() {
		return this.vertices;
	}

	@Override
	public Set<Edge> getEdges() {
		return this.edges;
	}

	@Override
	public boolean containsVertex(int vertex) {
		return this.vertices.contains(vertex);
	}

	@Override
	public boolean containsEdge(Edge edge) {
		return this.edges.contains(edge);
	}

	@Override
	public boolean containsEdge(int x, int y) {
		return this.edges.contains(new DefaultEdge(x, y));
	}

	@Override
	public boolean addVertex(int vertex) {
		return this.vertices.add(vertex);
	}

	@Override
	public void addVertices(int nbVertices) {
		if(this.vertices.size()!=0) {
			throw new IllegalArgumentException();
		}
		for(int i=1 ; i<=nbVertices ; i++) {
			this.addVertex(i);
		}
	}

	@Override
	public boolean addEdge(Edge edge) {
		if(!this.vertices.contains(edge.getX()) || (edge.getX()!=edge.getY() && !this.vertices.contains(edge.getY()))) {
			throw new IllegalArgumentException();
		}
		return this.edges.add(edge);
	}

	@Override
	public boolean addEdge(int x, int y) {
		if(!this.vertices.contains(x) || (x!=y && !this.vertices.contains(y))) {
			throw new IllegalArgumentException();
		}
		return this.edges.add(new DefaultEdge(x, y));
	}
	
	@Override
	public DefaultGraph clone() {
		DefaultGraph graph = null;
	    try {
	      	graph = (DefaultGraph)super.clone();
	    } catch(CloneNotSupportedException e) {
	      	System.err.println(e.getMessage());
	    }
	    graph.vertices.addAll(this.vertices);
	    graph.edges.addAll(this.edges);
	    return graph;
	}

	@Override
	public String toString() {
		return vertices.toString()+"\n"+this.edges.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result
				+ ((vertices == null) ? 0 : vertices.hashCode());
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
		DefaultGraph other = (DefaultGraph) obj;
		if (edges == null) {
			if (other.edges != null) {
				return false;
			}
		} else if (!edges.equals(other.edges)) {
			return false;
		}
		if (vertices == null) {
			if (other.vertices != null) {
				return false;
			}
		} else if (!vertices.equals(other.vertices)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeEdge(int x, int y) {
		return this.edges.remove(new DefaultEdge(x, y));
	}

	@Override
	public boolean removeEdge(Edge edge) {
		return this.edges.remove(edge);
	}

	@Override
	public int[][] getAdjacencyMatrix() {
		Integer[] vertices = this.getVertices().toArray(new Integer[0]);
		int[][] adjacencyMatrix = new int[this.vertices.size()][this.vertices.size()];
		for(int i=0 ; i<this.vertices.size() ; i++) {
			for(int j=0 ; j<this.vertices.size() ; j++) {
				if(this.containsEdge(vertices[i], vertices[j])) {
					adjacencyMatrix[i][j] = 1;
				}
			}
		}
		return adjacencyMatrix;
	}

	@Override
	public Map<Integer, List<Integer>> getTargetsLists() {
		Integer[] vertices = this.getVertices().toArray(new Integer[0]);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer source;
		for(int i=0 ; i<this.vertices.size() ; i++) {
			source = vertices[i];
			map.put(source, new LinkedList<Integer>());
			for(int vertex: vertices) {
				if(this.containsEdge(source, vertex)) {
					map.get(source).add(vertex);
				}
			}
		}
		return map;
	}

	@Override
	public Map<Integer, List<Integer>> getSourcesLists() {
		Integer[] vertices = this.getVertices().toArray(new Integer[0]);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Integer target;
		for(int i=0 ; i<this.vertices.size() ; i++) {
			target = vertices[i];
			map.put(target, new LinkedList<Integer>());
			for(int vertex: vertices) {
				if(this.containsEdge(vertex, target)) {
					map.get(target).add(vertex);
				}
			}
		}
		return map;
	}

	@Override
	public void buildGraphFromAdjacencyMatrix(int[][] matrix, int[] vertices) {
		int length = matrix.length;
		if(length<1 || length!=matrix[0].length || this.vertices.size()!=0) {
			throw new IllegalArgumentException();
		}
		for(int vertex: vertices) {
			this.addVertex(vertex);
		}
		for(int i=0 ; i<length ; i++) {
			for(int j=0 ; j<length ; j++) {
				if(matrix[i][j]==1) {
					this.addEdge(vertices[i], vertices[j]);
				}
			}
		}
	}

	@Override
	public void buildGraphFromTargetsLists(Map<Integer, List<Integer>> targets) {
		for(int source: targets.keySet()) {
			this.addVertex(source);
			for(int target: targets.get(source)) {
				if(!this.containsVertex(target)) {
					this.addVertex(target);
				}
				this.addEdge(source, target);
			}
		}
	}

	@Override
	public void buildGraphFromSourcesLists(Map<Integer, List<Integer>> sources) {
		for(int target: sources.keySet()) {
			this.addVertex(target);
			for(int source: sources.get(target)) {
				if(!this.containsVertex(source)) {
					this.addVertex(source);
				}
				this.addEdge(source, target);
			}
		}
	}
}