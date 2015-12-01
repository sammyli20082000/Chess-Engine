package PieceModel;

import java.util.ArrayList;

import BoardModel.Edge.Direction;
import BoardModel.Point;

public class Elephant extends Piece {

	public Elephant(String s, String l, double w, double h) {
		super(s, l, w, h);
	}

	@Override
	public ArrayList<Point> move(Point p) {
		ArrayList<Point> moves = new ArrayList<>();
//		if (this.getSide().equals(Piece.PlayerSide.RED)) {
//			try {
//				Point temp = p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.EAST)
//						.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.EAST);
//				if (temp.getId() % 10 >= 5) {
//					if (p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.EAST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//			try {
//				Point temp = p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.WEST)
//						.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.WEST);
//				if (temp.getId() % 10 >= 5) {
//					if (p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.WEST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//			try {
//				Point temp = p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.EAST)
//						.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.EAST);
//				if (temp.getId() % 10 >= 5) {
//					if (p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.EAST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//			try {
//				Point temp = p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.WEST)
//						.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.WEST);
//				if (temp.getId() % 10 >= 5) {
//					if (p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.WEST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//		} else if (this.getSide().equals(Piece.PlayerSide.BLACK)) {
//			try {
//				Point temp = p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.EAST)
//						.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.EAST);
//				if (temp.getId() % 10 <= 4) {
//					if (p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.EAST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//			try {
//				Point temp = p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.WEST)
//						.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.WEST);
//				if (temp.getId() % 10 <= 4) {
//					if (p.getNextPointByDirection(Direction.NORTH).getNextPointByDirection(Direction.WEST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//			try {
//				Point temp = p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.EAST)
//						.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.EAST);
//				if (temp.getId() % 10 <= 4) {
//					if (p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.EAST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//			try {
//				Point temp = p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.WEST)
//						.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.WEST);
//				if (temp.getId() % 10 <= 4) {
//					if (p.getNextPointByDirection(Direction.SOUTH).getNextPointByDirection(Direction.WEST)
//							.getPiece() == null) {
//						moves.add(temp);
//					}
//				}
//			} catch (Exception e) {
//
//			}
//		}

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
