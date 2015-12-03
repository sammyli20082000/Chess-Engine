package PieceModel;

import BoardModel.Edge.Direction;
import java.util.ArrayList;

import BoardModel.Point;

public class Horse extends Piece {

	public Horse(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	protected ArrayList<Point> moveIndependently(Point p) {
		ArrayList<Point> moves = new ArrayList<>();

		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.NORTH)
					.getNextPointByDirection(Direction.EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.NORTH)
					.getNextPointByDirection(Direction.WEST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.SOUTH)
					.getNextPointByDirection(Direction.EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.SOUTH)
					.getNextPointByDirection(Direction.WEST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.EAST).getNextPointByDirection(Direction.EAST)
					.getNextPointByDirection(Direction.NORTH));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.EAST).getNextPointByDirection(Direction.EAST)
					.getNextPointByDirection(Direction.SOUTH));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.WEST).getNextPointByDirection(Direction.WEST)
					.getNextPointByDirection(Direction.NORTH));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.WEST).getNextPointByDirection(Direction.WEST)
					.getNextPointByDirection(Direction.SOUTH));
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
