package UIHandlerModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import BoardModel.Board;
import BoardModel.Point;
import PieceModel.Piece;

/**
 * Created by him on 29/10/2015.
 */
public class GameAreaPanel extends JPanel {
    private Board board;
    private UIHandler ui;
    private ArrayList<Piece> pieces;
    private BufferedImage boardImage;
    private double boardImageTangent = 1.0, gamePanelTangent = 1.0;  // height / width, that is (y/x)
    private double mouseXTang, mouseYTang; //relative coordinate not pixel; range from 0 to 1
    private int boardPaintWidth, boardPaintHeight, boardBaseXShift, boardBaseYShift;
    private double screenBound = 0.8;

    public GameAreaPanel(UIHandler _ui, Board b) {
        ui = _ui;
        board = b;
        setupBoardImage();
        this.addMouseListener(handleMouseClick());
    }

    private void setupBoardImage() {
        try {
            boardImage = ImageIO.read(new File(board.getImageLink()));
            boardImageTangent = (double) boardImage.getHeight() / (double) boardImage.getWidth();
            calcGamePanelPreferredSize();
        } catch (Exception e) {
            Dimension monitorResolution = ui.getScreenResolution();
            boardPaintWidth = (int) (monitorResolution.getWidth() * screenBound);
            boardPaintHeight = (int) (monitorResolution.getHeight() * screenBound);
            this.setPreferredSize(new Dimension(boardPaintWidth, boardPaintHeight));
            e.printStackTrace();
            boardImage = null;
        }
    }

    private void calcGamePanelPreferredSize(){
        Dimension monitorResolution = ui.getScreenResolution();
        Boolean
                boardImageWidthExceedScreenWidth = boardImage.getWidth() > monitorResolution.getWidth() * screenBound,
                boardImageHeightExceedScreenHeight = boardImage.getHeight() > monitorResolution.getHeight() * screenBound;

        if ( boardImageWidthExceedScreenWidth && boardImageHeightExceedScreenHeight ){

            double screenTangent = 1.0 * monitorResolution.getHeight() / monitorResolution.getWidth();
            if(screenTangent > boardImageTangent){

                boardPaintWidth = (int)(monitorResolution.getWidth() * screenBound);
                boardPaintHeight = (int)(monitorResolution.getWidth() * screenBound * boardImageTangent);

            } else if(screenTangent < boardImageTangent){

                boardPaintWidth = (int)(monitorResolution.getHeight() * screenBound / boardImageTangent);
                boardPaintHeight = (int)(monitorResolution.getHeight() * screenBound);

            } else {

                boardPaintWidth = (int)(monitorResolution.getWidth() * screenBound);
                boardPaintHeight = (int)(monitorResolution.getHeight() * screenBound);

            }

        } else if ( boardImageWidthExceedScreenWidth ) {

            boardPaintWidth = (int) (monitorResolution.getWidth() * screenBound);
            boardPaintHeight = (int) (monitorResolution.getWidth() * screenBound * boardImageTangent);

        }else if ( boardImageHeightExceedScreenHeight ) {

            boardPaintWidth = (int) (monitorResolution.getHeight() * screenBound / boardImageTangent);
            boardPaintHeight = (int) (monitorResolution.getHeight() * screenBound);

        }else {

            boardPaintWidth = boardImage.getWidth();
            boardPaintHeight = boardImage.getHeight();

        }
        this.setPreferredSize(new Dimension(boardPaintWidth, boardPaintHeight));
    }

