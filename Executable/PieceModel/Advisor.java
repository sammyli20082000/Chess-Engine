package Executable.PieceModel;

import java.util.ArrayList;

import Executable.BoardModel.Point;
import Executable.BoardModel.Edge.Direction;

public class Advisor extends Piece {

	public Advisor(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	protected ArrayList<Point> moveIndependently(Point p) {
		ArrayList<Point> moves = new ArrayList<>();

		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH_EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH_WEST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH_EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH_WEST));
		} catch (Exception e) {

		}

		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i) == null) {
				moves.remove(i);
				i--;
				continue;
			}
		}

		return moves;
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		return moveIndependently(p);
	}

}
