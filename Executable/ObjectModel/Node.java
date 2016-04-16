package Executable.ObjectModel;

import java.io.Serializable;
import java.util.*;

import Executable.BoardModel.*;
import Executable.PieceModel.*;

public class Node implements Serializable {
	State state;
	public Move causingMove;
	public int movedFromPointId;
	public int movedToPointId;
	Node parent;
	int depth;
	int pathCost;

	public Node(Map<Integer, Integer> s, String side) {
		state = new State(s, side);
		causingMove = null;
		parent = null;
		depth = 0;
		pathCost = 0;
	}

	public Node(Map<Integer, Integer> s, String side, int fromPointId, int toPointointId, Move move, Node parentNode) {
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
