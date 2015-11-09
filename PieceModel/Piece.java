package PieceModel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import BoardModel.Point;

public abstract class Piece {
    public enum PlayerSide{
        RED,
        BLACK
    };
    PlayerSide side;
    BufferedImage pieceImage;
    private String imageLink;
    //private Point point;
    private double height, width; // 0 to 1

    public Piece(PlayerSide s, String l, double w, double h){
        imageLink = l;
        side = s;
        height = h;
        width = w;
        try{
            pieceImage = ImageIO.read(new File(imageLink));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*public Point getPoint(){
        return point;
    }*/
    public BufferedImage getPieceImage(){
        return pieceImage;
    }
    public PlayerSide getSide() {
        return side;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }

    /*public void setPoint(Point p){
        point = p;
    }*/
    abstract ArrayList<Point> move();

}
