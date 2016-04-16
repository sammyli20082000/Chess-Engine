package Executable.ObjectModel;

import java.io.Serializable;

import Executable.BoardModel.*;
import Executable.PieceModel.*;

public class Move implements Serializable {
	public int piece = -1;
	public String pieceName = "";
	public int fromPoint = -1;
	public int toPoint = -1;

	public Move() {

	}

	public Move(String name, int toPoint) {
		pieceName = name;
		this.toPoint = toPoint;
	}

	public static Move addingMove(int piece, int toPoint) {
		Move addingMove = new Move();
		addingMove.piece = piece;
		addingMove.toPoint = toPoint;
		return addingMove;
	}

	public static Move transformingMove(String name, int fromPoint, int toPoint) {
		Move transformingMove = new Move();
		transformingMove.pieceName = name;
		transformingMove.fromPoint = fromPoint;
		transformingMove.toPoint = toPoint;
		return transformingMove;
	}
	
	public boolean equals(Move move) {
		return this.fromPoint == move.fromPoint && this.toPoint == move.toPoint
				&& this.piece == move.piece && this.pieceName.equalsIgnoreCase(move.pieceName);
	}
}
