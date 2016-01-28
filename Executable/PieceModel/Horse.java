package Executable.PieceModel;

import Executable.BoardModel.Edge.Direction;
import java.util.ArrayList;

import Executable.BoardModel.Point;

public class Horse extends Piece {

	public Horse(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		ArrayList<Point> validMoves = new ArrayList<>();
		try {
			if (p.getNextPointByDirection(Direction.NORTH).getPiece() == null) {
				ArrayList<Direction> dirs = new ArrayList<>();

				dirs.add(Direction.NORTH);
				dirs.add(Direction.NORTH);
				dirs.add(Direction.EAST);
				
				validMoves.add(p.getNextPointByDirectionList(dirs));

				dirs.clear();
				dirs.add(Direction.NORTH);
				dirs.add(Direction.NORTH);
				dirs.add(Direction.WEST);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		try {
			if (p.getNextPointByDirection(Direction.EAST).getPiece() == null) {
				ArrayList<Direction> dirs = new ArrayList<>();

				dirs.add(Direction.EAST);
				dirs.add(Direction.EAST);
				dirs.add(Direction.NORTH);

				validMoves.add(p.getNextPointByDirectionList(dirs));

				dirs.clear();
				dirs.add(Direction.EAST);
				dirs.add(Direction.EAST);
				dirs.add(Direction.SOUTH);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		try {
			if (p.getNextPointByDirection(Direction.SOUTH).getPiece() == null) {
				ArrayList<Direction> dirs = new ArrayList<>();

				dirs.add(Direction.SOUTH);
				dirs.add(Direction.SOUTH);
				dirs.add(Direction.EAST);

				validMoves.add(p.getNextPointByDirectionList(dirs));

				dirs.clear();
				dirs.add(Direction.SOUTH);
				dirs.add(Direction.SOUTH);
				dirs.add(Direction.WEST);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		try {
			if (p.getNextPointByDirection(Direction.WEST).getPiece() == null) {
				ArrayList<Direction> dirs = new ArrayList<>();

				dirs.add(Direction.WEST);
				dirs.add(Direction.WEST);
				dirs.add(Direction.SOUTH);

				validMoves.add(p.getNextPointByDirectionList(dirs));

				dirs.clear();
				dirs.add(Direction.WEST);
				dirs.add(Direction.WEST);
				dirs.add(Direction.NORTH);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		
		for (int i = 0; i < validMoves.size(); i++) {
			if (validMoves.get(i).getPiece() != null && validMoves.get(i).getPiece().getSide() == this.getSide()) {
				validMoves.remove(i);
				i--;
				continue;
			}
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
