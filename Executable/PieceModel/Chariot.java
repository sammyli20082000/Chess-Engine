package Executable.PieceModel;

import Executable.BoardModel.Edge.Direction;
import java.util.ArrayList;

import Executable.BoardModel.Board;
import Executable.BoardModel.Edge;
import Executable.BoardModel.Point;

public class Chariot extends Piece {

	public Chariot(String s, String l, double w, double h, String n) {
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
				if (northValidMoves.get(i).getPiece().getSide() == this.getSide()) {
					northValidMoves.remove(i);
					i--;
				}
				counter++;
				continue;
			}
			if (counter > 0) {
				northValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;

		for (int i = 0; i < southValidMoves.size(); i++) {
			if (counter == 0 && southValidMoves.get(i).getPiece() != null) {
				if (southValidMoves.get(i).getPiece().getSide() == this.getSide()) {
					southValidMoves.remove(i);
					i--;
				}
				counter++;
				continue;
			}
			if (counter > 0) {
				southValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;
		
		for (int i = 0; i < westValidMoves.size(); i++) {
			if (counter == 0 && westValidMoves.get(i).getPiece() != null) {
				if (westValidMoves.get(i).getPiece().getSide() == this.getSide()) {
					westValidMoves.remove(i);
					i--;
				}
				counter++;
				continue;
			}
			if (counter > 0) {
				westValidMoves.remove(i);
				i--;
				continue;
			}
		}
		counter = 0;

		for (int i = 0; i < eastValidMoves.size(); i++) {
			if (counter == 0 && eastValidMoves.get(i).getPiece() != null) {
				if (eastValidMoves.get(i).getPiece().getSide() == this.getSide()) {
					eastValidMoves.remove(i);
					i--;
				}
				counter++;
				continue;
			}
			if (counter > 0) {
				eastValidMoves.remove(i);
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
			if (validMoves.get(i) == null) {
				validMoves.remove(i);
				i--;
				continue;
			}
		}

		return validMoves;
	}
}
