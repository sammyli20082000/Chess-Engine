package Executable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import Executable.BoardModel.Point;
import Executable.PieceModel.Piece;

public class Node implements Serializable {
	State state;
	String causingMove;
	int movedFromPointId;
	int movedToPointId;
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

	public Node(Map<Integer, Integer> s, String side, int fromPointId, int toPointointId, String move, Node parentNode) {
		state = new State(s, side);
		movedFromPointId = fromPointId;
		movedToPointId = toPointointId;
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
