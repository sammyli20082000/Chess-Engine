package Executable.PieceModel;

import java.util.ArrayList;

import Executable.BoardModel.Edge.Direction;
import Executable.BoardModel.Point;
import Executable.DataAndSetting;

public class Elephant extends Piece {

	public Elephant(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		ArrayList<Point> validMoves = new ArrayList<>();
		try {
			ArrayList<Direction> dirs = new ArrayList<>();
			dirs.add(Direction.NORTH);
			dirs.add(Direction.EAST);

			if (p.getNextPointByDirectionList(dirs).getPiece() == null) {
				dirs.add(Direction.NORTH);
				dirs.add(Direction.EAST);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		try {
			ArrayList<Direction> dirs = new ArrayList<>();
			dirs.add(Direction.SOUTH);
			dirs.add(Direction.EAST);

			if (p.getNextPointByDirectionList(dirs).getPiece() == null) {
				dirs.add(Direction.SOUTH);
				dirs.add(Direction.EAST);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		try {
			ArrayList<Direction> dirs = new ArrayList<>();
			dirs.add(Direction.NORTH);
			dirs.add(Direction.WEST);

			if (p.getNextPointByDirectionList(dirs).getPiece() == null) {
				dirs.add(Direction.NORTH);
				dirs.add(Direction.WEST);

				validMoves.add(p.getNextPointByDirectionList(dirs));
			}
		} catch (Exception e) {

		}
		try {
			ArrayList<Direction> dirs = new ArrayList<>();
			dirs.add(Direction.SOUTH);
			dirs.add(Direction.WEST);

			if (p.getNextPointByDirectionList(dirs).getPiece() == null) {
				dirs.add(Direction.SOUTH);
				dirs.add(Direction.WEST);

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
