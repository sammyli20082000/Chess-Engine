package Executable.PieceModel;

import java.util.ArrayList;

import Executable.BoardModel.Point;
import Executable.BoardModel.Edge.Direction;

public class Advisor extends Piece {

	public Advisor(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		ArrayList<Point> validMoves = new ArrayList<>();

		try {
			validMoves.add(p.getNextPointByDirection(Direction.NORTH_EAST));
		} catch (Exception e) {

		}
		try {
			validMoves.add(p.getNextPointByDirection(Direction.NORTH_WEST));
		} catch (Exception e) {

		}
		try {
			validMoves.add(p.getNextPointByDirection(Direction.SOUTH_EAST));
		} catch (Exception e) {

		}
		try {
			validMoves.add(p.getNextPointByDirection(Direction.SOUTH_WEST));
		} catch (Exception e) {

		}

		for (int i = 0; i < validMoves.size(); i++) {
			if (validMoves.get(i) == null) {
				validMoves.remove(i);
				i--;
				continue;
			}
		}

		return validMoves;

	}
}
