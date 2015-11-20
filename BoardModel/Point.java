package BoardModel;
import java.util.*;

import PieceModel.Piece;

public class Point {
	private static int idCounter = 0;
	private int id;
	private Piece piece;
	private Map<Edge.Direction, Point> edges = new HashMap<Edge.Direction, Point>();
	private double
			posX, posY, // 0 to 1, position on graphic, coordinate of center of image
			width, height; // 0 to 1, position on graphic
	private ArrayList<Point> pieceInsideMovable = new ArrayList<Point>();
	
	public Point(double x, double y, double w, double h){
		id = idCounter;
		idCounter++;

		for(Edge.Direction n : Edge.Direction.values())

		posX = x;
		posY = y;
		width = w;
		height = h;
		piece = null;
	}

	public int getId(){
		return id;
	}

	public Piece getPiece(){
		return piece;
	}

	public double getPosX(){
		return posX;
	}

	public double getPosY(){
		return posY;
	}

	public double getWidth(){
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setPiece(Piece p){
		piece = p;
	}

	public void addEdge(Edge.Direction dir, Point point) { edges.put(dir, point); }
	
	public Point getNextPointByDirection(Edge.Direction dir){
		return edges.get(dir);
	}
	
	public ArrayList<Point> getPieceInsideMovable() {
		pieceInsideMovable = getPiece().move(this);
		return pieceInsideMovable;
	}
	
}