package Executable.BoardModel;
import java.io.Serializable;
import java.util.*;
import Executable.PieceModel.Piece;
public class Point implements Serializable {
public static int idCounter = 0;
private int id;
private Piece piece;
private Map<Edge.Direction, Point> edges = new HashMap<Edge.Direction, Point>();
private double posX, posY, // 0 to 1, position on graphic, coordinate of center of image
width, height; // 0 to 1, position on graphic
private ArrayList<Point> pieceInsideMovable = new ArrayList<Point>();
public Point(double x, double y, double w, double h) {
id = idCounter;
idCounter++;
posX = x;
posY = y;
width = w;
height = h;
piece = null;
}
public Point(double x, double y, double w, double h, int id) {
this.id = id;
posX = x;
posY = y;
width = w;
height = h;
piece = null;
}
public int getId() {
return id;
}
public Piece getPiece() {
return piece;
}
public double getPosX() {
return posX;
}
public double getPosY() {
return posY;
}
public double getWidth() {
return width;
}
public double getHeight() {
return height;
}
public void setPiece(Piece p) {
piece = p;
}
public void addEdge(Edge.Direction dir, Point point) {
edges.put(dir, point);
}
public Point getImmediateNextPointAt(Edge.Direction dir) {
try {
return edges.get(dir);
} catch (Exception e) {
return null;
}
}
public Point getImmediateNextPointAt(Edge.Direction[] dirs) {
Point point = this;
for (int i = 0; i < dirs.length; i++) {
try {
point = point.getImmediateNextPointAt(dirs[i]);
} catch (Exception e) {
return null;
}
}
return point;
}
public ArrayList<Point> getPointsAlongDirection(Edge.Direction dir) {
Point point = this;
ArrayList<Point> points = new ArrayList<>();
for (;;) {
try {
point = point.getImmediateNextPointAt(dir);
if (point == null) break;
points.add(point);
} catch (Exception e) {
break;
}
}
return points;
}
public ArrayList<Point> getPieceInsideMovable() {
pieceInsideMovable = getPiece().moveInvolvingOtherPiece(this);
return pieceInsideMovable;
}
public static void resetIdCounter() {
Point.idCounter = 0;
}
}
