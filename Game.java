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
import PieceModel.Soldier;
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
		}

		// for (int i = 0; i < points.size(); i++) {
		// Point p = points.get(i);
		// if (i / 10 == 0) {
		// if (i % 10 == 0) {
		// p.addEdge(Direction.SOUTH, points.get(i + 1));
		// } else if (i % 10 == 9) {
		// p.addEdge(Direction.NORTH, points.get(i - 1));
		// } else {
		// p.addEdge(Direction.SOUTH, points.get(i + 1));
		// p.addEdge(Direction.NORTH, points.get(i - 1));
		// }
		// p.addEdge(Direction.EAST, points.get(i + 10));
		// } else if (i / 10 == 8) {
		// if (i % 10 == 0) {
		// p.addEdge(Direction.SOUTH, points.get(i + 1));
		// } else if (i % 10 == 9) {
		// p.addEdge(Direction.NORTH, points.get(i - 1));
		// } else {
		// p.addEdge(Direction.SOUTH, points.get(i + 1));
		// p.addEdge(Direction.NORTH, points.get(i - 1));
		// }
		// p.addEdge(Direction.WEST, points.get(i - 10));
		// } else if (i % 10 == 0) {
		// if (!(i / 10 == 0) && !(i / 10 == 8)) {
		// p.addEdge(Direction.SOUTH, points.get(i + 1));
		// p.addEdge(Direction.EAST, points.get(i + 10));
		// p.addEdge(Direction.WEST, points.get(i - 10));
		// }
		// } else if (i % 10 == 9) {
		// if (!(i / 10 == 0) && !(i / 10 == 8)) {
		// continue;
		// } else {
		// p.addEdge(Direction.NORTH, points.get(i - 1));
		// p.addEdge(Direction.EAST, points.get(i + 10));
		// p.addEdge(Direction.WEST, points.get(i - 10));
		// }
		// }
		// }

		// String chessName[] = new String[] { "general", "chariot", "horse",
		// "elephant", "advisor", "cannon", "soldier" };
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
				Piece bg = new General(Piece.PlayerSide.RED, "pic/red_General.png", diffX * squareConst,
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

	// for (int i = 0; i < chessName.length; i++) {
	// Piece rP = null, bP = null;
	// double squareConst = 0.8;
	//
	// switch (i) {
	// case 0:
	// rP = new General(Piece.PlayerSide.RED, "pic/red_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new General(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// case 1:
	// rP = new Chariot(Piece.PlayerSide.RED, "pic/red_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new Chariot(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// case 2:
	// rP = new Horse(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png",
	// diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new Horse(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// case 3:
	// rP = new Elephant(Piece.PlayerSide.RED, "pic/red_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new Elephant(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// case 4:
	// rP = new Advisor(Piece.PlayerSide.RED, "pic/red_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new Advisor(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// case 5:
	// rP = new Cannon(Piece.PlayerSide.RED, "pic/red_" + chessName[i] + ".png",
	// diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new Cannon(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// case 6:
	// rP = new Soldier(Piece.PlayerSide.RED, "pic/red_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// bP = new Soldier(Piece.PlayerSide.BLACK, "pic/black_" + chessName[i] +
	// ".png", diffX * squareConst,
	// diffX * squareConst / boardTangent);
	// break;
	// }
	//
	// currPieces.add(rP);
	// currPieces.add(bP);
	//
	// if (i == 0) {
	// board.getPointByID(40).setPiece(rP);
	// board.getPointByID(49).setPiece(bP);
	// } else if (i == chessName.length - 1) {
	// for (int j = 0; j < 5; j++) {
	// board.getPointByID(3 + 20 * j).setPiece(rP);
	// board.getPointByID(6 + 20 * j).setPiece(bP);
	// }
	// } else if (i == chessName.length - 2) {
	// for (int j = 0; j < 2; j++) {
	// board.getPointByID(12 + 60 * j).setPiece(rP);
	// board.getPointByID(17 + 60 * j).setPiece(bP);
	// }
	// } else {
	// for (int j = 0; j < 2; j++) {
	// board.getPointByID(0 + 10 * (i - 1) + (100 - 20 * i) * j).setPiece(rP);
	// board.getPointByID(9 + 10 * (i - 1) + (100 - 20 * i) * j).setPiece(bP);
	// }
	// }
	// }
	// ui.refreshWindow();
	// }

	private UIHandler.eventCallBack handleUIEventCallBack() {
		return new UIHandler.eventCallBack() {
			public void onMenuBarItemClicked(UIHandler.MenubarMessage msg) {
				handleMenuBarMessage(msg);
			}

			@Override
			public void onPointSelected(Point point) {
				if (selectedPiece != null && point != null) {
					if (board.getSelectedPieceMovable().contains(point)) {
						point.setPiece(selectedPiece);
						selectedPoint.setPiece(null);
					}
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
				board.updateSelectedPieceMovable(point);
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
