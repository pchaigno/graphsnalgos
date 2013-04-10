package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The default implementation for a Graph.
 * @author Paul Chaignon
 */
public class DefaultGraph extends AbstractGraph<DefaultEdge> implements UndirectedGraph<DefaultEdge> {

	@Override
    public DefaultGraph clone() {
        DefaultGraph graph = new DefaultGraph();
        graph.vertices.addAll(this.vertices);
        graph.edges.addAll(this.edges);
        return graph;
    }
	
	@Override
	public Graph<DefaultEdge> edgesGraph() {
        // Couple numbers to the edges and build the vertices of the edges graph:
		Graph<DefaultEdge> edgesGraph = this.graphFactory.build();
        Map<Integer, Integer[]> edges = new HashMap<Integer, Integer[]>();
        Set<DefaultEdge> edgesAdded = new HashSet<DefaultEdge>();
        DefaultEdge edge;
        int num = 1;
        for(int vertexX: this.getVertices()) {
            for(int vertexY: this.getVertices()) {
            	edge = new DefaultEdge(vertexX, vertexY);
                if(this.containsEdge(edge) && !edgesAdded.contains(edge)) {
                	edgesAdded.add(edge);
                    edges.put(num, new Integer[] {vertexX, vertexY});
                    edgesGraph.addVertex(num);
                    num++;
                }
            }
        }
        
        // Link the vertices:
        Integer[] edgeX, edgeY;
        for(int vertexX: edges.keySet()) {
            edgeX = edges.get(vertexX);
            for(int vertexY: edges.keySet()) {
                if(vertexX!=vertexY) {
                    edgeY = edges.get(vertexY);
                    if(edgeX[0].equals(edgeY[0]) || edgeX[0].equals(edgeY[1]) || edgeX[1].equals(edgeY[0]) || edgeX[1].equals(edgeY[1])) {
                        if(!edgesGraph.containsEdge(vertexY, vertexX)) {
                            edgesGraph.addEdge(vertexX, vertexY);
                        }
                    }
                }
            }
        }
        
        return edgesGraph;
	}

	@Override
	protected void checkEdge(DefaultEdge edge) {
		if(edge.getClass()!=DefaultEdge.class) {
			throw new IllegalArgumentException("The edge must be a of class DefaultEdge.");
		}
	}
}