package Executable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Executable.BoardModel.Board;
import Executable.BoardModel.Point;
import Executable.FileHandlerModel.FileHandler;
import Executable.PieceModel.Piece;
import Executable.UIHandlerModel.UIHandler;

public class Game implements Serializable {
	Game game;
	Board board;
	AI ai;

	boolean canCapture = true;
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
		// set game
		game = this;

		// set location
		gameLocation = (new File(Game.class.getClassLoader().getResource("").getPath())).getAbsolutePath();

		// set board
		board = new Board();

		sides.add(DataAndSetting.PieceData.PlayerSide.player);
		sides.add(DataAndSetting.PieceData.PlayerSide.computer);

		ai = new AI(game);

		ui = new UIHandler(handleUIEventCallBack());

		addDataToInfoPanel();
		ui.setBoard(board);
		ui.updateStatusBarStatus("Select start side and start game");
	}

	private void addPointsPiecesEdgesToBoard() {
		// int row = 10, column = 9;
		//
		// double baseX = 0.05569007263922518, baseY = 0.04216216216216216,
		// diffX = (0.9382566585956417 - baseX) / (column - 1), diffY = (0.96 -
		// baseY) / (row - 1), radius = 0.2,
		// boardTangent = ui.getBoardTangent();

		for (int i = 0; i < DataAndSetting.PointEdgeData.boardPointsArray.length; i++) {
			DataAndSetting.PointEdgeData.PointDataPackage pack = DataAndSetting.PointEdgeData.boardPointsArray[i];
			board.addPoint(pack.xCoordinate, pack.yCoordinate, pack.width, pack.height); //should add id inside constructor
		}

		for (int i = 0; i < DataAndSetting.PointEdgeData.pointEdgeRelations.length; i++) {
			DataAndSetting.PointEdgePackage dataPackage = DataAndSetting.PointEdgeData.pointEdgeRelations[i];
			board.getPointByID(dataPackage.sourceID).addEdge(dataPackage.edgeDirection,
					board.getPointByID(dataPackage.targetID));
		}

		currPieces = new ArrayList<Piece>();

		for (int i = 0; i < DataAndSetting.PieceData.initialPiecePlacingData.length; i++) {
			DataAndSetting.PieceDataPackage dataPackage = DataAndSetting.PieceData.initialPiecePlacingData[i];
			Piece newPiece = DataAndSetting.PieceData.makeStandardPiece(dataPackage.pieceType, dataPackage.playerSide);
			board.getPointByID(dataPackage.pointID).setPiece(newPiece);
			currPieces.add(newPiece);
		}

		ui.refreshWindow();
	}

	private FileHandler.EventCallBackHandler handleFileEventCallBack() {
		return new FileHandler.EventCallBackHandler() {

			@Override
			public void newGame() {
				board = new Board();
				Point.resetIdCounter();
				currentSide = "";
				currPieces = null;
				ai = new AI(game);
				ui.setBoard(board);
				ui.enableStartGameButton();
				history = null;
				ui.restoreMovementHistoryList();
				selectedPiece = null;
				selectedPoint = null;
				ui.refreshWindow();
			}

			@Override
			public void saveGame() {
				FileOutputStream fout = null;
				ObjectOutputStream oos = null;
				try {
					fout = new FileOutputStream("save.chesshis");
					oos = new ObjectOutputStream(fout);

					oos.writeObject(history);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fout.close();
						oos.close();
					} catch (IOException e) {

					}
				}
			}

			@Override
			public void loadGame() {
				FileInputStream fin = null;
				ObjectInputStream ois = null;

				try {
					fin = new FileInputStream("save.chesshis");
					ois = new ObjectInputStream(fin);

					ArrayList<Node> oldHistory = (ArrayList<Node>) ois.readObject();

					newGame();
					handleUIEventCallBack().onStartGame(oldHistory.get(0).getState().getSide());

					for (Node n : oldHistory) {
						computerMakeMove(board.getPointByID(n.movedFromPointId), board.getPointByID(n.movedToPointId));
					}

					ui.refreshWindow();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fin.close();
						ois.close();
					} catch (Exception e) {

					}
				}

			}
		};
	}

	private UIHandler.EventCallBackHandler handleUIEventCallBack() {
		return new UIHandler.EventCallBackHandler() {
			public void onMenuBarItemClicked(UIHandler.MenubarMessage msg) {
				handleMenuBarMessage(msg);
			}

			@Override
			public void onCancelMovement() {

			}

			@Override
			public void onConfirmMovement() {

			}

			@Override
			public void onStartGame(String playerSide) {
				// define starter side
				ui.updateStatusBarStatus("Start game: " + playerSide);
				switch (playerSide) {
				case "Player 1":
					currentSide = DataAndSetting.PieceData.PlayerSide.player;
					break;
				case "Computer":
					currentSide = DataAndSetting.PieceData.PlayerSide.computer;
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
				ui.addMovementHistoryRecord("Initial");

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
						System.out.print(currentSide);
						System.out.println(" move.");

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
						history.add(new Node(state, currentSide, selectedPoint.getId(), point.getId(),
								board.getMoveString(selectedPiece, selectedPoint, point),
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
					System.out.println(currentSide);

					// update move history and status bar
					ui.updateStatusBarStatus(board.getMoveString(selectedPiece, selectedPoint, point));
					ui.addMovementHistoryRecord(board.getMoveString(selectedPiece, selectedPoint, point));
					Map<Integer, Integer> state = new HashMap<>();
					for (Point everyPoint : board.getPoints()) {
						if (everyPoint.getPiece() != null) {
							state.put(everyPoint.getId(), everyPoint.getPiece().getId());
						}
					}
					history.add(new Node(state, currentSide, selectedPoint.getId(), point.getId(),
							board.getMoveString(selectedPiece, selectedPoint, point), history.get(history.size() - 1)));

					// reset point and piece
					selectedPiece = null;
					selectedPoint = null;
				} else {
					if (point.getPiece().getSide() == currentSide) {
						board.updateSelectedPieceMovable(point, currentSide);
						selectedPiece = point.getPiece();
						selectedPoint = point;
					}
				}
			}

			@Override
			public void onUndo(int undoStep) {
				Node nodeGoingBackTo = history.get(history.size() - undoStep - 1);

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
			handleFileEventCallBack().newGame();
			break;
		case MENUITEM_LOAD_GAME:
			ui.updateStatusBarStatus("Load game");
			handleFileEventCallBack().loadGame();
			break;
		case MENUITEM_SAVE_GAME:
			ui.updateStatusBarStatus("Save game");
			handleFileEventCallBack().saveGame();
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

	public void computerMakeMove(Point fromPoint, Point toPoint) {
		handleUIEventCallBack().onPieceOnPointSelected(fromPoint);
		if (toPoint.getPiece() == null) {
			handleUIEventCallBack().onPointSelected(toPoint);

		} else {
			handleUIEventCallBack().onPieceOnPointSelected(toPoint);
		}
	}

	private void addDataToInfoPanel() {
		String AIName = "Computer", playerName = "Player 1";

		ui.infoPanelUpdatePlayerSideData(DataAndSetting.PieceData.PlayerSide.player, AIName);
		ui.infoPanelUpdatePlayerSideData(DataAndSetting.PieceData.PlayerSide.computer, playerName);

		// ui.infoPanelUpdatePlayerRemainingTimeData(playerName, "5:00\"00");
		// ui.infoPanelUpdatePlayerRemainingTimeData(AIName, "5:00\"00");
		//
		// String[][] systemData = new String[][] { { "CPU Time", "2:50\"00" },
		// { "No. Threads", "4" },
		// { "Peek RAM", "1536 MB" }, { "Curr. RAM", "64 MB" }, { "Calc. Steps",
		// "1048576" },
		// { "1 Step Time", "0.162 ms" } };
		// for (String[] item : systemData)
		// ui.infoPanelUpdateSystemInfoData(item[0], item[1]);
	}
}
