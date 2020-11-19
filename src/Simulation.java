import java.util.*;
public class Simulation{
	public static void main(String[] args) {
		int[][] board = new int[4][4];
		King whiteKing = new King("white");
		board[whiteKing.getRow()][whiteKing.getColumn()] = 3;
		King blackKing = new King("black");
		board[blackKing.getRow()][blackKing.getColumn()] = 7;
		Rook rook1 = new Rook();
		while (board[rook1.getRow()][rook1.getColumn()] == 1 
				|| board[rook1.getRow()][rook1.getColumn()] == 7 
				|| board[rook1.getRow()][rook1.getColumn()] == 3) {
			rook1.reRandomize();
		}
		board[rook1.getRow()][rook1.getColumn()] = 1;
		Rook rook2 = new Rook();
		while (board[rook2.getRow()][rook2.getColumn()] == 1 
				|| board[rook2.getRow()][rook2.getColumn()] == 3 
				|| board[rook2.getRow()][rook2.getColumn()] == 7) {
			rook2.reRandomize();
		}
		board[rook2.getRow()][rook2.getColumn()] = 1;
		for (int r = 0; r < board.length; r ++) {
			System.out.println();
			for (int c = 0; c < board[0].length; c ++) {
				System.out.print(board[r][c] + "  ");
			}
		}
		int numTurnsTaken = 0;
		String simContinuation = "canthisbeover";
		while (simContinuation.equals("canthisbeover")) {
			if (numTurnsTaken % 2 == 0) {
				simContinuation = whiteMove(board, rook1, rook2, whiteKing);
			}
			else {
				simContinuation = blackMove(board, rook1, rook2, blackKing);
			}
			numTurnsTaken ++;
			System.out.println();
			for (int r = 0; r < board.length; r ++) {
				System.out.println();
				for (int c = 0; c < board[0].length; c ++) {
					System.out.print(board[r][c] + "  ");
				}
			}
		}
		if (numTurnsTaken == 25) {
			simContinuation = "too long";
		}
		
	}
	public static String whiteMove(int[][] board, Rook rook1, Rook rook2, King whiteKing) {
		Random randomizerForWhichPiece = new Random();
		int loopCounterForMovement = 1;
		int whichWhitePiece = randomizerForWhichPiece.nextInt(3);
		if (whichWhitePiece == 0 && rook1.health == 1) {
			while (loopCounterForMovement != 0) {
				if(rook1.move(board).contentEquals("success")) {
					loopCounterForMovement = 0;
				}
				else if (loopCounterForMovement == 12) {
					rook1.die(board);
				}
			}
		}
		else if (whichWhitePiece == 1 && rook2.health == 1) {
			while (loopCounterForMovement != 0) {
				if(rook2.move(board).contentEquals("success")) {
					loopCounterForMovement = 0;
				}
				else if (loopCounterForMovement == 12) {
					rook2.die(board);
				}
			}
		}
		else if (whichWhitePiece == 2) {
			String kingMove = whiteKing.move(board, rook1, rook2);
			if (kingMove.contentEquals("fail")) {
				return "we're done";
			}
		}
		else 
			whiteMove(board, rook1, rook2, whiteKing);
		return "canthisbeover";
	}
	public static String blackMove(int[][] board, Rook rook1, Rook rook2, King blackKing) {
		String kingMove = blackKing.move(board, rook1, rook2);
		if (kingMove.contentEquals("fail")) {
			return "finally";
		}
		if (kingMove.contentEquals("kill rook1")) {
			rook1.die(board);
		}
		if (kingMove.contentEquals("kill rook2")) {
			rook2.die(board);
		}
		
		return "canthisbeover";
	}
	
}