package Board;

import java.util.*;

import Piece.Piece;

public class Point {
	Map<Integer, Point> edges = new HashMap<Integer, Point>();
	Piece pieceHolding;
	int posX, posY;
	
	public Point(int posX, int posY){
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
