package Executable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Executable.BoardModel.Board;
import Executable.BoardModel.Point;
import Executable.PieceModel.Advisor;
import Executable.PieceModel.Cannon;
import Executable.PieceModel.Chariot;
import Executable.PieceModel.Elephant;
import Executable.PieceModel.General;
import Executable.PieceModel.Horse;
import Executable.PieceModel.Piece;
import Executable.PieceModel.Soldier;
import Executable.UIHandlerModel.UIHandler;

public class Game {
    Board board;
    AI ai;

    boolean canCapture;
    ArrayList<String> sides = new ArrayList<>();

    String currentSide;
    ArrayList<Piece> currPieces;
    ArrayList<Node> history;

    Piece selectedPiece;
    Point selectedPoint;

    UIHandler ui;
    String gameLocation;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        // set location
        gameLocation = (new File(Game.class.getClassLoader().getResource("").getPath())).getAbsolutePath();

        // set board
        board = new Board();
        board.setImageLink("pic/board.png");

        // set chess type
        canCapture = true;

        sides.add(DataAndSetting.PieceData.PlayerSide.RED);
        sides.add(DataAndSetting.PieceData.PlayerSide.BLACK);

        ai = new AI(this);

        ui = new UIHandler(handleUIEventCallBack());
        addDataToInfoPanel();
        ui.setBoard(board);
        ui.updateStatusBarStatus("Select start side and start game");
    }

    private void addPointsPiecesEdgesToBoard() {
        int row = 10, column = 9;

        double baseX = 0.05569007263922518, baseY = 0.04216216216216216,
                diffX = (0.9382566585956417 - baseX) / (column - 1), diffY = (0.96 - baseY) / (row - 1), radius = 0.2,
                boardTangent = ui.getBoardTangent();

        for (int i = 0; i < DataAndSetting.PointEdgeData.boardPointsArray.length; i++)
            board.addPoint(DataAndSetting.PointEdgeData.boardPointsArray[i]);
        /*for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                board.addPoint(baseX + diffX * i, baseY + diffY * j, radius * 2 * diffX,
                        radius * 2 * diffX / boardTangent);
            }
        }*/
        for (int i = 0; i < DataAndSetting.PointEdgeData.pointEdgeRelations.length; i++) {
            DataAndSetting.PointEdgePackage dataPackage = DataAndSetting.PointEdgeData.pointEdgeRelations[i];
            board.getPointByID(dataPackage.sourceID).addEdge(dataPackage.edgeDirection, board.getPointByID(dataPackage.targetID));
        }
        /*for (int i = 0; i < 90; i++) {
            if (i / 10 < 8) {
                board.getPointByID(i).addEdge(Direction.EAST, board.getPointByID(i + 10));
            }
            if (i / 10 > 0) {
                board.getPointByID(i).addEdge(Direction.WEST, board.getPointByID(i - 10));
            }
            if (i % 10 < 9) {
                board.getPointByID(i).addEdge(Direction.SOUTH, board.getPointByID(i + 1));
            }
            if (i % 10 > 0) {
                board.getPointByID(i).addEdge(Direction.NORTH, board.getPointByID(i - 1));
            }
            if (i == 41 || i == 48) {
                board.getPointByID(i).addEdge(Direction.NORTH_EAST, board.getPointByID(i + 9));
                board.getPointByID(i).addEdge(Direction.NORTH_WEST, board.getPointByID(i - 11));
                board.getPointByID(i).addEdge(Direction.SOUTH_EAST, board.getPointByID(i + 11));
                board.getPointByID(i).addEdge(Direction.SOUTH_WEST, board.getPointByID(i - 9));
            }
            if (i == 30 || i == 37) {
                board.getPointByID(i).addEdge(Direction.SOUTH_EAST, board.getPointByID(i + 11));
            }
            if (i == 32 || i == 39) {
                board.getPointByID(i).addEdge(Direction.NORTH_EAST, board.getPointByID(i + 9));
            }
            if (i == 50 || i == 57) {
                board.getPointByID(i).addEdge(Direction.SOUTH_WEST, board.getPointByID(i - 9));
            }
            if (i == 52 || i == 59) {
                board.getPointByID(i).addEdge(Direction.NORTH_WEST, board.getPointByID(i - 11));
            }
        }*/

        currPieces = new ArrayList<Piece>();

        for (int i=0;i<DataAndSetting.PieceData.initialPiecePlacingData.length;i++){
            DataAndSetting.PieceDataPackage dataPackage = DataAndSetting.PieceData.initialPiecePlacingData[i];
            Piece newPiece = DataAndSetting.PieceData.makeStandardPiece(dataPackage.pieceType, dataPackage.playerSide);
            board.getPointByID(dataPackage.pointID).setPiece(newPiece);
            currPieces.add(newPiece);
        }
        /*for (int i = 0; i < 90; i++) {
            double squareConst = 0.8;
            switch (i) {
                case 0:
                case 80:
                    Piece rc = new Chariot(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_chariot.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u8eca");
                    board.getPointByID(i).setPiece(rc);
                    currPieces.add(rc);
                    break;
                case 10:
                case 70:
                    Piece rh = new Horse(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_horse.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u99ac");
                    board.getPointByID(i).setPiece(rh);
                    currPieces.add(rh);
                    break;
                case 20:
                case 60:
                    Piece re = new Elephant(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_elephant.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u8c61");
                    board.getPointByID(i).setPiece(re);
                    currPieces.add(re);
                    break;
                case 30:
                case 50:
                    Piece ra = new Advisor(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_advisor.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u58eb");
                    board.getPointByID(i).setPiece(ra);
                    currPieces.add(ra);
                    break;
                case 40:
                    Piece rg = new General(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_general.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u5c07");
                    board.getPointByID(i).setPiece(rg);
                    currPieces.add(rg);
                    break;
                case 12:
                case 72:
                    Piece rp = new Cannon(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_cannon.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u7832");
                    board.getPointByID(i).setPiece(rp);
                    currPieces.add(rp);
                    break;
                case 3:
                case 23:
                case 43:
                case 63:
                case 83:
                    Piece rs = new Soldier(DataAndSetting.PieceData.PlayerSide.BLACK, "pic/black_soldier.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u5352");
                    board.getPointByID(i).setPiece(rs);
                    currPieces.add(rs);
                    break;

                case 9:
                case 89:
                    Piece bc = new Chariot(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_chariot.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u4fe5");
                    board.getPointByID(i).setPiece(bc);
                    currPieces.add(bc);
                    break;
                case 19:
                case 79:
                    Piece bh = new Horse(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_horse.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u508c");
                    board.getPointByID(i).setPiece(bh);
                    currPieces.add(bh);
                    break;
                case 29:
                case 69:
                    Piece be = new Elephant(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_elephant.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u76f8");
                    board.getPointByID(i).setPiece(be);
                    currPieces.add(be);
                    break;
                case 39:
                case 59:
                    Piece ba = new Advisor(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_advisor.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u4ed5");
                    board.getPointByID(i).setPiece(ba);
                    currPieces.add(ba);
                    break;
                case 49:
                    Piece bg = new General(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_general.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u5e25");
                    board.getPointByID(i).setPiece(bg);
                    currPieces.add(bg);
                    break;
                case 17:
                case 77:
                    Piece bp = new Cannon(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_cannon.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u70ae");
                    board.getPointByID(i).setPiece(bp);
                    currPieces.add(bp);
                    break;
                case 6:
                case 26:
                case 46:
                case 66:
                case 86:
                    Piece bs = new Soldier(DataAndSetting.PieceData.PlayerSide.RED, "pic/red_soldier.png", diffX * squareConst,
                            diffX * squareConst / boardTangent, "\u5175");
                    board.getPointByID(i).setPiece(bs);
                    currPieces.add(bs);
                    break;
            }
        }*/

        ui.refreshWindow();
    }

    private UIHandler.EventCallBackHandler handleUIEventCallBack() {
        return new UIHandler.EventCallBackHandler() {
            public void onMenuBarItemClicked(UIHandler.MenubarMessage msg) {
                handleMenuBarMessage(msg);
            }

            @Override
            public void onCancelMovement() {
                // ui.updateStatusBarStatus("cancel movement");
            }

            @Override
            public void onConfirmMovement() {
                // ui.updateStatusBarStatus("confirm movement");
                // ui.addMovementHistoryRecord("confirmed move");
            }

            @Override
            public void onStartGame(String playerSide) {
                // define starter side
                ui.updateStatusBarStatus("Start game: " + playerSide);
                switch (playerSide) {
                    case "Player 1":
                        currentSide = DataAndSetting.PieceData.PlayerSide.RED;
                        break;
                    case "Computer":
                        currentSide = DataAndSetting.PieceData.PlayerSide.BLACK;
                        break;
                }

                addPointsPiecesEdgesToBoard();
                ui.setStatusBarButtonsEnabled(true);

                history = new ArrayList<>();
                Map<Integer, Integer> state = new HashMap<>();
                for (Point everyPoint : board.getPoints()) {
                    if (everyPoint.getPiece() != null) {
                        state.put(everyPoint.getId(), everyPoint.getPiece().getId());
                    }
                }
                history.add(new Node(state, currentSide));
            }

            @Override
            public Point getSelectedPointOrPiece() {
                return selectedPoint;
            }

            @Override
            public ArrayList<Point> getPieceNextMovePointCandidateList() {
                if (selectedPoint != null && selectedPiece != null)
                    return board.getSelectedPieceMovable();
                else
                    return null;
            }

            @Override
            public void onPointSelected(Point point) {
                if (selectedPiece != null && point != null) {
                    if (board.getSelectedPieceMovable().contains(point)) {
                        // move
                        board.movePiece(selectedPiece, selectedPoint, point);
                        System.out.println("Move!");

                        // change side
                        currentSide = sides.get((sides.indexOf(currentSide) + 1) % sides.size());

                        // update move history and status bar
                        ui.updateStatusBarStatus(board.getMoveString(selectedPiece, selectedPoint, point));
                        ui.addMovementHistoryRecord(board.getMoveString(selectedPiece, selectedPoint, point));
                        Map<Integer, Integer> state = new HashMap<>();
                        for (Point everyPoint : board.getPoints()) {
                            if (everyPoint.getPiece() != null) {
                                state.put(everyPoint.getId(), everyPoint.getPiece().getId());
                            }
                        }
                        history.add(
                                new Node(state, currentSide, board.getMoveString(selectedPiece, selectedPoint, point),
                                        history.get(history.size() - 1)));

                        // reset point and piece
                        selectedPoint = null;
                        selectedPiece = null;
                    } else {
                        selectedPoint = point;
                    }
                } else {
                    selectedPiece = null;
                    selectedPoint = point;
                }
            }

            @Override
            public void onPieceOnPointSelected(Point point) {
                if (selectedPiece != null && canCapture && board.getSelectedPieceMovable().contains(point)) {
                    // capture
                    board.capture(selectedPiece, selectedPoint, point);
                    System.out.println("Capture!");

                    // change side
                    currentSide = sides.get((sides.indexOf(currentSide) + 1) % sides.size());

                    // update move history and status bar
                    ui.updateStatusBarStatus(board.getMoveString(selectedPiece, selectedPoint, point));
                    ui.addMovementHistoryRecord(board.getMoveString(selectedPiece, selectedPoint, point));
                    Map<Integer, Integer> state = new HashMap<>();
                    for (Point everyPoint : board.getPoints()) {
                        if (everyPoint.getPiece() != null) {
                            state.put(everyPoint.getId(), everyPoint.getPiece().getId());
                        }
                    }
                    history.add(new Node(state, currentSide, board.getMoveString(selectedPiece, selectedPoint, point),
                            history.get(history.size() - 1)));

                    // reset point and piece
                    selectedPiece = null;
                    selectedPoint = null;
                } else {
                    board.updateSelectedPieceMovable(point, currentSide);
                    selectedPiece = point.getPiece();
                    selectedPoint = point;
                }
            }

            @Override
            public void onUndo(int undoStep) {
                Node nodeGoingBackTo = history.get(history.size() - 1 - undoStep);

                currentSide = nodeGoingBackTo.getState().getSide();
                for (Point point : board.getPoints()) {
                    if (nodeGoingBackTo.getState().getPointsState().containsKey(point.getId())) {
                        point.setPiece(currPieces.get(nodeGoingBackTo.getState().getPointsState().get(point.getId())));
                    } else {
                        point.setPiece(null);
                    }
                }

                for (int i = 0; i < undoStep; i++) {
                    history.remove(history.size() - 1);
                }

                ui.refreshWindow();
            }
        };
    }

    private void handleMenuBarMessage(UIHandler.MenubarMessage msg) {
        switch (msg) {
            case MENUITEM_WINDOW_CLOSING:
                programTerminate();
                break;
            case MENUITEM_NEW_GAME:
                ui.updateStatusBarStatus("New game");
                break;
            case MENUITEM_LOAD_GAME:
                ui.updateStatusBarStatus("Load game");
                break;
            case MENUITEM_SAVE_GAME:
                ui.updateStatusBarStatus("Save game");
                break;
            case MENUITEM_STEP_UNDO:
                ui.updateStatusBarStatus("Step undo");
                break;
            case MENUITEM_STEP_REDO:
                ui.updateStatusBarStatus("Step redo");
                break;
            case MENUITEM_GAME_DISTRIBUTE_COMPUTING:
                ui.updateStatusBarStatus("Distributed computing");
                break;
            case MENUITEM_VIEW_SHOW_DEBUG:
                ui.updateStatusBarStatus("Show Debug: " + ui.getIsShowDebug());
                break;
            case MENUITEM_VIEW_SHOW_PIECE_PLACING_POINT:
                ui.updateStatusBarStatus("Show Piece Placing Point: " + ui.getIsShowPiecePlacingPoint());
                break;
            case MENUITEM_VIEW_DETAIL_SYSTEM_INFO:
                ui.updateStatusBarStatus("Detailed system information");
                break;
            case MENUITEM_VIEW_AI_THINKING_STEP:
                ui.updateStatusBarStatus("AI thinking step");
                break;
            case MENUITEM_VIEW_GAME_TREE:
                ui.updateStatusBarStatus("Game tree");
                break;
            case MENUITEM_HELP_ABOUT:
                ui.updateStatusBarStatus("About");
                break;
            case MENUITEM_HELP_TUTORIAL:
                ui.updateStatusBarStatus("Tutorial");
                break;
            case MENUITEM_VIEW_FIT_WINDOW:
                ui.fitWindow();
                break;
        }
    }

    private void programTerminate() {
        System.exit(0);
    }

    private void addDataToInfoPanel() {
        String AIName = "Computer", playerName = "Player 1";

        ui.infoPanelUpdatePlayerSideData(DataAndSetting.PieceData.PlayerSide.RED, AIName);
        ui.infoPanelUpdatePlayerSideData(DataAndSetting.PieceData.PlayerSide.BLACK, playerName);

        ui.infoPanelUpdatePlayerRemainingTimeData(playerName, "5:00\"00");
        ui.infoPanelUpdatePlayerRemainingTimeData(AIName, "5:00\"00");

        String[][] systemData = new String[][]{{"CPU Time", "2:50\"00"}, {"No. Threads", "4"},
                {"Peek RAM", "1536 MB"}, {"Curr. RAM", "64 MB"}, {"Calc. Steps", "1048576"},
                {"1 Step Time", "0.162 ms"}};
        for (String[] item : systemData)
            ui.infoPanelUpdateSystemInfoData(item[0], item[1]);
    }
}