    int debugCounter;
    private void printString(Graphics g, String s){
        debugCounter++;
        g.setColor(Color.red);
        g.drawString(s, 0, (int) (1.25 * g.getFont().getSize() * debugCounter));
    }
    private void printString(Graphics g, String tag, double[] dSet){
        String s = tag + ": ";
        for (double d : dSet)
            s = s+ d + ", ";
        printString(g, s);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (boardImage != null) g.drawImage(boardImage, boardBaseXShift, boardBaseYShift, boardPaintWidth, boardPaintHeight, null);

        drawPiecePlacingPoints(g);

        Point selectedPoint = ui.getCalback().getSelectedPoint();
        debugCounter=0;
        printString(g, "mouse", new double[]{mouseXTang, mouseYTang});
        printString(g, "board image tanget", new double []{boardImageTangent});
        printString(g, "board paint size", new double[] {boardPaintWidth, boardPaintHeight});
        printString(g, "board panel size", new double[] {this.getWidth(), this.getHeight()});
        printString(g, "selected point", new double[]{
                (selectedPoint == null)? -1: selectedPoint.getId()
        });

        drawCurrentPieces(g);
    }

    private void drawCurrentPieces(Graphics g){
        for(Point point :board.getPoints()){
            Piece piece = point.getPiece();

            if(piece != null){
                double pieceWidth = piece.getWidth(),
                        pieceHeight = piece.getHeight();

                g.drawImage(
                        piece.getPieceImage(),
                        (int)(((point.getPosX()-pieceWidth/2)*boardPaintWidth)+boardBaseXShift),
                        (int)(((point.getPosY()-pieceHeight/2)*boardPaintHeight)+boardBaseYShift),
                        (int)(pieceWidth*boardPaintWidth),
                        (int)(pieceHeight*boardPaintHeight),
                        null);
            }
        }
    }

    private void drawPiecePlacingPoints(Graphics g){
        for (Point p: board.getPoints()){
            int
                    w = (int) (p.getWidth() * boardPaintWidth),
                    h = (int) (p.getHeight() * boardPaintHeight),
                    x = (int) ((p.getPosX() - p.getWidth()/2) * boardPaintWidth + boardBaseXShift),
                    y = (int) ((p.getPosY() - p.getHeight()/2) * boardPaintHeight + boardBaseYShift);

            g.setColor(new Color(0.0f, 0.0f, 1.0f, 0.5f));
            g.fillRect(x, y, w, h);
            g.setColor(Color.red);
            g.drawString(p.getId()+"", x+w/2, y+h/2);
        }
    }

    public void notifyWindowResized(){
        gamePanelTangent = (double) this.getHeight() / this.getWidth();
        if (boardImage != null && gamePanelTangent != boardImageTangent){
            if (gamePanelTangent > boardImageTangent){
                boardBaseXShift = 0;
                boardPaintWidth = this.getWidth();
                boardPaintHeight = (int) (boardPaintWidth * boardImageTangent);
                boardBaseYShift = (this.getHeight() - boardPaintHeight) / 2;
            }else{
                boardBaseYShift = 0;
                boardPaintHeight = this.getHeight();
                boardPaintWidth = (int) (boardPaintHeight / boardImageTangent);
                boardBaseXShift = (this.getWidth()-boardPaintWidth) / 2;
            }
        }else{
            boardBaseXShift = 0;
            boardBaseYShift = 0;
            boardPaintHeight = this.getHeight();
            boardPaintWidth = this.getWidth();
        }
    }

    private MouseAdapter handleMouseClick(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseXTang = ((double) e.getX() - boardBaseXShift) / boardPaintWidth;
                mouseYTang = ((double) e.getY() - boardBaseYShift) / boardPaintHeight;

                ui.getCalback().onPointSelected(getWhichPointClicked());

                repaint();
            }
        };
    }

    private Point getWhichPointClicked(){
        for (Point p : board.getPoints()){
            if(
                    p.getPosX() - p.getWidth() / 2 <= mouseXTang &&
                    p.getPosX() + p.getWidth() / 2 >= mouseXTang &&
                    p.getPosY() - p.getHeight() / 2 <= mouseYTang &&
                    p.getPosY() + p.getHeight() / 2 >= mouseYTang)
                return p;
        }
        return  null;
    }

}