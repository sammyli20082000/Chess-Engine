package UIHandlerModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
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

    public GameAreaPanel(UIHandler _ui) {
        ui = _ui;
        setupBoardImage();
        this.addMouseListener(handleMouseClick());
    }

    private void setupBoardImage() {
        try {
            boardImage = ImageIO.read(new File(DataAndSetting.BoardData.imageLink));
            boardImageTangent = (double) boardImage.getHeight() / (double) boardImage.getWidth();
            calcGamePanelPreferredSize(boardImage.getWidth(), boardImage.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
            calcGamePanelPreferredSize(
                    DataAndSetting.BoardData.preferredPixelWidth,
                    DataAndSetting.BoardData.preferredPixelHeight
            );
        }
    }

    private void calcGamePanelPreferredSize(int width, int height) {
        Dimension monitorResolution = ui.getScreenResolution();
        Boolean
                boardImageWidthExceedScreenWidth = width > monitorResolution.getWidth() * screenBound,
                boardImageHeightExceedScreenHeight = height > monitorResolution.getHeight() * screenBound;

        if (boardImageWidthExceedScreenWidth && boardImageHeightExceedScreenHeight) {

            double screenTangent = 1.0 * monitorResolution.getHeight() / monitorResolution.getWidth();
            if (screenTangent > boardImageTangent) {

                boardPaintWidth = (int) (monitorResolution.getWidth() * screenBound);
                boardPaintHeight = (int) (monitorResolution.getWidth() * screenBound * boardImageTangent);

            } else if (screenTangent < boardImageTangent) {

                boardPaintWidth = (int) (monitorResolution.getHeight() * screenBound / boardImageTangent);
                boardPaintHeight = (int) (monitorResolution.getHeight() * screenBound);

            } else {

                boardPaintWidth = (int) (monitorResolution.getWidth() * screenBound);
                boardPaintHeight = (int) (monitorResolution.getHeight() * screenBound);

            }

        } else if (boardImageWidthExceedScreenWidth) {

            boardPaintWidth = (int) (monitorResolution.getWidth() * screenBound);
            boardPaintHeight = (int) (monitorResolution.getWidth() * screenBound * boardImageTangent);

        } else if (boardImageHeightExceedScreenHeight) {

            boardPaintWidth = (int) (monitorResolution.getHeight() * screenBound / boardImageTangent);
            boardPaintHeight = (int) (monitorResolution.getHeight() * screenBound);

        } else {

            boardPaintWidth = width;
            boardPaintHeight = height;

        }
        this.setPreferredSize(new Dimension(boardPaintWidth, boardPaintHeight));
    }

    int debugCounter;

    private void printString(Graphics g, String s) {
        debugCounter++;
        int printYShift = (int) (1.25 * g.getFont().getSize() * debugCounter);
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(s, g);

        g.setColor(new Color(0f, 0f, 0f, 0.3f));
        g.fillRect(0, printYShift - fm.getAscent(), (int) rect.getWidth(), (int) rect.getHeight());

        g.setColor(Color.white);
        g.drawString(s, 0, printYShift);
    }

    private void printString(Graphics g, String tag, double[] dSet) {
        String s = tag + ": ";
        for (int i = 0; i < dSet.length; i++) {
            if (i == dSet.length - 1)
                s = s + dSet[i];
            else
                s = s + dSet[i] + ", ";
        }
        printString(g, s);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (boardImage != null)
            g.drawImage(boardImage, boardBaseXShift, boardBaseYShift, boardPaintWidth, boardPaintHeight, null);

        if (board != null) {
            if (ui.getIsShowPiecePlacingPoint()) drawAllPiecePlacingPointsForDebug(g);
            drawCurrentPieces(g);
            drawFrameForSelectedObject(g);
            drawPieceNextMoveCandidatePoints(g);
        }

        if (ui.getIsShowDebug()) printDebugLog(g);
    }

    private void drawAllPiecePlacingPointsForDebug(Graphics g){
        if (board!=null)
        drawPiecePlacingPoints(g, board.getPoints(), new Color(0f ,0f ,1f, 0.5f), true);
    }

    private void drawPieceNextMoveCandidatePoints(Graphics g){
        ArrayList<Point> points = ui.getCallback().getPieceNextMovePointCandidateList();
        if (points==null) return;
        drawPiecePlacingPoints(g, points, new Color(1f, 0f, 0f, 0.5f), false);
    }

    private void drawFrameForSelectedObject(Graphics g) {
        Point selectedPoint = ui.getCallback().getSelectedPointOrPiece();
        Piece selectedPiece = (selectedPoint != null) ? selectedPoint.getPiece() : null;

        if (selectedPoint == null) return;

        double
                posX = selectedPoint.getPosX(),
                posY = selectedPoint.getPosY(),
                width = (selectedPiece != null) ? selectedPiece.getWidth() : selectedPoint.getWidth(),
                height = (selectedPiece != null) ? selectedPiece.getHeight() : selectedPoint.getHeight();

        double frameThickness = 1.0 / 15, frameLength = 1.0 / 4;
        g.setColor(Color.green);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int
                        w = (int) (width * boardPaintWidth * frameLength),
                        h = (int) (height * boardPaintHeight * frameThickness);

                g.fillRect(
                        (int) ((posX - width / 2.0 + width * (1 - frameLength) * j) * boardPaintWidth + boardBaseXShift),
                        (int) ((posY - height / 2.0 + height * (1 - frameThickness) * i) * boardPaintHeight + boardBaseYShift),
                        (w > 0) ? w : 1,
                        (h > 0) ? h : 1
                );

                w = (int) (width * boardPaintWidth * frameThickness);
                h = (int) (height * boardPaintHeight * frameLength);

                g.fillRect(
                        (int) ((posX - width / 2.0 + width * (1 - frameThickness) * j) * boardPaintWidth + boardBaseXShift),
                        (int) ((posY - height / 2.0 + height * (1 - frameLength) * i) * boardPaintHeight + boardBaseYShift),
                        (w > 0) ? w : 1,
                        (h > 0) ? h : 1

                );
            }
        }
    }

    private void printDebugLog(Graphics g) {
        debugCounter = 0;
        Font f = g.getFont();
        g.setFont(new Font(f.getName(), Font.PLAIN, f.getSize() * 3 / 2));

        printString(g, "mouse", new double[]{mouseXTang, mouseYTang});
        printString(g, "board image tanget", new double[]{boardImageTangent});
        printString(g, "board paint size", new double[]{boardPaintWidth, boardPaintHeight});
        printString(g, "board panel size", new double[]{this.getWidth(), this.getHeight()});
        printString(g, "selected Point: ", new double[]{
                ui.getCallback().getSelectedPointOrPiece() == null ? -1 : ui.getCallback().getSelectedPointOrPiece().getId()
        });
    }

    private void drawCurrentPieces(Graphics g) {
        for (Point point : board.getPoints()) {
            Piece piece = point.getPiece();

            if (piece != null) {
                double pieceWidth = piece.getWidth(),
                        pieceHeight = piece.getHeight();

                g.drawImage(
                        piece.getPieceImage(),
                        (int) (((point.getPosX() - pieceWidth * 0.5) * boardPaintWidth) + boardBaseXShift),
                        (int) (((point.getPosY() - pieceHeight * 0.5) * boardPaintHeight) + boardBaseYShift),
                        (int) (pieceWidth * boardPaintWidth),
                        (int) (pieceHeight * boardPaintHeight),
                        null);
            }
        }
    }

    private void drawPiecePlacingPoints(Graphics g, ArrayList<Point>points, Color color, boolean showID) {
        for (Point p : points) {
            if(p != null) {
                int
                        w = (int) (p.getWidth() * boardPaintWidth),
                        h = (int) (p.getHeight() * boardPaintHeight),
                        x = (int) ((p.getPosX() - p.getWidth() / 2) * boardPaintWidth + boardBaseXShift),
                        y = (int) ((p.getPosY() - p.getHeight() / 2) * boardPaintHeight + boardBaseYShift);

                g.setColor(color);
                g.fillRect(x, y, w, h);
                g.setColor(Color.green);
                if (showID) g.drawString(p.getId() + "", x + w / 2, y + h / 2);
            }
        }
    }

    public void notifyWindowResized() {
        gamePanelTangent = (double) this.getHeight() / this.getWidth();
        if (boardImage != null && gamePanelTangent != boardImageTangent) {
            if (gamePanelTangent > boardImageTangent) {
                boardBaseXShift = 0;
                boardPaintWidth = this.getWidth();
                boardPaintHeight = (int) (boardPaintWidth * boardImageTangent);
                boardBaseYShift = (this.getHeight() - boardPaintHeight) / 2;
            } else {
                boardBaseYShift = 0;
                boardPaintHeight = this.getHeight();
                boardPaintWidth = (int) (boardPaintHeight / boardImageTangent);
                boardBaseXShift = (this.getWidth() - boardPaintWidth) / 2;
            }
        } else {
            boardBaseXShift = 0;
            boardBaseYShift = 0;
            boardPaintHeight = this.getHeight();
            boardPaintWidth = this.getWidth();
        }
    }

    private MouseAdapter handleMouseClick() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseXTang = ((double) e.getX() - boardBaseXShift) / boardPaintWidth;
                mouseYTang = ((double) e.getY() - boardBaseYShift) / boardPaintHeight;

                Point selectedPoint = checkThatPointOrPieceClicked();

                if (selectedPoint != null && selectedPoint.getPiece() != null)
                    ui.getCallback().onPieceOnPointSelected(selectedPoint);
                else if (selectedPoint != null)
                    ui.getCallback().onPointSelected(selectedPoint);
                else
                    ui.getCallback().onPointSelected(null);

                ui.refreshWindow();
            }
        };
    }

    private Point checkThatPointOrPieceClicked() {
        if (board == null ) return null;
        for (Point p : board.getPoints()) {
            Piece pieceOnPoint = p.getPiece();

            if ((
                    pieceOnPoint != null &&
                            p.getPosX() - pieceOnPoint.getWidth() * 0.5 <= mouseXTang &&
                            p.getPosX() + pieceOnPoint.getWidth() * 0.5 >= mouseXTang &&
                            p.getPosY() - pieceOnPoint.getHeight() * 0.5 <= mouseYTang &&
                            p.getPosY() + pieceOnPoint.getHeight() * 0.5 >= mouseYTang)
                    ||
                    (p.getPosX() - p.getWidth() * 0.5 <= mouseXTang &&
                            p.getPosX() + p.getWidth() * 0.5 >= mouseXTang &&
                            p.getPosY() - p.getHeight() * 0.5 <= mouseYTang &&
                            p.getPosY() + p.getHeight() * 0.5 >= mouseYTang))
                return p;
        }
        return null;
    }

    public double getBoardTangent() {
        if (boardImage == null)
            return 1.0 * this.getHeight() / this.getWidth();
        else
            return boardImageTangent;
    }

    public void setBoard(Board b) {
        board = b;
        ui.refreshWindow();
    }

    public boolean isBoardImageNotSet(){
        return boardImage == null;
    }
}