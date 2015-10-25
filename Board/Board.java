package Board;
import java.util.ArrayList;
import Piece.Piece;

public class Board {
	ArrayList <Point> points;

	public Board(){
		points = new ArrayList<Point>();
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
	public void addPoints(ArrayList <Point> p){
		this.points = p;
	}

	public Point getPointByID(int id){
		return new Point(0, 0, 0);
	}
}
