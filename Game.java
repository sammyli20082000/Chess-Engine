import java.io.File;
import java.util.ArrayList;

import BoardModel.Board;
import BoardModel.Edge.Direction;
import BoardModel.Point;
import PieceModel.Advisor;
import PieceModel.Cannon;
import PieceModel.Chariot;
import PieceModel.Elephant;
import PieceModel.General;
import PieceModel.Horse;
import PieceModel.Piece;
import PieceModel.Soldier;
import UIHandlerModel.UIHandler;

public class Game {
	Board board;
	AI ai;
	boolean canCapture;
	ArrayList<String> sides = new ArrayList<>();
	String currentSide;
	ArrayList<Piece> currPieces;
	Piece selectedPiece;
	Point selectedPoint;
	Node tree_root;
	UIHandler ui;
	String myLocation;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		//set location
		myLocation = (new File(Game.class.getClassLoader().getResource("").getPath())).getAbsolutePath();
		
		//set board
		board = new Board();
		board.setImageLink("pic/board.png");
		
		//set chess type
		canCapture = true;
		
		sides.add(Piece.PlayerSide.RED);
		sides.add(Piece.PlayerSide.BLACK);
		
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

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				board.addPoint(baseX + diffX * i, baseY + diffY * j, radius * 2 * diffX,
						radius * 2 * diffX / boardTangent);
			}
		}

		board.getPointByID(43).addEdge(Direction.SOUTH, board.getPointByID(44));
		for (int i = 0; i < 90; i++) {
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
		}

		currPieces = new ArrayList<Piece>();

		for (int i = 0; i < 90; i++) {
			double squareConst = 0.8;
			switch (i) {
			case 0:
			case 80:
				Piece rc = new Chariot(Piece.PlayerSide.BLACK, "pic/black_chariot.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(rc);
				currPieces.add(rc);
				break;
			case 10:
			case 70:
				Piece rh = new Horse(Piece.PlayerSide.BLACK, "pic/black_horse.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(rh);
				currPieces.add(rh);
				break;
			case 20:
			case 60:
				Piece re = new Elephant(Piece.PlayerSide.BLACK, "pic/black_elephant.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(re);
				currPieces.add(re);
				break;
			case 30:
			case 50:
				Piece ra = new Advisor(Piece.PlayerSide.BLACK, "pic/black_advisor.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(ra);
				currPieces.add(ra);
				break;
			case 40:
				Piece rg = new General(Piece.PlayerSide.BLACK, "pic/black_general.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(rg);
				currPieces.add(rg);
				break;
			case 12:
			case 72:
				Piece rp = new Cannon(Piece.PlayerSide.BLACK, "pic/black_cannon.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(rp);
				currPieces.add(rp);
				break;
			case 3:
			case 23:
			case 43:
			case 63:
			case 83:
				Piece rs = new Soldier(Piece.PlayerSide.BLACK, "pic/black_soldier.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(rs);
				currPieces.add(rs);
				break;

			case 9:
			case 89:
				Piece bc = new Chariot(Piece.PlayerSide.RED, "pic/red_chariot.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(bc);
				currPieces.add(bc);
				break;
			case 19:
			case 79:
				Piece bh = new Horse(Piece.PlayerSide.RED, "pic/red_horse.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(bh);
				currPieces.add(bh);
				break;
			case 29:
			case 69:
				Piece be = new Elephant(Piece.PlayerSide.RED, "pic/red_elephant.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(be);
				currPieces.add(be);
				break;
			case 39:
			case 59:
				Piece ba = new Advisor(Piece.PlayerSide.RED, "pic/red_advisor.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(ba);
				currPieces.add(ba);
				break;
			case 49:
				Piece bg = new General(Piece.PlayerSide.RED, "pic/red_general.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(bg);
				currPieces.add(bg);
				break;
			case 17:
			case 77:
				Piece bp = new Cannon(Piece.PlayerSide.RED, "pic/red_cannon.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(bp);
				currPieces.add(bp);
				break;
			case 6:
			case 26:
			case 46:
			case 66:
			case 86:
				Piece bs = new Soldier(Piece.PlayerSide.RED, "pic/red_soldier.png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				board.getPointByID(i).setPiece(bs);
				currPieces.add(bs);
				break;
			}
		}

		ui.refreshWindow();
	}

	private UIHandler.EventCallBackHandler handleUIEventCallBack() {
		return new UIHandler.EventCallBackHandler() {
			public void onMenuBarItemClicked(UIHandler.MenubarMessage msg) {
				handleMenuBarMessage(msg);
			}

			@Override
			public void onPointSelected(Point point) {
				if (selectedPiece != null && point != null) {
					if (board.getSelectedPieceMovable().contains(point)) {
						board.movePiece(selectedPiece, selectedPoint, point);
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
			public void onCancelMovement() {
				ui.updateStatusBarStatus("cancel movement");
			}

			@Override
			public void onConfirmMovement() {
				ui.updateStatusBarStatus("confirm movement");
				ui.addMovementHistoryRecord("confirmed move");
			}

			@Override
			public void onStartGame(String playerSide) {
				ui.updateStatusBarStatus("Start game: " + playerSide);
				addPointsPiecesEdgesToBoard();
				ui.setStatusBarButtonsEnabled(true);
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
			public void onPieceOnPointSelected(Point point) {
				if (selectedPiece != null && canCapture && board.getSelectedPieceMovable().contains(point)) {
					board.capture(selectedPiece, selectedPoint, point);
					selectedPiece = null;
					selectedPoint = null;
				} else {
					board.updateSelectedPieceMovable(point);
					selectedPiece = point.getPiece();
					selectedPoint = point;
				}
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

		ui.infoPanelUpdatePlayerSideData(Piece.PlayerSide.RED, AIName);
		ui.infoPanelUpdatePlayerSideData(Piece.PlayerSide.BLACK, playerName);

		ui.infoPanelUpdatePlayerRemainingTimeData(playerName, "5:00\"00");
		ui.infoPanelUpdatePlayerRemainingTimeData(AIName, "5:00\"00");

		String[][] systemData = new String[][] { { "CPU Time", "2:50\"00" }, { "No. Threads", "4" },
				{ "Peek RAM", "1536 MB" }, { "Curr. RAM", "64 MB" }, { "Calc. Steps", "1048576" },
				{ "1 Step Time", "0.162 ms" } };
		for (String[] item : systemData)
			ui.infoPanelUpdateSystemInfoData(item[0], item[1]);
	}
}
