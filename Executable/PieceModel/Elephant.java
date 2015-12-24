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
	protected ArrayList<Point> moveIndependently(Point p) {
		ArrayList<Point> moves = new ArrayList<>();
		
		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.NORTH)
					.getNextPointByDirection(Direction.EAST).getNextPointByDirection(Direction.EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.NORTH)
					.getNextPointByDirection(Direction.WEST).getNextPointByDirection(Direction.WEST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.SOUTH)
					.getNextPointByDirection(Direction.EAST).getNextPointByDirection(Direction.EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.SOUTH)
					.getNextPointByDirection(Direction.WEST).getNextPointByDirection(Direction.WEST));
		} catch (Exception e) {

		}

		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i) == null) {
				moves.remove(i);
				i--;
				continue;
			}
			
			if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.RED)) {
				if (!(moves.get(i).getId() % 10 >= 5)) {
					moves.remove(i);
					i--;
					continue;
				}
			} else if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.BLACK)) {
				if (!(moves.get(i).getId() % 10 <= 4)) {
					moves.remove(i);
					i--;
					continue;
				}
			}
		}

		return moves;
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		return moveIndependently(p);
	}
}
