package Piece;
import java.util.ArrayList;

public abstract class Piece {
	int side;	//0 -> red, 1 -> blue;
	Coordinates pos;
	
	abstract ArrayList<Coordinates> move();
	
	
	
	protected int getSide(){
		return side;
	}
}
