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
	private int scale;
	
	private boolean isGameOver;
	
	private Random rand; // seed??
	
	public GameEngine(int scale, int bombCount) {
		validateInput(scale, bombCount);
		rand = new Random();
		
		this.bombCount = bombCount;
		this.scale = scale;
		
		isGameOver = false;
		
		intializeBoard(scale);
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
		if (row < 0 || row >= board.length || column < 0 || column >= board.length) {
			throw new IllegalArgumentException(Messages.INVALID_DATA);
		}
		
		if (!boardChecked[row][column]) {
			boardChecked[row][column] = true;
			
			if (isEqualToBomb(board[row][column])) {
				isGameOver = true;
				board[row][column] = BoardCodes.BOMB_CLICKED;
				setAllToChecked(); // do I need this??
			} 
			
			// recursive check..
		} else {
			//???
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
	
	private void intializeBoard(int scale) {
		board = new int[scale][];
		boardChecked = new boolean[scale][];
		
		for (int i = 0; i < scale; i++) {
			board[i] = new int[scale];
			boardChecked[i] = new boolean[scale];
		}
	}
	
	private void setBombs(int bombsCount) {
		for (int i = 0; i < bombsCount; i++) {
			int row = rand.nextInt(scale);
			int column = rand.nextInt(scale);
			
			if (board[row][column] == BoardCodes.BOMB) {
				i--;
				continue;
			}
			
			board[row][column] = BoardCodes.BOMB;
		}
	}
	
	private void validateInput(int scale, int bombCount) {
		if (scale < 0 || bombCount < 0) {
			throw new IllegalArgumentException(Messages.INVALID_DATA);
		}
		if (bombCount > scale) {
			throw new IllegalArgumentException(Messages.BOMBS_BIGGER_SCALE);
		}	
	}
	
	private void setAdjacentCells() {
		for (int i = 0; i < board.length; i++) {
			for(int k = 0; k < board.length; k++) {
				try {
					if (isEqualToBomb(board[i - 1][k - 1])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i - 1][k])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i - 1][k + 1])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i][k - 1])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i][k + 1])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i + 1][k - 1])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i + 1][k])) {
						board[i][k]++;
					}
					if (isEqualToBomb(board[i + 1][k + 1])) {
						board[i][k]++;
					}
					
				} catch (ArrayIndexOutOfBoundsException e) {
					
				} catch (Exception x) {
					throw x;
				}
			}
		}
	}

	private boolean isEqualToBomb(int code) {
		return code == BoardCodes.BOMB;
	}
	
	private void setAllToChecked() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				boardChecked[i][j] = true;
			}
		}
	}
}
