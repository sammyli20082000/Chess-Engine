package Executable;
import java.io.File;
import Executable.BoardModel.*;
import Executable.PieceModel.*;
public class DataAndSetting {
public static String localDir = getLocalDir();
public static String getLocalDir() {
String s;
try {
s = new File(DataAndSetting.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "\\pic\\";
} catch (Exception e) {
s = new File(DataAndSetting.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\pic\\";
}
return s;
}
public static class BoardData {
public static String imageLink = localDir + "board.png";
public static int preferredPixelWidth = 550;
public static int preferredPixelHeight = 825;
}
public static class PointEdgePackage {
public int sourceID, targetID;
public Edge.Direction edgeDirection;
public PointEdgePackage(int sourceID, Edge.Direction edgeDirection, int targetID) {
this.sourceID = sourceID;
this.edgeDirection = edgeDirection;
this.targetID = targetID;
}
}
public static class PieceDataPackage {
public int pointID;
public String playerSide, pieceType;
public PieceDataPackage(int pointID, String pieceType, String playerSide) {
this.pointID = pointID;
this.playerSide = playerSide;
this.pieceType = pieceType;
}
}
public static class PieceData {
public static class PlayerSide {
public static String RED = "RED";
public static String BLACK = "BLACK";
}
public static Piece makeStandardPiece(String pieceType, String playerSide) {
if (pieceType.equalsIgnoreCase("General") && playerSide.equals(PlayerSide.BLACK))
return new BlackGeneral(playerSide, localDir + "BLACK_General.png", 0.1, 0.06666666666666667, "General");
else if (pieceType.equalsIgnoreCase("General") && playerSide.equals(PlayerSide.RED))
return new RedGeneral(playerSide, localDir + "RED_General.png", 0.1, 0.06666666666666667, "General");
else
return null;
}
public static PieceDataPackage[] initialPiecePlacingData = new PieceDataPackage[] {
new PieceDataPackage(8, "General", PlayerSide.RED),
new PieceDataPackage(0, "General", PlayerSide.BLACK),
};
}
public static class PointEdgeData {
public static class PointDataPackage {
public double xCoordinate, yCoordinate, width, height;
public int id;
public PointDataPackage(double xCoordinate, double yCoordinate, double width, double height, int id) {
this.xCoordinate = xCoordinate;
this.yCoordinate = yCoordinate;
this.width = width;
this.height = height;
this.id = id;
}
}
public static PointDataPackage[] boardPointsArray = new PointDataPackage[]{
new PointDataPackage(0.09917091836734691, 0.1989795918367347, 0.05, 0.03333333333333333, 0),
new PointDataPackage(0.09917091836734691, 0.47959183673469385, 0.05, 0.03333333333333333, 1),
new PointDataPackage(0.09917091836734691, 0.7602040816326531, 0.05, 0.03333333333333333, 2),
new PointDataPackage(0.5019132653061225, 0.1989795918367347, 0.05, 0.03333333333333333, 3),
new PointDataPackage(0.5019132653061225, 0.47959183673469385, 0.05, 0.03333333333333333, 4),
new PointDataPackage(0.5019132653061225, 0.7602040816326531, 0.05, 0.03333333333333333, 5),
new PointDataPackage(0.904655612244898, 0.1989795918367347, 0.05, 0.03333333333333333, 6),
new PointDataPackage(0.904655612244898, 0.47959183673469385, 0.05, 0.03333333333333333, 7),
new PointDataPackage(0.904655612244898, 0.7602040816326531, 0.05, 0.03333333333333333, 8),
};
public static PointEdgePackage[] pointEdgeRelations = new PointEdgePackage[]{
new PointEdgePackage(0, Edge.Direction.EAST, 3),
new PointEdgePackage(0, Edge.Direction.SOUTH, 1),
new PointEdgePackage(1, Edge.Direction.NORTH, 0),
new PointEdgePackage(1, Edge.Direction.EAST, 4),
new PointEdgePackage(1, Edge.Direction.SOUTH, 2),
new PointEdgePackage(2, Edge.Direction.NORTH, 1),
new PointEdgePackage(2, Edge.Direction.EAST, 5),
new PointEdgePackage(3, Edge.Direction.WEST, 0),
new PointEdgePackage(3, Edge.Direction.EAST, 6),
new PointEdgePackage(3, Edge.Direction.SOUTH, 4),
new PointEdgePackage(4, Edge.Direction.WEST, 1),
new PointEdgePackage(4, Edge.Direction.NORTH, 3),
new PointEdgePackage(4, Edge.Direction.EAST, 7),
new PointEdgePackage(4, Edge.Direction.SOUTH, 5),
new PointEdgePackage(5, Edge.Direction.WEST, 2),
new PointEdgePackage(5, Edge.Direction.NORTH, 4),
new PointEdgePackage(5, Edge.Direction.EAST, 8),
new PointEdgePackage(6, Edge.Direction.WEST, 3),
new PointEdgePackage(6, Edge.Direction.SOUTH, 7),
new PointEdgePackage(7, Edge.Direction.WEST, 4),
new PointEdgePackage(7, Edge.Direction.NORTH, 6),
new PointEdgePackage(7, Edge.Direction.SOUTH, 8),
new PointEdgePackage(8, Edge.Direction.WEST, 5),
new PointEdgePackage(8, Edge.Direction.NORTH, 7),
};
}
}
