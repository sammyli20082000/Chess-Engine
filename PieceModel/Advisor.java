package PieceModel;

import java.util.ArrayList;

import BoardModel.Point;
import BoardModel.Edge.Direction;

public class Advisor extends Piece {
	
	public Advisor(String s, String l, double w, double h) {
		super(s, l, w, h);
	}

	@Override
	public ArrayList<Point> move(Point p) {
		ArrayList<Point> moves = new ArrayList<>();
		
		moves.add(p.getNextPointByDirection(Direction.NORTH_EAST));
		moves.add(p.getNextPointByDirection(Direction.NORTH_WEST));
		moves.add(p.getNextPointByDirection(Direction.SOUTH_EAST));
		moves.add(p.getNextPointByDirection(Direction.SOUTH_WEST));
		
		return moves;
	}

}
