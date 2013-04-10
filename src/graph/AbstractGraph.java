package graph;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractGraph<T extends Edge> implements Graph<T> {
	protected Set<Integer> vertices;
	protected EdgeFactory<T> edgeFactory;
	protected GraphFactory<T> graphFactory;
	protected Set<T> edges;
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public AbstractGraph() {
		this.vertices = new HashSet<Integer>();
		this.edges = new HashSet<T>();
        ParameterizedType superclass = (ParameterizedType)getClass().getGenericSuperclass();
		try {
			this.edgeFactory = new EdgeFactory<T>((Class<T>)superclass.getActualTypeArguments()[0]);
			this.graphFactory = new GraphFactory<T>(this.getClass());
		} catch (InstantiationException e) {
			// Not possible normally since T extends Edge.
		}
	}
	
	@Override
	public Set<Integer> getVertices() {
		return this.vertices;
	}

	@Override
	public Set<T> getEdges() {
		return this.edges;
	}
	
	protected abstract void checkEdge(T edge);

	@Override
	public boolean containsVertex(int vertex) {
		return this.vertices.contains(vertex);
	}

	@Override
	public boolean containsEdge(T edge) {
		this.checkEdge(edge);
		return this.edges.contains(edge);
	}

	@Override
	public boolean containsEdge(int x, int y) {
		return this.edges.contains(this.edgeFactory.build(x, y));
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
	public boolean addEdge(T edge) {
		this.checkEdge(edge);
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
	    return this.edges.add(this.edgeFactory.build(x, y));
    }
	
	@Override
	public abstract AbstractGraph<T> clone();

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

	@SuppressWarnings("unchecked")
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
		AbstractGraph<T> other = (AbstractGraph<T>) obj;
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
		return this.edges.remove(this.edgeFactory.build(x, y));
    }

	@Override
	public boolean removeEdge(T edge) {
		this.checkEdge(edge);
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

	@Override
    public Graph<T> union(Graph<T> graph) {
	    if(!this.getVertices().equals(graph.getVertices())) {
            throw new IllegalArgumentException("The two graphs must have the same vertices.");
	    }
	    Graph<T> union = this.graphFactory.build();
	    for(int vertex: this.getVertices()) {
            union.addVertex(vertex);
	    }
	    for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
            	T edge = this.edgeFactory.build(vertexX, vertexY);
                if(this.containsEdge(edge) || graph.containsEdge(edge)) {
                    union.addEdge(edge);
                }
            }
	    }
	    return union;
    }

	@Override
    public Graph<T> composition(Graph<T> graph) {
	    if(!this.getVertices().equals(graph.getVertices())) {
            throw new IllegalArgumentException("The two graphs must have the same vertices.");
	    }
	    Graph<T> composition = this.graphFactory.build();
	    for(int vertex: this.getVertices()) {
            composition.addVertex(vertex);
	    }
	    for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
                for(int vertexZ: this.getVertices()) {
                    if(graph.containsEdge(vertexX, vertexZ) && this.containsEdge(vertexZ, vertexY)) {
                        composition.addEdge(vertexX, vertexY);
                    }
                }
            }
	    }
	    return composition;
    }

	@Override
	public Graph<T> power(int p) {
		if(p==1) {
			return (Graph<T>)this.clone();
		}
		return this.composition(this.power(p-1));
	}

	@Override
    public Graph<T> transpose() {
        Graph<T> transposed = this.graphFactory.build();
        for(int vertex: this.getVertices()) {
            transposed.addVertex(vertex);
        }
        for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
                if(this.containsEdge(vertexX, vertexY)) {
                    transposed.addEdge(vertexY, vertexX);
                }
            }
        }
        return transposed;
    }

	@Override
    public Graph<T> complementary() {
        Graph<T> complementary = this.graphFactory.build();
        for(int vertex: this.getVertices()) {
            complementary.addVertex(vertex);
        }
        for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
                if(!this.containsEdge(vertexX, vertexY)) {
                    complementary.addEdge(vertexX, vertexY);
                }
            }
        }
        return complementary;
    }

	@Override
    public Graph<T> complementaryWithoutLoops() {
        Graph<T> complementary = this.graphFactory.build();
        for(int vertex: this.getVertices()) {
            complementary.addVertex(vertex);
        }
        for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
                if(vertexX!=vertexY && !this.containsEdge(vertexX, vertexY)) {
                    complementary.addEdge(vertexX, vertexY);
                }
            }
        }
        return complementary;
    }

	@Override
	public boolean isPartialGraph(Graph<T> partialGraph) {
		for(int vertexX: this.getVertices()) {
			for(int vertexY: this.getVertices()) {
				if(partialGraph.containsEdge(vertexX, vertexY) && !this.containsEdge(vertexX, vertexY)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
    public Graph<T> subgraphFrom(Set<Integer> a) {
        Graph<T> subgraph = this.graphFactory.build();
        for(int vertex: a) {
            subgraph.addVertex(vertex);
        }
        for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
                if(a.contains(vertexX) && a.contains(vertexY) && this.containsEdge(vertexX, vertexY)) {
                        subgraph.addEdge(vertexX, vertexY);
                }
            }
        }
        return subgraph;
	}
	
	@Override
	public Graph<T> subgraphWithout(Set<Integer> a) {
        Graph<T> subgraph = this.graphFactory.build();
        for(int vertex: this.getVertices()) {
            if(!a.contains(vertex)) {
                subgraph.addVertex(vertex);
            }
        }
        for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
                if(!a.contains(vertexX) && !a.contains(vertexY) && this.containsEdge(vertexX, vertexY)) {
                    subgraph.addEdge(vertexX, vertexY);
                }
            }
        }
        return subgraph;
	}

	@Override
	public boolean isReflexive() {
		for(int vertex: this.getVertices()) {
			if(!this.containsEdge(vertex, vertex)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isAntiReflexive() {
		for(int vertex: this.getVertices()) {
			if(this.containsEdge(vertex, vertex)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isSymetric() {
		for(int vertexX: this.getVertices()) {
			for(int vertexY: this.getVertices()) {
				if(this.containsEdge(vertexX, vertexY)) {
					if(vertexX!=vertexY && !this.containsEdge(vertexY, vertexX)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isAntiSymetric() {
		for(int vertexX: this.getVertices()) {
			for(int vertexY: this.getVertices()) {
				if(this.containsEdge(vertexX, vertexY)) {
					if(vertexX!=vertexY && this.containsEdge(vertexY, vertexX)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isTransitive() {
		for(int vertexX: this.getVertices()) {
			for(int vertexY: this.getVertices()) {
				for(int vertexZ: this.getVertices()) {
					if(this.containsEdge(vertexX, vertexY) && this.containsEdge(vertexY, vertexZ)) {
						if(!this.containsEdge(vertexX, vertexZ)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isAntiTransitive() {
		for(int vertexX: this.getVertices()) {
			for(int vertexY: this.getVertices()) {
				for(int vertexZ: this.getVertices()) {
					if(this.containsEdge(vertexX, vertexY) && this.containsEdge(vertexY, vertexZ)) {
						if(this.containsEdge(vertexX, vertexZ)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isStronglyAntiTransitive() {
		// TODO No idea on how to check this...
		return true;
	}
}
