package PieceModel;

import java.util.ArrayList;

import BoardModel.Edge.Direction;
import BoardModel.Point;

public class Cannon extends Piece {
	public Cannon(String s, String l, double w, double h, String n) {
		super(s, l, w, h, n);
	}

	@Override
	public ArrayList<Point> move(Point p) {
		ArrayList<Point> moves = new ArrayList<>();
		Point point = p;

		for (;;) {
			try {
				point = point.getNextPointByDirection(Direction.NORTH);
				moves.add(point);
			} catch (Exception e) {
				point = p;
				break;
			}
		}
		
		for (;;) {
			try {
				point = point.getNextPointByDirection(Direction.EAST);
				moves.add(point);
			} catch (Exception e) {
				point = p;
				break;
			}
		}
		
		for (;;) {
			try {
				point = point.getNextPointByDirection(Direction.SOUTH);
				moves.add(point);
			} catch (Exception e) {
				point = p;
				break;
			}
		}
		
		for (;;) {
			try {
				point = point.getNextPointByDirection(Direction.WEST);
				moves.add(point);
			} catch (Exception e) {
				point = p;
				break;
			}
		}
		
		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i) == null) {
				moves.remove(i);
				i--;
				continue;
			}
		}

		return moves;
	}

}
