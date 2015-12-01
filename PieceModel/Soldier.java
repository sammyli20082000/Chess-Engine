package PieceModel;

import java.util.ArrayList;

import BoardModel.Edge.Direction;
import BoardModel.Point;

public class Soldier extends Piece {
	public Soldier(String s, String l, double w, double h) {
		super(s, l, w, h);
	}

	@Override
	public ArrayList<Point> move(Point p) {
		ArrayList<Point> moves = new ArrayList<>();
		if (this.getSide().equals(Piece.PlayerSide.RED)) {
			if (p.getId() % 10 <= 4) {
				if (p.getId() % 10 != 0)
					moves.add(p.getNextPointByDirection(Direction.NORTH));
				moves.add(p.getNextPointByDirection(Direction.EAST));
				moves.add(p.getNextPointByDirection(Direction.WEST));
			} else {
				moves.add(p.getNextPointByDirection(Direction.NORTH));
			}
		} else if (this.getSide().equals(Piece.PlayerSide.BLACK)) {
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
}
