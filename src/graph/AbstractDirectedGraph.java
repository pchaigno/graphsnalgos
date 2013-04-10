package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractDirectedGraph<T extends DirectedEdge> extends AbstractGraph<T> implements DirectedGraph<T> {

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
	public Set<Integer> getTargets(int vertex) {
		Set<Integer> targets = new HashSet<Integer>();
		for(int v: this.vertices) {
			if(this.containsEdge(vertex, v)) {
				targets.add(v);
			}
		}
		return targets;
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
	public Set<Integer> getSources(int vertex) {
		Set<Integer> sources = new HashSet<Integer>();
		for(int v: this.vertices) {
			if(this.containsEdge(v, vertex)) {
				sources.add(v);
			}
		}
		return sources;
	}
}