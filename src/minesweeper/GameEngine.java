package minesweeper;

import java.util.Arrays;
import java.util.Random;
import minesweeper.contracts.*;
import minesweeper.constants.BoardCodes;
import minesweeper.constants.Messages;

public class GameEngine implements IGameEngine {
	private int[][] board; 
	private boolean[][] boardChecked;
	//private boolean[][] flags; ???? Can't risk to implement it without the clear assignment.
	
	private int bombCount; // do I need this??
	private int rows;
	private int columns;
	
	private boolean isGameOver;
	
	private Random rand;
	
	public GameEngine(int rows, int columns, int bombCount) {
		rand = new Random();
		
		this.bombCount = bombCount;
		this.rows = rows;
		this.columns = columns;
		
		isGameOver = false;
		
		intializeBoard(rows, columns);
		setBombs(bombCount);
		setAdjacentCells();
	}

	@Override
	public int[][] getBoard() {
		return Arrays.copyOf(board, board.length);
	}
	
	@Override
	public boolean[][] getCheckedBoard() {
		return Arrays.copyOf(boardChecked, boardChecked.length);
	}
	
	@Override
	public void check(int row, int column) {
		if (row < 0 || row >= rows || column < 0 || column >= columns) {
			throw new IllegalArgumentException(Messages.INVALID_DATA);
		}
		
		if (!boardChecked[row][column]) {
			boardChecked[row][column] = true;
			
			if (isEqualToBomb(board[row][column])) {
				isGameOver = true;
				board[row][column] = BoardCodes.BOMB_CLICKED;
				setAllToChecked();
			} 
			
			// TODO: Implement recursive checking.
		}
		
	}

	@Override
	public void flag(int row, int column) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isGameOver() {
		return isGameOver;
	}
	
	private void intializeBoard(int rows, int columns) {
		board = new int[rows][];
		boardChecked = new boolean[rows][];
		
		for (int i = 0; i < rows; i++) {
			board[i] = new int[columns];
			boardChecked[i] = new boolean[columns];
		}
	}
	
	private void setBombs(int bombsCount) {
		for (int i = 0; i < bombsCount; i++) {
			int row = rand.nextInt(rows);
			int column = rand.nextInt(columns);
			
			if (board[row][column] == BoardCodes.BOMB) {
				i--;
				continue;
			}
			
			board[row][column] = BoardCodes.BOMB;
		}
	}
	
	private void setAdjacentCells() {
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < columns; k++) {
					if (isEqualToBomb(board[i][k])) {
						continue;
					}
				
					if (i - 1 >= 0) {
						if (k - 1 >= 0 && isEqualToBomb(board[i - 1][k - 1])) {
							board[i][k]++;
						}
						
						if (isEqualToBomb(board[i - 1][k])) {
							board[i][k]++;
						}
						
						if (k + 1 < columns && isEqualToBomb(board[i - 1][k + 1])) {
							board[i][k]++;
						}
					}
					
					if (k - 1 >= 0 && isEqualToBomb(board[i][k - 1])) {
						board[i][k]++;
					}
					if (k + 1 < columns && isEqualToBomb(board[i][k + 1])) {
						board[i][k]++;
					}
					
					if (i + 1 < rows) {
						if (k - 1 >= 0 && isEqualToBomb(board[i + 1][k - 1])) {
							board[i][k]++;
						}
						
						if (isEqualToBomb(board[i + 1][k])) {
							board[i][k]++;
						}
						
						if (k + 1 < columns && isEqualToBomb(board[i + 1][k + 1])) {
							board[i][k]++;
						}
					}
			}
		}
	}

	private boolean isEqualToBomb(int code) {
		return code == BoardCodes.BOMB;
	}
	
	private void setAllToChecked() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				boardChecked[i][j] = true;
			}
		}
	}
}
