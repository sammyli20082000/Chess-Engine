package Piece;

import java.util.ArrayList;
import Board.Point;

public abstract class Piece {
	int side;	//0 -> red, 1 -> blue;
	Point pos;
	
	abstract ArrayList<Point> move();
	
	protected int getSide(){
		return side;
	}
}
