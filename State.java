import java.util.ArrayList;
import java.util.Map;

import BoardModel.Point;

public class State {
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
