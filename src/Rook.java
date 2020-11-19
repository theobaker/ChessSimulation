import java.util.Random;
public class Rook extends Piece {
	Random rand = new Random();
	Rook(){
		int objectType = 1;
		int health = 1;
		int i = rand.nextInt(4);
		row = i;
		int j = rand.nextInt(4);
		column = j;
	}

	public String move(int board[][]) {
		Random randomizer1 = new Random();
		int randRookNumberOfSquares = randomizer1.nextInt(4);
		int directionForMovement = randomizer1.nextInt(3);

		//move right
		if (directionForMovement == 0) {
			if (errorCheckForMove(row, column, row, column + randRookNumberOfSquares, board) == 0){
				return "fail"; 
				/*
				 * 
				 * necessitates this method call runs in a loop so that 
				 * it keeps calling it until it gets a success
				 * 
				 */
			}
			move(row, column, row, column + randRookNumberOfSquares, board, 1);
			//			board[row][column] = 0;
			//			column = column + randRookNumberOfSquares;
			//			board[row][column] = 1;
		}

		//move down
		if (directionForMovement == 1) {
			if(errorCheckForMove(row, column, row + randRookNumberOfSquares, column, board) == 0) {
				return "fail";
			}
			move(row, column, row + randRookNumberOfSquares, column, board, 1);

			//			board[row][column] = 0;
			//			row = row + randRookNumberOfSquares;
			//			board[row][column] = 1;
		}

		//move left
		if (directionForMovement == 2) {
			if(errorCheckForMove(row, column, row, column - randRookNumberOfSquares, board) == 0) {
				return "fail";
			}
			move(row, column, row, column - randRookNumberOfSquares, board, 1);	
			//			board[row][column] = 0;
			//			column = column - randRookNumberOfSquares;
			//			board[row][column] = 1;
		}

		//move up
		if (directionForMovement == 3) {
			if(errorCheckForMove(row, column, row - randRookNumberOfSquares, column, board) == 0) {
				return "fail";
			}
			move(row, column, row - randRookNumberOfSquares, column, board, 1);
			//			board[row][column] = 0;
			//			row = row - randRookNumberOfSquares;
			//			board[row][column] = 1;
		}
		return "success";
	}
	public int errorCheckForMove(int oldRow, int oldColumn, int newRow, int newColumn, int[][] board) {
		if (newColumn > 3 || newColumn < 0 || newRow > 3 || newRow < 0) {
			return 0;
		}
		int startingColumn = Math.min(oldColumn, newColumn);
		for (int i = startingColumn; i <= Math.abs(newColumn - oldColumn); i++) {
			if(board[newRow][i] == 3 || board[newRow][i] == 1) {
				return 0;
			}
		}
		int startingRow = Math.min(oldRow, newRow);
		for (int i = startingRow; i <= Math.abs(newRow - oldRow); i++) {
			if(board[i][newColumn] == 3 || board[i][newColumn] == 1) {
				return 0;
			}
		}

		return 1;
	}
}

