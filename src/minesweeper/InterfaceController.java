package minesweeper;

import minesweeper.constants.*;
import minesweeper.contracts.*;

public class InterfaceController implements IInterfaceController {
	private IPrinter printer;
	private IReader reader;
	private IGameEngine gameEngine;
	
	public InterfaceController(IPrinter printer, IReader reader, IGameEngine gameEngine) {
		validateInput(printer, reader, gameEngine);
		
		this.printer = printer;
		this.reader = reader;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actualize() {
		int[][] board = gameEngine.getBoard();
		boolean[][] checked = gameEngine.getCheckedBoard();
		boolean[][]	flags = gameEngine.getFlagsBoard();

		printHeader(board.length, board[0].length);
		
		for (int i = 0; i < board.length; i++) {
			if (i + 1 < 10) {
				printer.print((i + 1) + " | ");
			} else {
				printer.print((i + 1) + "| ");
			}
			
			for (int k = 0; k < board[0].length; k++) {
				if (flags[i][k]) {
					printer.print(Symbols.FLAG);
				} else if (checked[i][k]) {
					switch(board[i][k]) {
						case 0: printer.print(Symbols.EMPTY); break;
						case 1: printer.print(Symbols.ONE); break;
						case 2: printer.print(Symbols.TWO); break;
						case 3: printer.print(Symbols.THREE); break;
						case 4: printer.print(Symbols.FOUR); break;
						case 5: printer.print(Symbols.FIVE); break;
						case 6: printer.print(Symbols.SIX); break;
						case 7: printer.print(Symbols.SEVEN); break;
						case 8: printer.print(Symbols.EIGHT); break;
						case -1: printer.print(Symbols.BOMB); break;
						case -3: printer.print(Symbols.BOMB_CLICKED); break;
						default: throw new IllegalArgumentException(Messages.UNRECOGNIZED_SYMBOL); 
					}
				} else {
					printer.print(Symbols.CLOSED);
				}
				
				printer.print(" ");
			}
			
			printer.printLine("");
		}
		
		
		if (gameEngine.isGameWon()) {
			printer.printLine("You have won!");
		} else if (gameEngine.isGameOver()) {
			printer.printLine("You have lost!");
		}
	}

	@Override
	public void parseCommand() {
		if (gameEngine.isGameOver()) {
			throw new IllegalArgumentException(Messages.FALTY_PARSE_GAME_OVER);
		}
		
		String command = "";  
		int row = 0;
		String column = "";
		int columnActual = 0;
		
		while (true) {
			printer.printLine(Messages.PARSER_INPUT_MESSAGE);
			String[] commandTokens = reader.readInput().split(" ");
			
			try {
				command = commandTokens[0].toLowerCase();
				row = Integer.parseInt(commandTokens[1]);
				column = commandTokens[2].toLowerCase();
				
				if (column.length() > 1) {
					continue;
				}
				
				columnActual = column.charAt(0) - 97;		
			} catch (Exception e) {
				continue;
			}
			
			if (command.equals(Messages.COMMAND_CHECK)) {
				gameEngine.check(row - 1, columnActual);
				break;
			} else if (command.equals(Messages.COMMAND_FLAG)) {
				gameEngine.flag(row - 1, columnActual);
				break;
			}
		} 
		
	}

	@Override
	public boolean isOpen() {
		return !gameEngine.isGameWon() && !gameEngine.isGameOver();
	}
	
	private void validateInput(IPrinter printer, IReader reader, IGameEngine gameEngine) {
		if (printer == null || reader == null || gameEngine == null) {
			throw new IllegalArgumentException(Messages.ARGUMENTS_NULL);
		}
	}
	
	private void printHeader(int lengthRows, int lengthColumns) {
		printer.clear(lengthRows*2);	
		
		printer.print("    ");
		for (int i = 0; i < lengthColumns; i++) {
			printer.print((char)(65 + i) + " ");
		}
		
		printer.printLine("");
		
		printer.print("   ");
		for (int i = 0; i < lengthColumns*2; i++) {
			printer.print("-");
		}		
		
		printer.printLine("");	
	}
}
