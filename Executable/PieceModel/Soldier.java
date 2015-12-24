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
	protected ArrayList<Point> moveIndependently(Point p) {
		ArrayList<Point> moves = new ArrayList<>();
		if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.RED)) {
			if (p.getId() % 10 <= 4) {
				if (p.getId() % 10 != 0)
					moves.add(p.getNextPointByDirection(Direction.NORTH));
				moves.add(p.getNextPointByDirection(Direction.EAST));
				moves.add(p.getNextPointByDirection(Direction.WEST));
			} else {
				moves.add(p.getNextPointByDirection(Direction.NORTH));
			}
		} else if (this.getSide().equals(DataAndSetting.PieceData.PlayerSide.BLACK)) {
			if (p.getId() % 10 >= 5) {
				if (p.getId() % 10 != 9)
					moves.add(p.getNextPointByDirection(Direction.SOUTH));
				moves.add(p.getNextPointByDirection(Direction.EAST));
				moves.add(p.getNextPointByDirection(Direction.WEST));
			} else {
				moves.add(p.getNextPointByDirection(Direction.SOUTH));
			}
		}
		
		for(int i = 0; i < moves.size(); i++){
			if(moves.get(i) == null){
				moves.remove(i);
				i--;
			}
		}
		
		return moves;
	}

	@Override
	public ArrayList<Point> moveInvolvingOtherPiece(Point p) {
		return moveIndependently(p);
	}
}
