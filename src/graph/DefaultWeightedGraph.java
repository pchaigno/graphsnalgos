package graph;

import java.util.Set;

/**
 * Default implementation for WeightedGraph.
 * @author Paul Chaignon
 */
public class DefaultWeightedGraph extends AbstractGraph<WeightedEdge> implements WeightedGraph {

	@Override
	public boolean addEdge(int x, int y, double value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DefaultWeightedGraph clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeEdge(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DefaultWeightedGraph transpose() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph complementary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph complementaryWithoutLoops() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph subgraphFrom(Set<Integer> a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph subgraphWithout(Set<Integer> a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph edgesGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph union(Graph<WeightedEdge> graph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultWeightedGraph composition(Graph<WeightedEdge> graph) {
		// TODO Auto-generated method stub
		return null;
	}
}