package PieceModel;

import java.util.ArrayList;

import BoardModel.Point;

/**
 * Created by him on 9/11/2015.
 */
public class TempPiece extends Piece {

    public TempPiece(PlayerSide s, String link, double w, double h) {
        super(s, link, w, h);
    }

    @Override
    ArrayList<Point> move() {
        return null;
    }
}
