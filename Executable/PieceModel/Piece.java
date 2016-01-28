package Executable.PieceModel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Executable.BoardModel.Board;
import Executable.BoardModel.Point;

public abstract class Piece implements Serializable {
    private static int idCounter=0;
    private int id;
    private String name;
    private String side;
    BufferedImage pieceImage;
    private String imageLink;
    private double height, width; // 0 to 1

    /**
     * @param s side
     * @param l	imageLink
     * @param w	imageWidth
     * @param h	imageHeight
     * @param n	Name
     */
    public Piece(String s, String l, double w, double h, String n){
        id = idCounter;
        idCounter++;
        imageLink = l;
        side = s;
        height = h;
        width = w;
        name = n;
        try{
            pieceImage = ImageIO.read(new File(imageLink));
        }catch (Exception e){
            e.printStackTrace();
            pieceImage = null;
        }
    }

    /*public Point getPoint(){
        return point;
    }*/
    public BufferedImage getPieceImage(){
        return pieceImage;
    }
    public String getSide() {
        return side;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    public int getId(){
        return id;
    }
    public String getImageLink(){
        return imageLink;
    }
    public String getName(){
    	return name;
    }

    public abstract ArrayList<Point> moveInvolvingOtherPiece(Point p);
}
