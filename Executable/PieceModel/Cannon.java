package Executable.PieceModel;

import java.util.ArrayList;

import Executable.BoardModel.Edge.Direction;
import Executable.BoardModel.Point;

public class Cannon extends Piece {
	public Cannon(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		ArrayList<Point> validMoves = new ArrayList<>();
		int counter = 0;

		ArrayList<Point> northValidMoves = p.getPointsAlongDirection(Direction.NORTH);
		ArrayList<Point> eastValidMoves = p.getPointsAlongDirection(Direction.EAST);
		ArrayList<Point> southValidMoves = p.getPointsAlongDirection(Direction.SOUTH);
		ArrayList<Point> westValidMoves = p.getPointsAlongDirection(Direction.WEST);

		for (int i = 0; i < northValidMoves.size(); i++) {
			if (counter == 0 && northValidMoves.get(i).getPiece() != null) {
				counter++;
				northValidMoves.remove(i);
				i--;
				continue;
			}
			if (counter == 1) {
				if (northValidMoves.get(i).getPiece() == null) {
					northValidMoves.remove(i);
					i--;
					continue;
				} else {
					counter++;
					continue;
				}
			}
			if (counter > 1) {
				northValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;
		
		for (int i = 0; i < southValidMoves.size(); i++) {
			if (counter == 0 && southValidMoves.get(i).getPiece() != null) {
				counter++;
				southValidMoves.remove(i);
				i--;
				continue;
			}
			if (counter == 1) {
				if (southValidMoves.get(i).getPiece() == null) {
					southValidMoves.remove(i);
					i--;
					continue;
				} else {
					counter++;
					continue;
				}
			}
			if (counter > 1) {
				southValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;
		
		for (int i = 0; i < eastValidMoves.size(); i++) {
			if (counter == 0 && eastValidMoves.get(i).getPiece() != null) {
				counter++;
				eastValidMoves.remove(i);
				i--;
				continue;
			}
			if (counter == 1) {
				if (eastValidMoves.get(i).getPiece() == null) {
					eastValidMoves.remove(i);
					i--;
					continue;
				} else {
					counter++;
					continue;
				}
			}
			if (counter > 1) {
				eastValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;
		
		for (int i = 0; i < westValidMoves.size(); i++) {
			if (counter == 0 && westValidMoves.get(i).getPiece() != null) {
				counter++;
				westValidMoves.remove(i);
				i--;
				continue;
			}
			if (counter == 1) {
				if (westValidMoves.get(i).getPiece() == null) {
					westValidMoves.remove(i);
					i--;
					continue;
				} else {
					counter++;
					continue;
				}
			}
			if (counter > 1) {
				westValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;

		for (int i = 0; i < northValidMoves.size(); i++) {
			validMoves.add(northValidMoves.get(i));
		}
		for (int i = 0; i < eastValidMoves.size(); i++) {
			validMoves.add(eastValidMoves.get(i));
		}
		for (int i = 0; i < southValidMoves.size(); i++) {
			validMoves.add(southValidMoves.get(i));
		}
		for (int i = 0; i < westValidMoves.size(); i++) {
			validMoves.add(westValidMoves.get(i));
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
