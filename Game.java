import java.io.File;
import java.util.ArrayList;

import BoardModel.Board;
import BoardModel.Point;
import PieceModel.Piece;
import PieceModel.TempPiece;
import UIHandlerModel.UIHandler;

public class Game {
    Board board;
    AI ai;
    ArrayList<Piece> currePieces;
    Piece selcetedPiece;
    Point selectedPoint;
    Node tree_root;
    UIHandler ui;
    String myLocation;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        myLocation =
                (new File(Game.class.getClassLoader().getResource("").getPath()))
                        .getAbsolutePath();
        board = new Board();
        board.setImageLink("pic/board.png");
        addPointsAndPieceToBoard();
        ai = new AI(this);
        ui = new UIHandler(new UIHandler.eventCallBack() {
            public void onMenuBarItemClicked(UIHandler.MenubarMessage msg) {
                handleMenuBarMessage(msg);
            }

            @Override
            public Board getBoard() {
                return board;
            }

            @Override
            public void onPointSelected(Point point) {
                selectedPoint = point;
            }

            @Override
            public void onPieceSelected(Piece piece) {
                selcetedPiece = piece;
            }

            @Override
            public ArrayList<Piece> getCurrentPieces() {
                return currePieces;
            }

            @Override
            public Piece getSelectedPiece() {
                return selcetedPiece;
            }

            @Override
            public Point getSelectedPoint() {
                return selectedPoint;
            }

        });
    }

    private void addPointsAndPieceToBoard() {
        int row = 10, column = 9;
        double
                baseX = 0.05569007263922518,
                baseY = 0.04216216216216216,
                diffX = (0.9382566585956417 - baseX) / (column - 1),
                diffY = (0.96 - baseY) / (row - 1),
                radius = 0.2;

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                board.addPoint(baseX + diffX * i, baseY + diffY * j, radius * 2 * diffX, radius * 2 * diffY);
            }
        }

        String chessName[] = new String[]{
                "general", "chariot", "horse", "elephant", "advisor", "cannon", "soldier"
        };
        currePieces = new ArrayList<Piece>();

        for(int i=0; i< chessName.length ; i++){
            TempPiece rP, bP;
            double squareConst = 0.8;
            rP = new TempPiece(Piece.PlayerSide.RED, "pic/red_"+chessName[i]+".png", diffX*squareConst, diffY*squareConst);
            bP = new TempPiece(Piece.PlayerSide.BLACK, "pic/black_"+chessName[i]+".png", diffX*squareConst, diffY*squareConst);
            currePieces.add(rP);
            currePieces.add(bP);

            if(i==0){
                board.getPointByID(40).setPiece(rP);
                board.getPointByID(49).setPiece(bP);
            }else if (i == chessName.length-1){
                for (int j=0; j< 5;j++){
                    board.getPointByID(3+20*j).setPiece(rP);
                    board.getPointByID(6+20*j).setPiece(bP);
                }
            }else if (i == chessName.length-2){
                for (int j=0; j<2; j++){
                    board.getPointByID(12+60*j).setPiece(rP);
                    board.getPointByID(17+60*j).setPiece(bP);
                }
            }else {
                for (int j = 0; j < 2; j++) {
                    board.getPointByID(0+10*(i-1)+(100-20*i)*j).setPiece(rP);
                    board.getPointByID(9+10*(i-1)+(100-20*i)*j).setPiece(bP);
                }
            }
        }
    }

    private void handleMenuBarMessage(UIHandler.MenubarMessage msg) {
        switch (msg) {
            case MENUITEM_WINDOW_CLOSING:
                programTerminate();
                break;
            case MENUITEM_NEW_GAME:
                ui.updateStatus("New game");
                break;
            case MENUITEM_LOAD_GAME:
                ui.updateStatus("Load game");
                break;
            case MENUITEM_SAVE_GAME:
                ui.updateStatus("Save game");
                break;
            case MENUITEM_STEP_UNDO:
                ui.updateStatus("Step undo");
                break;
            case MENUITEM_STEP_REDO:
                ui.updateStatus("Step redo");
                break;
            case MENUITEM_GAME_DISTRIBUTE_COMPUTING:
                ui.updateStatus("Distributed computing");
                break;
            case MENUITEM_VIEW_DETAIL_SYSTEM_INFO:
                ui.updateStatus("Detailed system information");
                break;
            case MENUITEM_VIEW_AI_THINKING_STEP:
                ui.updateStatus("AI thinking step");
                break;
            case MENUITEM_VIEW_GAME_TREE:
                ui.updateStatus("Game tree");
                break;
            case MENUITEM_HELP_ABOUT:
                ui.updateStatus("About");
                break;
            case MENUITEM_HELP_TUTORIAL:
                ui.updateStatus("Tutorial");
                break;
            case MENUITEM_VIEW_FIT_WINDOW:
                ui.fitWindow();
                break;
        }
    }

    private void programTerminate() {
        System.exit(0);
    }
}
