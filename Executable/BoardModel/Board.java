package Executable.BoardModel;
import java.util.ArrayList;
import Executable.PieceModel.Piece;
import Executable.ObjectModel.Move;
public class Board {
ArrayList<Point> points;
ArrayList<Point> selectedPieceMovable;
public Board() {
points = new ArrayList<Point>();
selectedPieceMovable = new ArrayList<>();
}
public void addPoint(Point p) {
points.add(p);
}
public Point addPoint(double x, double y, double w, double h) {
Point p = new Point(x, y, w, h);
addPoint(p);
return p;
}
public Point addPoint(double x, double y, double w, double h, int id) {
Point p = new Point(x, y, w, h, id);
addPoint(p);
return p;
}
public void addPoint(ArrayList<Point> p) {
points = p;
}
public Point getPointById(int id) {
for (Point p : points) {
if (p.getId() == id)
return p;
}
return null;
}
public ArrayList<Point> getPoints() {
return points;
}
public void updateSelectedPieceMovable(Point p, String currentSide) {
selectedPieceMovable = new ArrayList<>(p.getPieceInsideMovable());
}
public ArrayList<Point> getSelectedPieceMovable() {
return selectedPieceMovable;
}
public void capture(Piece piece, Point fromPoint, Point toPoint) {
toPoint.setPiece(piece);
fromPoint.setPiece(null);
}
public void movePiece(Piece piece, Point fromPoint, Point toPoint) {
toPoint.setPiece(piece);
fromPoint.setPiece(null);
}
public String getMoveString(Piece piece, Point fromPoint, Point toPoint) {
if (toPoint == null)
return piece.getName() + " from " + fromPoint.getId() + " to " + toPoint.getId();
else
return piece.getName() + " added to " + fromPoint.getId();
}
public String getMoveString(Move move) {
if (move.fromPoint != -1)
return move.pieceName + " from " + move.fromPoint + " to " + move.toPoint;
else
return move.pieceName + " added to " + move.toPoint;
}
public ArrayList<Move> generateAllValidMoves(String side) {
ArrayList<Move> validMoves = new ArrayList<>();
for (Point point : points) {
if (point.getPiece() != null && point.getPiece().getSide().equals(side)) {
ArrayList<Point> tempMovables = point.getPieceInsideMovable();
for (Point tempMovable : tempMovables) {
validMoves.add(Move.transformingMove(point.getPiece().getName(), point.getId(), tempMovable.getId()));
}
}
}
return validMoves;
}
public boolean canContinue() {
return true;
}
public static void filpboard() {
// TODO: This function is used to change AI thinking perspective. Please change pieces' position by setPiece() in Point object.
// if this is handled in AI and moving rules coding, the implementation of this function can be skipped.
}
}
