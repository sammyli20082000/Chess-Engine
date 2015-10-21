package Board;

import Piece.Piece;

public class Board {
	Piece[][] points = new Piece[9][10];

	public void addPiece(Piece p, int posX, int posY) {
		points[posX][posY] = p;
	}

	public void movePiece(int oldX, int oldY, int newX, int newY) {
		points[newX][newY] = points[oldX][oldY];
		points[oldX][oldY] = null;
	}
}
