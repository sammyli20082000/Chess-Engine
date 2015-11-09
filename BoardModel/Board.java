package BoardModel;
import java.util.ArrayList;

public class Board {
	ArrayList <Point> points;
	String imageLink;

	public Board(){
		points = new ArrayList<Point>();
		imageLink = "";
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}

	public int addPoint(double x, double y, double w, double h){
		Point p = new Point(x, y, w, h);
		addPoint(p);
		return p.getId();
	}
	
	public void addPoint(ArrayList <Point> p){
		points = p;
	}

	public void setImageLink(String s){
		imageLink = s;
	}

	public String getImageLink(){
		return imageLink;
	}

	public Point getPointByID(int id){
		for (Point p: points){
			if (p.getId() == id)
				return  p;
		}
		return null;
	}

	public ArrayList<Point> getPoints(){
		return points;
	}
}
