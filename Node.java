import java.util.ArrayList;
import java.util.Map;

import BoardModel.Point;

public class Node {
	State state;
	String causingMove;
	Node parent;
	int depth;
	int pathCost;

	public Node(Map<Integer, Integer> s, String side) {
		state = new State(s, side);
		causingMove = "";
		parent = null;
		depth = 0;
		pathCost = 0;
	}

	public Node(Map<Integer, Integer> s, String side, String move, Node parentNode) {
		state = new State(s, side);
		causingMove = move;
		parent = parentNode;
		depth = parentNode.getDepth() + 1;
		pathCost = parentNode.getPathCost() + calCausingMoveCost();
	}

	public int getDepth() {
		return depth;
	}

	public int calCausingMoveCost() {
		return 1;
	}

	public int getPathCost() {
		return pathCost;
	}

	public State getState() {
		return state;
	}
}
