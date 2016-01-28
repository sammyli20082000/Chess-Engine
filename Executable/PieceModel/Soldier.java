package Executable.PieceModel;

import java.util.ArrayList;

import Executable.BoardModel.Edge.Direction;
import Executable.BoardModel.Point;
import Executable.DataAndSetting;

public class Soldier extends Piece {
	public Soldier(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		ArrayList<Point> validMoves = new ArrayList<>();
		if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.player)) {
			if (p.getId() % 10 <= 4) {
				if (p.getId() % 10 != 0)
					validMoves.add(p.getNextPointByDirection(Direction.NORTH));
				validMoves.add(p.getNextPointByDirection(Direction.EAST));
				validMoves.add(p.getNextPointByDirection(Direction.WEST));
			} else {
				validMoves.add(p.getNextPointByDirection(Direction.NORTH));
			}
		} else if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.computer)) {
			if (p.getId() % 10 >= 5) {
				if (p.getId() % 10 != 9)
					validMoves.add(p.getNextPointByDirection(Direction.SOUTH));
				validMoves.add(p.getNextPointByDirection(Direction.EAST));
				validMoves.add(p.getNextPointByDirection(Direction.WEST));
			} else {
				validMoves.add(p.getNextPointByDirection(Direction.SOUTH));
			}
		}
		
		for(int i = 0; i < validMoves.size(); i++){
			if(validMoves.get(i) == null){
				validMoves.remove(i);
				i--;
			}
		}
		
		return validMoves;
	}
}
