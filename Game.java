import java.io.File;
import java.util.ArrayList;

import BoardModel.Board;
import BoardModel.Edge;
import BoardModel.Edge.Direction;
import BoardModel.Point;
import PieceModel.Advisor;
import PieceModel.Cannon;
import PieceModel.Chariot;
import PieceModel.Elephant;
import PieceModel.General;
import PieceModel.Horse;
import PieceModel.Piece;
import PieceModel.Solider;
import PieceModel.TempPiece;
import UIHandlerModel.UIHandler;

public class Game {
	Board board;
	AI ai;
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
		myLocation = (new File(Game.class.getClassLoader().getResource("").getPath())).getAbsolutePath();
		board = new Board();
		board.setImageLink("pic/board.png");
		ai = new AI(this);
		ui = new UIHandler(handleUIEventCallBack());
		addDataToInfoPanel();
		ui.updateStatusBarStatus("Select start side and start game");
	}

	private void addPointsPiecesEdgesToBoard() {
		int row = 10, column = 9;

		ArrayList<Point> points = new ArrayList<>();
		double baseX = 0.05569007263922518, baseY = 0.04216216216216216,
				diffX = (0.9382566585956417 - baseX) / (column - 1), diffY = (0.96 - baseY) / (row - 1), radius = 0.2,
				boardTangent = ui.getBoardTangent();

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				board.addPoint(baseX + diffX * i, baseY + diffY * j, radius * 2 * diffX,
						radius * 2 * diffX / boardTangent);
			}
		}

		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			if (i / 10 == 0) {
				if (i % 10 == 0) {
					p.addEdge(Direction.SOUTH, points.get(i + 1));
				} else if (i % 10 == 9) {
					p.addEdge(Direction.NORTH, points.get(i - 1));
				} else {
					p.addEdge(Direction.SOUTH, points.get(i + 1));
					p.addEdge(Direction.NORTH, points.get(i - 1));
				}
				p.addEdge(Direction.EAST, points.get(i + 10));
			} else if (i / 10 == 8) {
				if (i % 10 == 0) {
					p.addEdge(Direction.SOUTH, points.get(i + 1));
				} else if (i % 10 == 9) {
					p.addEdge(Direction.NORTH, points.get(i - 1));
				} else {
					p.addEdge(Direction.SOUTH, points.get(i + 1));
					p.addEdge(Direction.NORTH, points.get(i - 1));
				}
				p.addEdge(Direction.WEST, points.get(i - 10));
			} else if (i % 10 == 0) {
				if (!(i / 10 == 0) && !(i / 10 == 8)) {
					p.addEdge(Direction.SOUTH, points.get(i + 1));
					p.addEdge(Direction.EAST, points.get(i + 10));
					p.addEdge(Direction.WEST, points.get(i - 10));
				}
			} else if (i % 10 == 9) {
				if (!(i / 10 == 0) && !(i / 10 == 8)) {
					continue;
				} else {
					p.addEdge(Direction.NORTH, points.get(i - 1));
					p.addEdge(Direction.EAST, points.get(i + 10));
					p.addEdge(Direction.WEST, points.get(i - 10));
				}
			}
		}

		String chessName[] = new String[] { "general", "chariot", "horse", "elephant", "advisor", "cannon", "soldier" };
		currPieces = new ArrayList<Piece>();

		for (int i = 0; i < 90; i++) {
			switch (i) {
			case 0:
			case 80:
				break;
			case 10:
			case 70:
				break;
			case 20:
			case 60:
				break;
			case 30:
			case 50:
				break;
			case 40:
				break;
			//
			// case 12:
			// case
			}
		}

		for (int i = 0; i < chessName.length; i++) {
			Piece rP = null, bP = null;
			double squareConst = 0.8;

			switch (i) {
			case 0:
				rP = new General(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new General(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			case 1:
				rP = new Chariot(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new Chariot(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			case 2:
				rP = new Horse(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new Horse(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			case 3:
				rP = new Elephant(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new Elephant(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			case 4:
				rP = new Advisor(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new Advisor(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			case 5:
				rP = new Cannon(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new Cannon(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			case 6:
				rP = new Solider(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				bP = new Solider(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] + ".png", diffX * squareConst,
						diffX * squareConst / boardTangent);
				break;
			}

			currPieces.add(rP);
			currPieces.add(bP);

			if (i == 0) {
				board.getPointByID(40).setPiece(rP);
				board.getPointByID(49).setPiece(bP);
			} else if (i == chessName.length - 1) {
				for (int j = 0; j < 5; j++) {
					board.getPointByID(3 + 20 * j).setPiece(rP);
					board.getPointByID(6 + 20 * j).setPiece(bP);
				}
			} else if (i == chessName.length - 2) {
				for (int j = 0; j < 2; j++) {
					board.getPointByID(12 + 60 * j).setPiece(rP);
					board.getPointByID(17 + 60 * j).setPiece(bP);
				}
			} else {
				for (int j = 0; j < 2; j++) {
					board.getPointByID(0 + 10 * (i - 1) + (100 - 20 * i) * j).setPiece(rP);
					board.getPointByID(9 + 10 * (i - 1) + (100 - 20 * i) * j).setPiece(bP);
				}
			}
		}
		ui.refreshWindow();
	}

	private UIHandler.eventCallBack handleUIEventCallBack() {
		return new UIHandler.eventCallBack() {
			public void onMenuBarItemClicked(UIHandler.MenubarMessage msg) {
				handleMenuBarMessage(msg);
			}

			@Override
			public Board requestBoard() {
				return board;
			}

			@Override
			public void onPointSelected(Point point) {
				if (selectedPiece != null && point != null) {
					point.setPiece(selectedPiece);
					selectedPoint.setPiece(null);
				} else {
					selectedPiece = null;
				}
				selectedPoint = point;
			}

			@Override
			public void onCancelMovement() {
				ui.updateStatusBarStatus("cancel movement");
			}

			@Override
			public void onConfirmMovement() {
				ui.updateStatusBarStatus("confirm movement");
			}

			@Override
			public void onStartGame(String playerSide) {
				ui.updateStatusBarStatus("Start game: " + playerSide);
				addPointsPiecesEdgesToBoard();
				ui.setStatusBarButtonsEnabled(true);
			}

			@Override
			public void onPieceOnPointSelected(Point point) {
				selectedPoint = point;
				selectedPiece = point.getPiece();

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
