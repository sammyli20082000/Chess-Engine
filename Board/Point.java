package Board;
import java.util.*;
import Piece.Piece;

public class Point {
	int id;
	Map<Integer, Point> edges = new HashMap<Integer, Point>();
	double posX, posY; // 0 to 1, position on graphe
	
	public Point(int id, double posX, double posY){
		this.id = id;
		this.posX = posX;
		this.posY = posY;
	}

	public void addEdge(Point point, Integer dir) {
		edges.put(dir, point);
	}
	
	public Point getNextPointByDirection(int dir){
		return edges.get(dir);
	}
}