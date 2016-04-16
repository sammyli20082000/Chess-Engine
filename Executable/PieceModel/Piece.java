package Executable.PieceModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Executable.BoardModel.Point;
public abstract class Piece implements Serializable {
private static int idCounter=0;
private int id;
private String name;
private String side;
BufferedImage pieceImage;
private String imageLink;
private double height, width; // 0 to 1
public Piece(String side, String imageLink, double w, double h, String name) {
id = idCounter;
idCounter++;
this.imageLink = imageLink;
this.side = side;
height = h;
width = w;
this.name = name;
try {
pieceImage = ImageIO.read(new File(imageLink));
} catch (Exception e) {
}
}
public BufferedImage getPieceImage() {
return pieceImage;
}
public String getSide() {
return side;
}
public double getHeight() {
return height;
}
public double getWidth() {
return width;
}
public int getId() {
return id;
}
public String getImageLink() {
return imageLink;
}
public String getName(){
return name;
}
public static void resetIdCounter(){
idCounter = 0;
}
public abstract ArrayList<Point> moveInvolvingOtherPiece(Point p);
}
