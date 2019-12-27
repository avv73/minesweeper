package minesweeper.contracts;

public interface IGameEngine {
	void check(int row, int column);
	void flag(int row, int column);
	
	boolean isGameOver();
	boolean isGameWon();
	
	boolean[][]	getCheckedBoard();
	boolean[][] getFlagsBoard();
	int[][] getBoard();	
	
	int getCountOfFlags();
	int getCountOfClosed();
	int getCountOfBombs();
}
