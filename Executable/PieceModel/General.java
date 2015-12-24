package Executable.PieceModel;

import java.util.ArrayList;

import Executable.BoardModel.Point;
import Executable.BoardModel.Edge.Direction;
import Executable.DataAndSetting;

public class General extends Piece {

	public General(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	protected ArrayList<Point> moveIndependently(Point p) {
		ArrayList<Point> moves = new ArrayList<>();

		try {
			moves.add(p.getNextPointByDirection(Direction.EAST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.SOUTH));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.WEST));
		} catch (Exception e) {

		}
		try {
			moves.add(p.getNextPointByDirection(Direction.NORTH));
		} catch (Exception e) {

		}

		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i) == null) {
				moves.remove(i);
				i--;
				continue;
			}

			if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.RED)) {
				if (!(moves.get(i).getId() / 10 >= 3 && moves.get(i).getId() / 10 <= 5)) {
					moves.remove(i);
					i--;
					continue;
				}
				if (!(moves.get(i).getId() % 10 >= 7)) {
					moves.remove(i);
					i--;
					continue;
				}
			} else if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.BLACK)) {
				if (!(moves.get(i).getId() / 10 >= 3 && moves.get(i).getId() / 10 <= 5)) {
					moves.remove(i);
					i--;
					continue;
				}
				if (!(moves.get(i).getId() % 10 <= 2)) {
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
