package Executable.BoardModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Executable.State;
import Executable.PieceModel.Piece;

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
	
	public Point addPoint(double x, double y, double w, double h, int id) {
		Point p = new Point(x, y, w, h, id);
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
	
	public static void filpboard() {
	}
}
