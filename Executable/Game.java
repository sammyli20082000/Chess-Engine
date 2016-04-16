package Executable;
import java.io.*;
import java.util.*;
import javax.swing.*;
import Executable.BoardModel.Board;
import Executable.BoardModel.Point;
import Executable.FileHandlerModel.FileHandler;
import Executable.PieceModel.Piece;
import Executable.UIHandlerModel.UIHandler;
import Executable.ObjectModel.*;
import Executable.SocketConnection.*;
// GameType: ADD_MOVE_TYPE
public class Game implements Serializable {
Game game;
public Board board;
AI ai;
boolean canCapture = true;
public ArrayList<String> sides = new ArrayList<>();
String currentSide;
ArrayList<Piece> currPieces;
ArrayList<Node> history;
Piece selectedPiece;
Point selectedPoint;
UIHandler ui;
String gameLocation;
Server server;
Client client;
public static void main(String[] args) {
new Game();
}
public Game() {
game = this;
gameLocation = (new File(Game.class.getClassLoader().getResource("").getPath())).getAbsolutePath();
board = new Board();
sides.add(DataAndSetting.PieceData.PlayerSide.BLACK);
sides.add(DataAndSetting.PieceData.PlayerSide.RED);
ui = new UIHandler(handleUIEventCallBack());
addDataToInfoPanel();
ui.setBoard(board);
ui.updateStatusBarStatus("Select start side and start game");
ai = new AI(game);
}
private void addPointsPiecesEdgesToBoard() {
for (int i = 0; i < DataAndSetting.PointEdgeData.boardPointsArray.length; i++) {
DataAndSetting.PointEdgeData.PointDataPackage pack = DataAndSetting.PointEdgeData.boardPointsArray[i];
board.addPoint(pack.xCoordinate, pack.yCoordinate, pack.width, pack.height, pack.id);
}
for (int i = 0; i < DataAndSetting.PointEdgeData.pointEdgeRelations.length; i++) {
DataAndSetting.PointEdgePackage dataPackage = DataAndSetting.PointEdgeData.pointEdgeRelations[i];
board.getPointById(dataPackage.sourceID).addEdge(dataPackage.edgeDirection,
board.getPointById(dataPackage.targetID));
}
currPieces = new ArrayList<Piece>();
for (int i = 0; i < DataAndSetting.PieceData.initialPiecePlacingData.length; i++) {
DataAndSetting.PieceDataPackage dataPackage = DataAndSetting.PieceData.initialPiecePlacingData[i];
Piece newPiece = DataAndSetting.PieceData.makeStandardPiece(dataPackage.pieceType, dataPackage.playerSide);
board.getPointById(dataPackage.pointID).setPiece(newPiece);
currPieces.add(newPiece);
}
ui.refreshWindow();
}
private FileHandler.EventCallBackHandler handleFileEventCallBack() {
return new FileHandler.EventCallBackHandler() {
@Override
public void newGame() {
board = new Board();
currentSide = "";
currPieces = null;
Piece.resetIdCounter();
ai = new AI(game);
ui.setBoard(board);
ui.enableStartGameButton();
history = null;
ui.restoreMovementHistoryList();
selectedPiece = null;
selectedPoint = null;
server = null;
client = null;
ui.refreshWindow();
}
@Override
public void saveGame() {
FileOutputStream fout = null;
ObjectOutputStream oos = null;
JFileChooser chooser = new JFileChooser();
chooser.setSelectedFile(new File("save.bghis"));
try {
if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
fout = new FileOutputStream(chooser.getSelectedFile());
oos = new ObjectOutputStream(fout);
oos.writeObject(history);
} else {
JOptionPane.showMessageDialog(null, "Game cannot be saved.", "Saving Error",JOptionPane.YES_OPTION);
}
} catch (Exception e) {
// e.printStackTrace();
JOptionPane.showMessageDialog(null, "Game cannot be saved.", "Saving Error", JOptionPane.YES_OPTION);
} finally {
try {
fout.close();
oos.close();
} catch (IOException e) {}
}
}
@Override
public void loadGame() {
FileInputStream fin = null;
ObjectInputStream ois = null;
JFileChooser chooser = new JFileChooser();
chooser.setSelectedFile(new File("save.bghis"));
try {
if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
fin = new FileInputStream(chooser.getSelectedFile());
ois = new ObjectInputStream(fin);
ArrayList<Node> oldHistory = (ArrayList<Node>) ois.readObject();
newGame();
addPointsPiecesEdgesToBoard();
ui.setStatusBarButtonsEnabled(true);
currentSide = oldHistory.get(0).getState().getSide();
history = new ArrayList<>();
Map<Integer, Integer> state = new HashMap<>();
for (Point everyPoint : board.getPoints()) {
if (everyPoint.getPiece() != null) {
state.put(everyPoint.getId(), everyPoint.getPiece().getId());
}
}
history.add(new Node(state, currentSide));
ui.addMovementHistoryRecord("Initial");
for (int i = 1; i < oldHistory.size(); i++) {
computerMakeMove(oldHistory.get(i).causingMove);
}
ui.refreshWindow();
} else {
JOptionPane.showMessageDialog(null, "Game cannot be loaded.", "Loading Error",JOptionPane.YES_OPTION);
}
} catch (Exception e) {
// e.printStackTrace();
JOptionPane.showMessageDialog(null, "Game cannot be loaded.", "Loading Error", JOptionPane.YES_OPTION);
} finally {
try {
fin.close();
ois.close();
} catch (Exception e) {}
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
selectedPiece = null;
selectedPoint = null;
ui.refreshWindow();
}
@Override
public void onConfirmMovement() {
if (selectedPoint != null && selectedPoint.getPiece() == null) {
// add piece
Piece newPiece = DataAndSetting.PieceData.makeStandardPiece(ui.getSelectedPiece(), currentSide);
selectedPiece = newPiece;
currPieces.add(newPiece);
selectedPoint.setPiece(selectedPiece);
// change side
currentSide = sides.get((sides.indexOf(currentSide) + 1) % sides.size());
// update move history and status bar
Move move = new Move(selectedPiece.getName(), selectedPoint.getId());
updateUIMoveHistoryAndStatusBar(move);
if (server != null) server.sendMove(move);
else if (client!= null) client.sendMove(move);
// reset point and piece
selectedPiece = null;
selectedPoint = null;
ui.refreshWindow();
}
}
@Override
public void onStartGame(String playerSide) {
// define starter side
ui.updateStatusBarStatus("Start game: " + playerSide);
switch (playerSide) {
case "BLACK":
currentSide = DataAndSetting.PieceData.PlayerSide.BLACK;
break;
case "RED":
currentSide = DataAndSetting.PieceData.PlayerSide.RED;
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
public void onUndo(int undoStep) {
undo(undoStep);
ui.refreshWindow();
}
public void onPointSelected(Point point) {
if (selectedPiece != null && point != null) {
if (board.getSelectedPieceMovable().contains(point)) {
// move
board.movePiece(selectedPiece, selectedPoint, point);
// change side
currentSide = sides.get((sides.indexOf(currentSide) + 1) % sides.size());
// update move history and status bar
Move move = Move.transformingMove(point.getPiece().getName(), selectedPoint.getId(), point.getId());
updateUIMoveHistoryAndStatusBar(move);
if (server != null) server.sendMove(move);
else if (client!= null) client.sendMove(move);
// reset point and piece
selectedPiece = null;
selectedPoint = null;
} else {
selectedPoint = point;
}
} else {
selectedPoint = point;
selectedPiece = null;
}
}
public void onPieceOnPointSelected(Point point) {
if (selectedPiece != null && board.getSelectedPieceMovable().contains(point)) {
// capture
board.capture(selectedPiece, selectedPoint, point);
// change side
currentSide = sides.get((sides.indexOf(currentSide) + 1) % sides.size());
// update move history and status bar
Move move = Move.transformingMove(point.getPiece().getName(), selectedPoint.getId(), point.getId());
updateUIMoveHistoryAndStatusBar(move);
if (server != null) server.sendMove(move);
else if (client!= null) client.sendMove(move);
// reset point and piece
selectedPiece = null;
selectedPoint = null;
} else {
if (point.getPiece().getSide().equals(currentSide)) {
board.updateSelectedPieceMovable(point, currentSide);
selectedPiece = point.getPiece();
selectedPoint = point;
}
}
}
};
}
private void updateUIMoveHistoryAndStatusBar(Move move) {
if (!AI.virtualMoving) {
ui.updateStatusBarStatus(board.getMoveString(move));
ui.addMovementHistoryRecord(board.getMoveString(move));
}
Map<Integer, Integer> state = new HashMap<>();
for (Point everyPoint : board.getPoints()) {
if (everyPoint.getPiece() != null)
state.put(everyPoint.getId(), everyPoint.getPiece().getId());
}
history.add(new Node(state, currentSide, selectedPoint.getId(), move.toPoint,
move, history.get(history.size() - 1)));
}
public void undo(int undoStep) {
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
case MENUITEM_STEP_REDO:
ui.updateStatusBarStatus("Step redo");
break;
//case MENUITEM_GAME_DISTRIBUTE_COMPUTING:
//ui.updateStatusBarStatus("Distributed computing");
//break;
case MENUITEM_SERVER:
server = new Server(this);
break;
case MENUITEM_CLIENT:
client = new Client(this);
break;
case MENUITEM_VIEW_SHOW_DEBUG:
ui.updateStatusBarStatus("Show Debug: " + ui.getIsShowDebug());
break;
case MENUITEM_VIEW_SHOW_PIECE_PLACING_POINT:
ui.updateStatusBarStatus("Show Piece Placing Point: " + ui.getIsShowPiecePlacingPoint());
break;
//case MENUITEM_VIEW_DETAIL_SYSTEM_INFO:
//ui.updateStatusBarStatus("Detailed system information");
//break;
//case MENUITEM_VIEW_AI_THINKING_STEP:
//ui.updateStatusBarStatus("AI thinking step");
//break;
//case MENUITEM_VIEW_GAME_TREE:
//ui.updateStatusBarStatus("Game tree");
//break;
//case MENUITEM_HELP_ABOUT:
//ui.updateStatusBarStatus("About");
//break;
//case MENUITEM_HELP_TUTORIAL:
//ui.updateStatusBarStatus("Tutorial");
//break;
case MENUITEM_VIEW_FIT_WINDOW:
ui.fitWindow();
break;
}
}
private void programTerminate() {
System.exit(0);
}
public void computerMakeMove(Move move) {
if (history.size() > 1) {
Move causingMove = history.get(history.size() - 1).causingMove;
if (move.equals(causingMove)) return;
}
if (move.fromPoint != -1) {
handleUIEventCallBack().onPieceOnPointSelected(board.getPointById(move.fromPoint));
if (board.getPointById(move.toPoint).getPiece() == null) {
handleUIEventCallBack().onPointSelected(board.getPointById(move.toPoint));
} else {
handleUIEventCallBack().onPieceOnPointSelected(board.getPointById(move.toPoint));
}
} else {
Piece newPiece = DataAndSetting.PieceData.makeStandardPiece(move.pieceName, currentSide);
selectedPiece = newPiece;
currPieces.add(newPiece);
selectedPoint = board.getPointById(move.toPoint);
selectedPoint.setPiece(selectedPiece);
// change side
currentSide = sides.get((sides.indexOf(currentSide) + 1) % sides.size());
// update move history and status bar
updateUIMoveHistoryAndStatusBar(move);
// reset point and piece
selectedPiece = null;
selectedPoint = null;
}
ui.refreshWindow();
}
private void addDataToInfoPanel() {
String BLACK = "BLACK";
ui.infoPanelUpdatePlayerSideData("Player 0", BLACK);
String RED = "RED";
ui.infoPanelUpdatePlayerSideData("Player 1", RED);
JRadioButton generalButton = new JRadioButton("general");
generalButton.setActionCommand("general");
ui.infoPanelUpdateSystemInfoData(generalButton);
}
}
