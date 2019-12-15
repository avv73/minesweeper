package minesweeper.contracts;

public interface IGameEngine {
	void check(int row, int column);
	void flag(int row, int column);
	
	boolean isGameOver();
	
	boolean[][]	getCheckedBoard();
	int[][] getBoard();
}
