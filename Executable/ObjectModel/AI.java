package Executable.ObjectModel;

import java.util.ArrayList;
import java.util.Iterator;

import Executable.Game;

public class AI {
	Game game;
	public static boolean virtualMoving = false;

	public AI(Game g) {
		game = g;
	}

	/**
	 * @author StepTNT,
	 *         http://stackoverflow.com/questions/27527090/finding-the-best-move
	 *         -using-minmax-with-alpha-beta-pruning
	 *
	 */
	private MoveValue minMax(double alpha, double beta, int maxDepth, String fakeCurrentPlayer, String callerSide) {
		if (!game.board.canContinue()) {
			return new MoveValue();
		}

		ArrayList<Move> moves = sortMoves(game.board.generateAllValidMoves(fakeCurrentPlayer));
		Iterator<Move> movesIterator = moves.iterator();
		double value = 0;
		boolean isMaximizer = (callerSide.equals(fakeCurrentPlayer));
		if (maxDepth == 0 || !game.board.canContinue()) {
			return new MoveValue(value);
		}

		MoveValue returnMove;
		MoveValue bestMove = null;
		if (isMaximizer) {
			while (movesIterator.hasNext()) {
				Move currentMove = movesIterator.next();
				AI.virtualMoving = true;
				game.computerMakeMove(currentMove);
				returnMove = minMax(alpha, beta, maxDepth - 1,
						game.sides.get((game.sides.indexOf(fakeCurrentPlayer) + 1) % game.sides.size()), callerSide);
				game.undo(1);
				AI.virtualMoving = false;
				if ((bestMove == null) || (bestMove.returnValue < returnMove.returnValue)) {
					bestMove = returnMove;
					bestMove.returnMove = currentMove;
				}
				if (returnMove.returnValue > alpha) {
					alpha = returnMove.returnValue;
					bestMove = returnMove;
				}
				if (beta <= alpha) {
					bestMove.returnValue = beta;
					bestMove.returnMove = null;
					return bestMove; // pruning
				}
			}
			return bestMove;
		} else {
			while (movesIterator.hasNext()) {
				Move currentMove = movesIterator.next();
				AI.virtualMoving = true;
				game.computerMakeMove(currentMove);
				returnMove = minMax(alpha, beta, maxDepth - 1,
						game.sides.get((game.sides.indexOf(fakeCurrentPlayer) + 1) % game.sides.size()), callerSide);
				game.undo(1);
				AI.virtualMoving = false;
				if ((bestMove == null) || (bestMove.returnValue > returnMove.returnValue)) {
					bestMove = returnMove;
					bestMove.returnMove = currentMove;
				}
				if (returnMove.returnValue < beta) {
					beta = returnMove.returnValue;
					bestMove = returnMove;
				}
				if (beta <= alpha) {
					bestMove.returnValue = alpha;
					bestMove.returnMove = null;
					return bestMove; // pruning
				}
			}
			return bestMove;
		}
	}
	
	public Move getBestMove(int maxDepth, String currentPlayer, String callerSide) {
		return minMax(Integer.MIN_VALUE, Integer.MAX_VALUE, maxDepth, currentPlayer, callerSide).returnMove;
	}
	
	private ArrayList<Move> sortMoves(ArrayList<Move> validMoves) {
		return validMoves;
	}

	private class MoveValue {
		public double returnValue;
		public Move returnMove;

		public MoveValue() {
			returnValue = 0;
		}

		public MoveValue(double returnValue) {
			this.returnValue = returnValue;
		}

		public MoveValue(double returnValue, Move returnMove) {
			this.returnValue = returnValue;
			this.returnMove = returnMove;
		}
	}
}