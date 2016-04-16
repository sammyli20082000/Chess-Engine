package Executable.ObjectModel;
import java.io.Serializable;
import java.util.*;

import Executable.BoardModel.Point;

public class State implements Serializable {
	Map<Integer, Integer> state;	// pointId, pieceId
	String side;

	public State(Map<Integer, Integer> state, String side) {
		this.state = state;
		this.side = side;
	}

	public Map<Integer, Integer> getPointsState() {
		return state;
	}
	
	public String getSide() {
		return side;
	}
}
