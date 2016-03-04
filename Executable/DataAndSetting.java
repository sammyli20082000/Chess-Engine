package Executable;

import java.io.File;

import Executable.BoardModel.*;
import Executable.PieceModel.*;

/**
 * Created by root on 11/19/15.
 */
public class DataAndSetting {
    public static String localDir = getLocalDir();

    public static String getLocalDir() {
        String s;
        try {
            s = new File(DataAndSetting.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "/pic/";
        } catch (Exception e) {
            s = new File(DataAndSetting.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "/pic/";
        }
        return s;
    }

    public static class BoardData {
        public static String imageLink = localDir + "board.png";
        public static int preferredPixelWidth = 810;
        public static int preferredPixelHeight = 906;
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
            public static String player = "red";
            public static String computer = "black";
        }

        public static Piece makeStandardPiece(String pieceType, String playerSide) {
            if (pieceType.equals("Soldier") && playerSide.equals(PlayerSide.player))
                return new Soldier(playerSide, localDir + playerSide + "_soldier.png", 0.08825665859564165, 0.07890495967160015, "\u5175");
            else if (pieceType.equals("Soldier") && playerSide.equals(PlayerSide.computer))
                return new Soldier(playerSide, localDir + playerSide + "_soldier.png", 0.08825665859564165, 0.07890495967160015, "\u5352");
            else if (pieceType.equals("Advisor") && playerSide.equals(PlayerSide.player))
                return new Advisor(playerSide, localDir + playerSide + "_advisor.png", 0.08825665859564165, 0.07890495967160015, "\u4ed5");
            else if (pieceType.equals("Advisor") && playerSide.equals(PlayerSide.computer))
                return new Advisor(playerSide, localDir + playerSide + "_advisor.png", 0.08825665859564165, 0.07890495967160015, "\u58eb");
            else if (pieceType.equals("Cannon") && playerSide.equals(PlayerSide.player))
                return new Cannon(playerSide, localDir + playerSide + "_cannon.png", 0.08825665859564165, 0.07890495967160015, "\u70ae");
            else if (pieceType.equals("Cannon") && playerSide.equals(PlayerSide.computer))
                return new Cannon(playerSide, localDir + playerSide + "_cannon.png", 0.08825665859564165, 0.07890495967160015, "\u7832");
            else if (pieceType.equals("Chariot") && playerSide.equals(PlayerSide.player))
                return new Chariot(playerSide, localDir + playerSide + "_chariot.png", 0.08825665859564165, 0.07890495967160015, "\u4fe5");
            else if (pieceType.equals("Chariot") && playerSide.equals(PlayerSide.computer))
                return new Chariot(playerSide, localDir + playerSide + "_chariot.png", 0.08825665859564165, 0.07890495967160015, "\u8eca");
            else if (pieceType.equals("Elephant") && playerSide.equals(PlayerSide.player))
                return new Elephant(playerSide, localDir + playerSide + "_elephant.png", 0.08825665859564165, 0.07890495967160015, "\u76f8");
            else if (pieceType.equals("Elephant") && playerSide.equals(PlayerSide.computer))
                return new Elephant(playerSide, localDir + playerSide + "_elephant.png", 0.08825665859564165, 0.07890495967160015, "\u8c61");
            else if (pieceType.equals("General") && playerSide.equals(PlayerSide.player))
                return new General(playerSide, localDir + playerSide + "_general.png", 0.08825665859564165, 0.07890495967160015, "\u5e25");
            else if (pieceType.equals("General") && playerSide.equals(PlayerSide.computer))
                return new General(playerSide, localDir + playerSide + "_general.png", 0.08825665859564165, 0.07890495967160015, "\u5c07");
            else if (pieceType.equals("Horse") && playerSide.equals(PlayerSide.player))
                return new Horse(playerSide, localDir + playerSide + "_horse.png", 0.08825665859564165, 0.07890495967160015, "\u508c");
            else if (pieceType.equals("Horse") && playerSide.equals(PlayerSide.computer))
                return new Horse(playerSide, localDir + playerSide + "_horse.png", 0.08825665859564165, 0.07890495967160015, "\u99ac");
            else
                return null;
        }

        public static PieceDataPackage[] initialPiecePlacingData = new PieceDataPackage[]{
                new PieceDataPackage(0, "Chariot", PlayerSide.computer),
                new PieceDataPackage(3, "Soldier", PlayerSide.computer),
                new PieceDataPackage(6, "Soldier", PlayerSide.player),
                new PieceDataPackage(9, "Chariot", PlayerSide.player),
                new PieceDataPackage(10, "Horse", PlayerSide.computer),
                new PieceDataPackage(12, "Cannon", PlayerSide.computer),
                new PieceDataPackage(17, "Cannon", PlayerSide.player),
                new PieceDataPackage(19, "Horse", PlayerSide.player),
                new PieceDataPackage(20, "Elephant", PlayerSide.computer),
                new PieceDataPackage(23, "Soldier", PlayerSide.computer),
                new PieceDataPackage(26, "Soldier", PlayerSide.player),
                new PieceDataPackage(29, "Elephant", PlayerSide.player),
                new PieceDataPackage(30, "Advisor", PlayerSide.computer),
                new PieceDataPackage(39, "Advisor", PlayerSide.player),
                new PieceDataPackage(40, "General", PlayerSide.computer),
                new PieceDataPackage(43, "Soldier", PlayerSide.computer),
                new PieceDataPackage(46, "Soldier", PlayerSide.player),
                new PieceDataPackage(49, "General", PlayerSide.player),
                new PieceDataPackage(50, "Advisor", PlayerSide.computer),
                new PieceDataPackage(59, "Advisor", PlayerSide.player),
                new PieceDataPackage(60, "Elephant", PlayerSide.computer),
                new PieceDataPackage(63, "Soldier", PlayerSide.computer),
                new PieceDataPackage(66, "Soldier", PlayerSide.player),
                new PieceDataPackage(69, "Elephant", PlayerSide.player),
                new PieceDataPackage(70, "Horse", PlayerSide.computer),
                new PieceDataPackage(72, "Cannon", PlayerSide.computer),
                new PieceDataPackage(77, "Cannon", PlayerSide.player),
                new PieceDataPackage(79, "Horse", PlayerSide.player),
                new PieceDataPackage(80, "Chariot", PlayerSide.computer),
                new PieceDataPackage(83, "Soldier", PlayerSide.computer),
                new PieceDataPackage(86, "Soldier", PlayerSide.player),
                new PieceDataPackage(89, "Chariot", PlayerSide.player)
        };
    }

    public static class PointEdgeData {
        public static class PointDataPackage {
            public double xCoordinate, yCoordinate, width, height;

            public PointDataPackage(double xCoordinate, double yCoordinate, double width, double height) {
                this.xCoordinate = xCoordinate;
                this.yCoordinate = yCoordinate;
                this.width = width;
                this.height = height;
            }
        }

        public static PointDataPackage[] boardPointsArray = new PointDataPackage[]{
                new PointDataPackage(0.05569007263922518, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.05569007263922518, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.16601089588377724, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.2763317191283293, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.3866525423728814, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.49697336561743344, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.6072941888619855, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.7176150121065376, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.8279358353510896, 0.96, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.04216216216216216, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.14414414414414414, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.2461261261261261, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.3481081081081081, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.4500900900900901, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.5520720720720721, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.654054054054054, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.7560360360360361, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.858018018018018, 0.044128329297820824, 0.039452479835800074),
                new PointDataPackage(0.9382566585956417, 0.96, 0.044128329297820824, 0.039452479835800074)
        };

        public static PointEdgePackage[] pointEdgeRelations = new PointEdgePackage[]{
                new PointEdgePackage(0, Edge.Direction.EAST, 10),
                new PointEdgePackage(0, Edge.Direction.SOUTH, 1),
                new PointEdgePackage(1, Edge.Direction.EAST, 11),
                new PointEdgePackage(1, Edge.Direction.SOUTH, 2),
                new PointEdgePackage(1, Edge.Direction.NORTH, 0),
                new PointEdgePackage(2, Edge.Direction.EAST, 12),
                new PointEdgePackage(2, Edge.Direction.SOUTH, 3),
                new PointEdgePackage(2, Edge.Direction.NORTH, 1),
                new PointEdgePackage(3, Edge.Direction.EAST, 13),
                new PointEdgePackage(3, Edge.Direction.SOUTH, 4),
                new PointEdgePackage(3, Edge.Direction.NORTH, 2),
                new PointEdgePackage(4, Edge.Direction.EAST, 14),
                new PointEdgePackage(4, Edge.Direction.SOUTH, 5),
                new PointEdgePackage(4, Edge.Direction.NORTH, 3),
                new PointEdgePackage(5, Edge.Direction.EAST, 15),
                new PointEdgePackage(5, Edge.Direction.SOUTH, 6),
                new PointEdgePackage(5, Edge.Direction.NORTH, 4),
                new PointEdgePackage(6, Edge.Direction.EAST, 16),
                new PointEdgePackage(6, Edge.Direction.SOUTH, 7),
                new PointEdgePackage(6, Edge.Direction.NORTH, 5),
                new PointEdgePackage(7, Edge.Direction.EAST, 17),
                new PointEdgePackage(7, Edge.Direction.SOUTH, 8),
                new PointEdgePackage(7, Edge.Direction.NORTH, 6),
                new PointEdgePackage(8, Edge.Direction.EAST, 18),
                new PointEdgePackage(8, Edge.Direction.SOUTH, 9),
                new PointEdgePackage(8, Edge.Direction.NORTH, 7),
                new PointEdgePackage(9, Edge.Direction.EAST, 19),
                new PointEdgePackage(9, Edge.Direction.NORTH, 8),
                new PointEdgePackage(10, Edge.Direction.EAST, 20),
                new PointEdgePackage(10, Edge.Direction.WEST, 0),
                new PointEdgePackage(10, Edge.Direction.SOUTH, 11),
                new PointEdgePackage(11, Edge.Direction.EAST, 21),
                new PointEdgePackage(11, Edge.Direction.WEST, 1),
                new PointEdgePackage(11, Edge.Direction.SOUTH, 12),
                new PointEdgePackage(11, Edge.Direction.NORTH, 10),
                new PointEdgePackage(12, Edge.Direction.EAST, 22),
                new PointEdgePackage(12, Edge.Direction.WEST, 2),
                new PointEdgePackage(12, Edge.Direction.SOUTH, 13),
                new PointEdgePackage(12, Edge.Direction.NORTH, 11),
                new PointEdgePackage(13, Edge.Direction.EAST, 23),
                new PointEdgePackage(13, Edge.Direction.WEST, 3),
                new PointEdgePackage(13, Edge.Direction.SOUTH, 14),
                new PointEdgePackage(13, Edge.Direction.NORTH, 12),
                new PointEdgePackage(14, Edge.Direction.EAST, 24),
                new PointEdgePackage(14, Edge.Direction.WEST, 4),
                new PointEdgePackage(14, Edge.Direction.SOUTH, 15),
                new PointEdgePackage(14, Edge.Direction.NORTH, 13),
                new PointEdgePackage(15, Edge.Direction.EAST, 25),
                new PointEdgePackage(15, Edge.Direction.WEST, 5),
                new PointEdgePackage(15, Edge.Direction.SOUTH, 16),
                new PointEdgePackage(15, Edge.Direction.NORTH, 14),
                new PointEdgePackage(16, Edge.Direction.EAST, 26),
                new PointEdgePackage(16, Edge.Direction.WEST, 6),
                new PointEdgePackage(16, Edge.Direction.SOUTH, 17),
                new PointEdgePackage(16, Edge.Direction.NORTH, 15),
                new PointEdgePackage(17, Edge.Direction.EAST, 27),
                new PointEdgePackage(17, Edge.Direction.WEST, 7),
                new PointEdgePackage(17, Edge.Direction.SOUTH, 18),
                new PointEdgePackage(17, Edge.Direction.NORTH, 16),
                new PointEdgePackage(18, Edge.Direction.EAST, 28),
                new PointEdgePackage(18, Edge.Direction.WEST, 8),
                new PointEdgePackage(18, Edge.Direction.SOUTH, 19),
                new PointEdgePackage(18, Edge.Direction.NORTH, 17),
                new PointEdgePackage(19, Edge.Direction.EAST, 29),
                new PointEdgePackage(19, Edge.Direction.WEST, 9),
                new PointEdgePackage(19, Edge.Direction.NORTH, 18),
                new PointEdgePackage(20, Edge.Direction.EAST, 30),
                new PointEdgePackage(20, Edge.Direction.WEST, 10),
                new PointEdgePackage(20, Edge.Direction.SOUTH, 21),
                new PointEdgePackage(21, Edge.Direction.EAST, 31),
                new PointEdgePackage(21, Edge.Direction.WEST, 11),
                new PointEdgePackage(21, Edge.Direction.SOUTH, 22),
                new PointEdgePackage(21, Edge.Direction.NORTH, 20),
                new PointEdgePackage(22, Edge.Direction.EAST, 32),
                new PointEdgePackage(22, Edge.Direction.WEST, 12),
                new PointEdgePackage(22, Edge.Direction.SOUTH, 23),
                new PointEdgePackage(22, Edge.Direction.NORTH, 21),
                new PointEdgePackage(23, Edge.Direction.EAST, 33),
                new PointEdgePackage(23, Edge.Direction.WEST, 13),
                new PointEdgePackage(23, Edge.Direction.SOUTH, 24),
                new PointEdgePackage(23, Edge.Direction.NORTH, 22),
                new PointEdgePackage(24, Edge.Direction.EAST, 34),
                new PointEdgePackage(24, Edge.Direction.WEST, 14),
                new PointEdgePackage(24, Edge.Direction.SOUTH, 25),
                new PointEdgePackage(24, Edge.Direction.NORTH, 23),
                new PointEdgePackage(25, Edge.Direction.EAST, 35),
                new PointEdgePackage(25, Edge.Direction.WEST, 15),
                new PointEdgePackage(25, Edge.Direction.SOUTH, 26),
                new PointEdgePackage(25, Edge.Direction.NORTH, 24),
                new PointEdgePackage(26, Edge.Direction.EAST, 36),
                new PointEdgePackage(26, Edge.Direction.WEST, 16),
                new PointEdgePackage(26, Edge.Direction.SOUTH, 27),
                new PointEdgePackage(26, Edge.Direction.NORTH, 25),
                new PointEdgePackage(27, Edge.Direction.EAST, 37),
                new PointEdgePackage(27, Edge.Direction.WEST, 17),
                new PointEdgePackage(27, Edge.Direction.SOUTH, 28),
                new PointEdgePackage(27, Edge.Direction.NORTH, 26),
                new PointEdgePackage(28, Edge.Direction.EAST, 38),
                new PointEdgePackage(28, Edge.Direction.WEST, 18),
                new PointEdgePackage(28, Edge.Direction.SOUTH, 29),
                new PointEdgePackage(28, Edge.Direction.NORTH, 27),
                new PointEdgePackage(29, Edge.Direction.EAST, 39),
                new PointEdgePackage(29, Edge.Direction.WEST, 19),
                new PointEdgePackage(29, Edge.Direction.NORTH, 28),
                new PointEdgePackage(30, Edge.Direction.EAST, 40),
                new PointEdgePackage(30, Edge.Direction.WEST, 20),
                new PointEdgePackage(30, Edge.Direction.SOUTH, 31),
                new PointEdgePackage(30, Edge.Direction.SOUTH_EAST, 41),
                new PointEdgePackage(31, Edge.Direction.EAST, 41),
                new PointEdgePackage(31, Edge.Direction.WEST, 21),
                new PointEdgePackage(31, Edge.Direction.SOUTH, 32),
                new PointEdgePackage(31, Edge.Direction.NORTH, 30),
                new PointEdgePackage(32, Edge.Direction.EAST, 42),
                new PointEdgePackage(32, Edge.Direction.WEST, 22),
                new PointEdgePackage(32, Edge.Direction.SOUTH, 33),
                new PointEdgePackage(32, Edge.Direction.NORTH, 31),
                new PointEdgePackage(32, Edge.Direction.NORTH_EAST, 41),
                new PointEdgePackage(33, Edge.Direction.EAST, 43),
                new PointEdgePackage(33, Edge.Direction.WEST, 23),
                new PointEdgePackage(33, Edge.Direction.SOUTH, 34),
                new PointEdgePackage(33, Edge.Direction.NORTH, 32),
                new PointEdgePackage(34, Edge.Direction.EAST, 44),
                new PointEdgePackage(34, Edge.Direction.WEST, 24),
                new PointEdgePackage(34, Edge.Direction.SOUTH, 35),
                new PointEdgePackage(34, Edge.Direction.NORTH, 33),
                new PointEdgePackage(35, Edge.Direction.EAST, 45),
                new PointEdgePackage(35, Edge.Direction.WEST, 25),
                new PointEdgePackage(35, Edge.Direction.SOUTH, 36),
                new PointEdgePackage(35, Edge.Direction.NORTH, 34),
                new PointEdgePackage(36, Edge.Direction.EAST, 46),
                new PointEdgePackage(36, Edge.Direction.WEST, 26),
                new PointEdgePackage(36, Edge.Direction.SOUTH, 37),
                new PointEdgePackage(36, Edge.Direction.NORTH, 35),
                new PointEdgePackage(37, Edge.Direction.EAST, 47),
                new PointEdgePackage(37, Edge.Direction.WEST, 27),
                new PointEdgePackage(37, Edge.Direction.SOUTH, 38),
                new PointEdgePackage(37, Edge.Direction.NORTH, 36),
                new PointEdgePackage(37, Edge.Direction.SOUTH_EAST, 48),
                new PointEdgePackage(38, Edge.Direction.EAST, 48),
                new PointEdgePackage(38, Edge.Direction.WEST, 28),
                new PointEdgePackage(38, Edge.Direction.SOUTH, 39),
                new PointEdgePackage(38, Edge.Direction.NORTH, 37),
                new PointEdgePackage(39, Edge.Direction.EAST, 49),
                new PointEdgePackage(39, Edge.Direction.WEST, 29),
                new PointEdgePackage(39, Edge.Direction.NORTH, 38),
                new PointEdgePackage(39, Edge.Direction.NORTH_EAST, 48),
                new PointEdgePackage(40, Edge.Direction.EAST, 50),
                new PointEdgePackage(40, Edge.Direction.WEST, 30),
                new PointEdgePackage(40, Edge.Direction.SOUTH, 41),
                new PointEdgePackage(41, Edge.Direction.EAST, 51),
                new PointEdgePackage(41, Edge.Direction.WEST, 31),
                new PointEdgePackage(41, Edge.Direction.SOUTH, 42),
                new PointEdgePackage(41, Edge.Direction.NORTH, 40),
                new PointEdgePackage(41, Edge.Direction.NORTH_EAST, 50),
                new PointEdgePackage(41, Edge.Direction.NORTH_WEST, 30),
                new PointEdgePackage(41, Edge.Direction.SOUTH_EAST, 52),
                new PointEdgePackage(41, Edge.Direction.SOUTH_WEST, 32),
                new PointEdgePackage(42, Edge.Direction.EAST, 52),
                new PointEdgePackage(42, Edge.Direction.WEST, 32),
                new PointEdgePackage(42, Edge.Direction.SOUTH, 43),
                new PointEdgePackage(42, Edge.Direction.NORTH, 41),
                new PointEdgePackage(43, Edge.Direction.EAST, 53),
                new PointEdgePackage(43, Edge.Direction.WEST, 33),
                new PointEdgePackage(43, Edge.Direction.SOUTH, 44),
                new PointEdgePackage(43, Edge.Direction.NORTH, 42),
                new PointEdgePackage(44, Edge.Direction.EAST, 54),
                new PointEdgePackage(44, Edge.Direction.WEST, 34),
                new PointEdgePackage(44, Edge.Direction.SOUTH, 45),
                new PointEdgePackage(44, Edge.Direction.NORTH, 43),
                new PointEdgePackage(45, Edge.Direction.EAST, 55),
                new PointEdgePackage(45, Edge.Direction.WEST, 35),
                new PointEdgePackage(45, Edge.Direction.SOUTH, 46),
                new PointEdgePackage(45, Edge.Direction.NORTH, 44),
                new PointEdgePackage(46, Edge.Direction.EAST, 56),
                new PointEdgePackage(46, Edge.Direction.WEST, 36),
                new PointEdgePackage(46, Edge.Direction.SOUTH, 47),
                new PointEdgePackage(46, Edge.Direction.NORTH, 45),
                new PointEdgePackage(47, Edge.Direction.EAST, 57),
                new PointEdgePackage(47, Edge.Direction.WEST, 37),
                new PointEdgePackage(47, Edge.Direction.SOUTH, 48),
                new PointEdgePackage(47, Edge.Direction.NORTH, 46),
                new PointEdgePackage(48, Edge.Direction.EAST, 58),
                new PointEdgePackage(48, Edge.Direction.WEST, 38),
                new PointEdgePackage(48, Edge.Direction.SOUTH, 49),
                new PointEdgePackage(48, Edge.Direction.NORTH, 47),
                new PointEdgePackage(48, Edge.Direction.NORTH_EAST, 57),
                new PointEdgePackage(48, Edge.Direction.NORTH_WEST, 37),
                new PointEdgePackage(48, Edge.Direction.SOUTH_EAST, 59),
                new PointEdgePackage(48, Edge.Direction.SOUTH_WEST, 39),
                new PointEdgePackage(49, Edge.Direction.EAST, 59),
                new PointEdgePackage(49, Edge.Direction.WEST, 39),
                new PointEdgePackage(49, Edge.Direction.NORTH, 48),
                new PointEdgePackage(50, Edge.Direction.EAST, 60),
                new PointEdgePackage(50, Edge.Direction.WEST, 40),
                new PointEdgePackage(50, Edge.Direction.SOUTH, 51),
                new PointEdgePackage(50, Edge.Direction.SOUTH_WEST, 41),
                new PointEdgePackage(51, Edge.Direction.EAST, 61),
                new PointEdgePackage(51, Edge.Direction.WEST, 41),
                new PointEdgePackage(51, Edge.Direction.SOUTH, 52),
                new PointEdgePackage(51, Edge.Direction.NORTH, 50),
                new PointEdgePackage(52, Edge.Direction.EAST, 62),
                new PointEdgePackage(52, Edge.Direction.WEST, 42),
                new PointEdgePackage(52, Edge.Direction.SOUTH, 53),
                new PointEdgePackage(52, Edge.Direction.NORTH, 51),
                new PointEdgePackage(52, Edge.Direction.NORTH_WEST, 41),
                new PointEdgePackage(53, Edge.Direction.EAST, 63),
                new PointEdgePackage(53, Edge.Direction.WEST, 43),
                new PointEdgePackage(53, Edge.Direction.SOUTH, 54),
                new PointEdgePackage(53, Edge.Direction.NORTH, 52),
                new PointEdgePackage(54, Edge.Direction.EAST, 64),
                new PointEdgePackage(54, Edge.Direction.WEST, 44),
                new PointEdgePackage(54, Edge.Direction.SOUTH, 55),
                new PointEdgePackage(54, Edge.Direction.NORTH, 53),
                new PointEdgePackage(55, Edge.Direction.EAST, 65),
                new PointEdgePackage(55, Edge.Direction.WEST, 45),
                new PointEdgePackage(55, Edge.Direction.SOUTH, 56),
                new PointEdgePackage(55, Edge.Direction.NORTH, 54),
                new PointEdgePackage(56, Edge.Direction.EAST, 66),
                new PointEdgePackage(56, Edge.Direction.WEST, 46),
                new PointEdgePackage(56, Edge.Direction.SOUTH, 57),
                new PointEdgePackage(56, Edge.Direction.NORTH, 55),
                new PointEdgePackage(57, Edge.Direction.EAST, 67),
                new PointEdgePackage(57, Edge.Direction.WEST, 47),
                new PointEdgePackage(57, Edge.Direction.SOUTH, 58),
                new PointEdgePackage(57, Edge.Direction.NORTH, 56),
                new PointEdgePackage(57, Edge.Direction.SOUTH_WEST, 48),
                new PointEdgePackage(58, Edge.Direction.EAST, 68),
                new PointEdgePackage(58, Edge.Direction.WEST, 48),
                new PointEdgePackage(58, Edge.Direction.SOUTH, 59),
                new PointEdgePackage(58, Edge.Direction.NORTH, 57),
                new PointEdgePackage(59, Edge.Direction.EAST, 69),
                new PointEdgePackage(59, Edge.Direction.WEST, 49),
                new PointEdgePackage(59, Edge.Direction.NORTH, 58),
                new PointEdgePackage(59, Edge.Direction.NORTH_WEST, 48),
                new PointEdgePackage(60, Edge.Direction.EAST, 70),
                new PointEdgePackage(60, Edge.Direction.WEST, 50),
                new PointEdgePackage(60, Edge.Direction.SOUTH, 61),
                new PointEdgePackage(61, Edge.Direction.EAST, 71),
                new PointEdgePackage(61, Edge.Direction.WEST, 51),
                new PointEdgePackage(61, Edge.Direction.SOUTH, 62),
                new PointEdgePackage(61, Edge.Direction.NORTH, 60),
                new PointEdgePackage(62, Edge.Direction.EAST, 72),
                new PointEdgePackage(62, Edge.Direction.WEST, 52),
                new PointEdgePackage(62, Edge.Direction.SOUTH, 63),
                new PointEdgePackage(62, Edge.Direction.NORTH, 61),
                new PointEdgePackage(63, Edge.Direction.EAST, 73),
                new PointEdgePackage(63, Edge.Direction.WEST, 53),
                new PointEdgePackage(63, Edge.Direction.SOUTH, 64),
                new PointEdgePackage(63, Edge.Direction.NORTH, 62),
                new PointEdgePackage(64, Edge.Direction.EAST, 74),
                new PointEdgePackage(64, Edge.Direction.WEST, 54),
                new PointEdgePackage(64, Edge.Direction.SOUTH, 65),
                new PointEdgePackage(64, Edge.Direction.NORTH, 63),
                new PointEdgePackage(65, Edge.Direction.EAST, 75),
                new PointEdgePackage(65, Edge.Direction.WEST, 55),
                new PointEdgePackage(65, Edge.Direction.SOUTH, 66),
                new PointEdgePackage(65, Edge.Direction.NORTH, 64),
                new PointEdgePackage(66, Edge.Direction.EAST, 76),
                new PointEdgePackage(66, Edge.Direction.WEST, 56),
                new PointEdgePackage(66, Edge.Direction.SOUTH, 67),
                new PointEdgePackage(66, Edge.Direction.NORTH, 65),
                new PointEdgePackage(67, Edge.Direction.EAST, 77),
                new PointEdgePackage(67, Edge.Direction.WEST, 57),
                new PointEdgePackage(67, Edge.Direction.SOUTH, 68),
                new PointEdgePackage(67, Edge.Direction.NORTH, 66),
                new PointEdgePackage(68, Edge.Direction.EAST, 78),
                new PointEdgePackage(68, Edge.Direction.WEST, 58),
                new PointEdgePackage(68, Edge.Direction.SOUTH, 69),
                new PointEdgePackage(68, Edge.Direction.NORTH, 67),
                new PointEdgePackage(69, Edge.Direction.EAST, 79),
                new PointEdgePackage(69, Edge.Direction.WEST, 59),
                new PointEdgePackage(69, Edge.Direction.NORTH, 68),
                new PointEdgePackage(70, Edge.Direction.EAST, 80),
                new PointEdgePackage(70, Edge.Direction.WEST, 60),
                new PointEdgePackage(70, Edge.Direction.SOUTH, 71),
                new PointEdgePackage(71, Edge.Direction.EAST, 81),
                new PointEdgePackage(71, Edge.Direction.WEST, 61),
                new PointEdgePackage(71, Edge.Direction.SOUTH, 72),
                new PointEdgePackage(71, Edge.Direction.NORTH, 70),
                new PointEdgePackage(72, Edge.Direction.EAST, 82),
                new PointEdgePackage(72, Edge.Direction.WEST, 62),
                new PointEdgePackage(72, Edge.Direction.SOUTH, 73),
                new PointEdgePackage(72, Edge.Direction.NORTH, 71),
                new PointEdgePackage(73, Edge.Direction.EAST, 83),
                new PointEdgePackage(73, Edge.Direction.WEST, 63),
                new PointEdgePackage(73, Edge.Direction.SOUTH, 74),
                new PointEdgePackage(73, Edge.Direction.NORTH, 72),
                new PointEdgePackage(74, Edge.Direction.EAST, 84),
                new PointEdgePackage(74, Edge.Direction.WEST, 64),
                new PointEdgePackage(74, Edge.Direction.SOUTH, 75),
                new PointEdgePackage(74, Edge.Direction.NORTH, 73),
                new PointEdgePackage(75, Edge.Direction.EAST, 85),
                new PointEdgePackage(75, Edge.Direction.WEST, 65),
                new PointEdgePackage(75, Edge.Direction.SOUTH, 76),
                new PointEdgePackage(75, Edge.Direction.NORTH, 74),
                new PointEdgePackage(76, Edge.Direction.EAST, 86),
                new PointEdgePackage(76, Edge.Direction.WEST, 66),
                new PointEdgePackage(76, Edge.Direction.SOUTH, 77),
                new PointEdgePackage(76, Edge.Direction.NORTH, 75),
                new PointEdgePackage(77, Edge.Direction.EAST, 87),
                new PointEdgePackage(77, Edge.Direction.WEST, 67),
                new PointEdgePackage(77, Edge.Direction.SOUTH, 78),
                new PointEdgePackage(77, Edge.Direction.NORTH, 76),
                new PointEdgePackage(78, Edge.Direction.EAST, 88),
                new PointEdgePackage(78, Edge.Direction.WEST, 68),
                new PointEdgePackage(78, Edge.Direction.SOUTH, 79),
                new PointEdgePackage(78, Edge.Direction.NORTH, 77),
                new PointEdgePackage(79, Edge.Direction.EAST, 89),
                new PointEdgePackage(79, Edge.Direction.WEST, 69),
                new PointEdgePackage(79, Edge.Direction.NORTH, 78),
                new PointEdgePackage(80, Edge.Direction.WEST, 70),
                new PointEdgePackage(80, Edge.Direction.SOUTH, 81),
                new PointEdgePackage(81, Edge.Direction.WEST, 71),
                new PointEdgePackage(81, Edge.Direction.SOUTH, 82),
                new PointEdgePackage(81, Edge.Direction.NORTH, 80),
                new PointEdgePackage(82, Edge.Direction.WEST, 72),
                new PointEdgePackage(82, Edge.Direction.SOUTH, 83),
                new PointEdgePackage(82, Edge.Direction.NORTH, 81),
                new PointEdgePackage(83, Edge.Direction.WEST, 73),
                new PointEdgePackage(83, Edge.Direction.SOUTH, 84),
                new PointEdgePackage(83, Edge.Direction.NORTH, 82),
                new PointEdgePackage(84, Edge.Direction.WEST, 74),
                new PointEdgePackage(84, Edge.Direction.SOUTH, 85),
                new PointEdgePackage(84, Edge.Direction.NORTH, 83),
                new PointEdgePackage(85, Edge.Direction.WEST, 75),
                new PointEdgePackage(85, Edge.Direction.SOUTH, 86),
                new PointEdgePackage(85, Edge.Direction.NORTH, 84),
                new PointEdgePackage(86, Edge.Direction.WEST, 76),
                new PointEdgePackage(86, Edge.Direction.SOUTH, 87),
                new PointEdgePackage(86, Edge.Direction.NORTH, 85),
                new PointEdgePackage(87, Edge.Direction.WEST, 77),
                new PointEdgePackage(87, Edge.Direction.SOUTH, 88),
                new PointEdgePackage(87, Edge.Direction.NORTH, 86),
                new PointEdgePackage(88, Edge.Direction.WEST, 78),
                new PointEdgePackage(88, Edge.Direction.SOUTH, 89),
                new PointEdgePackage(88, Edge.Direction.NORTH, 87),
                new PointEdgePackage(89, Edge.Direction.WEST, 79),
                new PointEdgePackage(89, Edge.Direction.NORTH, 88)
        };
    }
}