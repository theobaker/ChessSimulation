import java.util.Random;

public class Piece {
	int row;
	int column;
	int health = 1;
	Random rand = new Random();  
	public void move(int originalRow, int originalColumn, int newRow, int newColumn, 
			int[][] board, int objectType) {
		board[originalRow][originalColumn] = 0;
		board[newRow][newColumn] = objectType;
	}
	public void reRandomize(){
		int i = rand.nextInt(4);
		row = i;
		int j = rand.nextInt(4);
		column = j;
	}
	public int getRow(){
		return row;
	}
	public int getColumn (){
		return column;
	}
	public void die(int board[][]) {
		health = 0;
		board[row][column] = 0;
	}
}
