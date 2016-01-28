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
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		ArrayList<Point> validMoves = new ArrayList<>();

		try {
			validMoves.add(p.getNextPointByDirection(Direction.EAST));
		} catch (Exception e) {

		}
		try {
			validMoves.add(p.getNextPointByDirection(Direction.SOUTH));
		} catch (Exception e) {

		}
		try {
			validMoves.add(p.getNextPointByDirection(Direction.WEST));
		} catch (Exception e) {

		}
		try {
			validMoves.add(p.getNextPointByDirection(Direction.NORTH));
		} catch (Exception e) {

		}

		for (int i = 0; i < validMoves.size(); i++) {
			if (validMoves.get(i) == null) {
				validMoves.remove(i);
				i--;
				continue;
			}

			if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.player)) {
				if (!(validMoves.get(i).getId() / 10 >= 3 && validMoves.get(i).getId() / 10 <= 5)) {
					validMoves.remove(i);
					i--;
					continue;
				}
				if (!(validMoves.get(i).getId() % 10 >= 7)) {
					validMoves.remove(i);
					i--;
					continue;
				}
			} else if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.computer)) {
				if (!(validMoves.get(i).getId() / 10 >= 3 && validMoves.get(i).getId() / 10 <= 5)) {
					validMoves.remove(i);
					i--;
					continue;
				}
				if (!(validMoves.get(i).getId() % 10 <= 2)) {
					validMoves.remove(i);
					i--;
					continue;
				}
			}
		}

		return validMoves;
	}
}
