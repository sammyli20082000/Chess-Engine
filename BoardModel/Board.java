package BoardModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PieceModel.Cannon;
import PieceModel.Elephant;
import PieceModel.Horse;
import PieceModel.Piece;

public class Board {
	ArrayList<Point> points;
	String imageLink;

	ArrayList<Point> selectedPieceMovable = new ArrayList<>();

	public Board() {
		points = new ArrayList<Point>();
		imageLink = "";
	}

	public void addPoint(Point p) {
		points.add(p);
	}

	public Point addPoint(double x, double y, double w, double h) {
		Point p = new Point(x, y, w, h);
		addPoint(p);
		return p;
	}

	public void addPoint(ArrayList<Point> p) {
		points = p;
	}

	public void setImageLink(String s) {
		imageLink = s;
	}

	public String getImageLink() {
		return imageLink;
	}

	public Point getPointByID(int id) {
		for (Point p : points) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void updateSelectedPieceMovable(Point p, String currentSide) {
		selectedPieceMovable = new ArrayList<>(p.getPieceInsideMovable());

//		if (!p.getPiece().getSide().equals(currentSide)) {
//			return;
//		}
//
//		for (int i = 0; i < p.getPieceInsideMovable().size(); i++) {
//			try {
//				if (!p.getPieceInsideMovable().get(i).getPiece().getSide().equals(p.getPiece().getSide())) {
//					selectedPieceMovable.add(p.getPieceInsideMovable().get(i));
//				}
//			} catch (Exception e) {
//				selectedPieceMovable.add(p.getPieceInsideMovable().get(i));
//			}
//		}
	}

	public ArrayList<Point> getSelectedPieceMovable() {
		return selectedPieceMovable;
	}

	public void capture(Piece piece, Point fromPoint, Point toPoint) {
		toPoint.setPiece(piece);
		fromPoint.setPiece(null);
	}

	public void movePiece(Piece piece, Point fromPoint, Point toPoint) {
		toPoint.setPiece(piece);
		fromPoint.setPiece(null);
	}

	public String getMoveString(Piece piece, Point fromPoint, Point toPoint) {
		return piece.getName() + " from " + fromPoint.getId() + " to " + toPoint.getId();
	}
}
