package BoardModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	public void updateSelectedPieceMovable(Point p) {
		selectedPieceMovable = p.getPieceInsideMovable();
	}
	
	public ArrayList<Point> getSelectedPieceMovable(){
		return selectedPieceMovable;
	}
}
