import java.util.Random;

public class King extends Piece {
	String color;
	int[][] boardReplica;
	int prospectiveRow;
	int prospectiveColumn;
	King(String whiteOrBlack){
		color = whiteOrBlack;
		if (color.equals("white")) {
			row = 0;
			column = 1;
		}
		else {
			row = 3;
			column = 2;
		}
		
	}
	public String move(int[][] board, Rook rook1, Rook rook2){
//MUST INCORPORATE DIAGONAL MOVEMENT PLEASE
//TRY CATCHING ISN'T WORKING BECAUSE IT JUST STOPS CHECKING ONCE IT LOOKS AT SOMETHING OUT OF BOUNDS
		if (color.equals("white")) {
			int i = 0;
			int loopCounter = 0;
			while(i!=1) {
				i = randomMovementForAnyKing(board, 3, 7);
				loopCounter++;
				if (loopCounter == 12) {
					die(board);
					return "fail";
				}
			}
			move(row, column, prospectiveRow, prospectiveColumn, board, 3);
//something about this move statement isn't working, I speculate, because we end up with multiple white kings
//which likely means it's just not erasing old kings as it moves them (idk why) 
		}
		else if (color.equals("black")) {
// this entire thing isn't working -- it seems to not want to move the king ever
			int i = 0;
			int loopCounter = 0;
			while(i!=1) {
				i = randomMovementForAnyKing(board, 3, 7);
				try{ 
					if (board[prospectiveRow][prospectiveColumn] == 1) {

						i = 1;
						if (rook1.row == prospectiveRow && rook1.column == prospectiveColumn) {
							return "kill rook1";
						}
						if (rook2.row == prospectiveRow && rook2.column == prospectiveColumn) {
							return "kill rook2";
						}
					}
					else if (i == 1) {
						for (int r = 0; r < board.length; r ++) {
							if (board[r][prospectiveColumn] == 1) {
								i = 0;
							}
						}
						for (int c = 0; c < board[0].length; c++) {
							if (board[prospectiveRow][c] == 1) {
								i = 0;
							}
						}
					}
					loopCounter++;
					if (loopCounter == 12)
						die(board);
					return "fail";
				}
				catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
				move(row, column, prospectiveRow, prospectiveColumn, board, 7);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return "fail";
				}
			}
		}
		return "success";

	}
	public int randomMovementForAnyKing(int[][] board, int typeKing, int otherKingNum) {
		boardReplica = board;
		Random rando = new Random();
		int directionForMovement = rando.nextInt(4);

		// move right if the spot is empty
		if (directionForMovement == 0) {
			try{
				boardReplica[row][column] = 0;

				prospectiveRow = row;
				prospectiveColumn = column + 1;
				boardReplica[row][prospectiveColumn] = typeKing;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				return 0;
			}
			try{ if (boardReplica[row + 1][column] == otherKingNum || boardReplica[row+1][column+1] == otherKingNum
					|| boardReplica[row][column + 2] == otherKingNum || boardReplica[row + 1][column + 2] 
							== otherKingNum || boardReplica[row-1][column] == otherKingNum || boardReplica[row-1][column+1] 
									== otherKingNum || boardReplica[row-1][column + 2] == otherKingNum) {
				return 0;
			}
			}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (board[row][column + 1] == 0) {
					return 1;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			return 0;
		}
		// move up if the spot is empty
		else if (directionForMovement == 1) {
			try{
				boardReplica[row][column] = 0;
				prospectiveColumn = column;
				prospectiveRow = row - 1;
				boardReplica[prospectiveRow][column] = typeKing;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				return 0;
			}
			if (boardReplica[row][column + 1] == otherKingNum || boardReplica[row-1][column+1] == otherKingNum
					|| boardReplica[row][column - 1] == otherKingNum || boardReplica[row - 1][column - 1] 
							== otherKingNum || boardReplica[row-2][column] == otherKingNum || boardReplica[row-2][column+1] 
									== otherKingNum || boardReplica[row-2][column - 1] == otherKingNum) {
				return 0;
			}
			try {
				if (board[row+1][column] == 0)
					return 1;
			}
			catch (ArrayIndexOutOfBoundsException e){

			}
			return 0;
		}
		//move left if empty
		else if (directionForMovement == 2) {
			try {
				boardReplica[row][column] = 0;
				prospectiveColumn = column - 1;
				prospectiveRow = row;
				boardReplica[row][prospectiveColumn] = typeKing;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				return 0;
			}
			try {
				if (boardReplica[row + 1][column] == otherKingNum || boardReplica[row+1][column-1] == otherKingNum
						|| boardReplica[row][column - 2] == otherKingNum || boardReplica[row + 1][column - 2] 
								== otherKingNum || boardReplica[row-1][column] == otherKingNum || boardReplica[row-1][column-1] 
										== otherKingNum || boardReplica[row-1][column - 2] == otherKingNum) {
					return 0;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (board[row][column-1] == 0)
					return 1;
			}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			return 0;
		}
		//move down if empty 
		else if (directionForMovement == 3) {
			try{
				boardReplica[row][column] = 0;
				prospectiveColumn = column;
				prospectiveRow = row+1;
				boardReplica[prospectiveRow][column] = typeKing;
			} 
			catch (ArrayIndexOutOfBoundsException e){
				return 0;
			}
			try {
				if (boardReplica[row + 1][column + 1] == otherKingNum || boardReplica[row+1][column-1] == otherKingNum
						|| boardReplica[row][column + 1] == otherKingNum || boardReplica[row][column - 1] 
								== otherKingNum || boardReplica[row+2][column-1] == otherKingNum || boardReplica[row+2][column+1] 
										== otherKingNum || boardReplica[row+2][column] == otherKingNum) {
					return 0;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (board[row-1][column] == 0) {
					return 1;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			return 0;
		}
		return 0;
	}
	public void die(int[][] board) {
		health = 0;
		System.out.println();
		for (int r = 0; r < board.length; r ++) {
			System.out.println();
			for (int c = 0; c < board[0].length; c ++) {
				System.out.print(board[r][c] + "  ");
			}
		}
		// print number of moves for each character
	}
}