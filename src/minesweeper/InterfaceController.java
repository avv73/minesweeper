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
				
		printer.clear(board.length);	
		
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board.length; k++) {
				printer.print(Symbols.SEPERATOR);
				
				if (checked[i][k]) {
					switch(board[i][k]) {
					case 0: printer.print(Symbols.EMPTY); break;
					case 1: printer.print(Symbols.ONE); break;
					case 2: printer.print(Symbols.TWO); break;
					case 3: printer.print(Symbols.THREE); break;
					case 4: printer.print(Symbols.FOUR); break;
					case -1: printer.print(Symbols.BOMB); break;
					case -2: printer.print(Symbols.FLAG); break;
					case -3: printer.print(Symbols.BOMB_CLICKED); break;
					default: throw new IllegalArgumentException(Messages.UNRECOGNIZED_SYMBOL); 
					}
				} else {
					printer.print(Symbols.CLOSED);
				}
				
				printer.print(Symbols.SEPERATOR);
			}
			
			printer.printLine("");
			printer.printLine(new String(new char[board.length * 3]).replace('\0', Symbols.TERMINATOR.charAt(0)));
		}
		
		
	}

	@Override
	public void parseCommand() {
		if (gameEngine.isGameOver()) {
			throw new IllegalArgumentException(Messages.FALTY_PARSE_GAME_OVER);
		}
		
		String command = "";  
		int row = 0;
		int column = 0;
		while (true) {
			printer.printLine(Messages.PARSER_INPUT_MESSAGE);
			String[] commandTokens = reader.readInput().split(" ");
			
			try {
				command = commandTokens[0].toLowerCase();
				row = Integer.parseInt(commandTokens[1]);
				column = Integer.parseInt(commandTokens[2]);
			} catch (Exception e) {
				continue;
			}
			
			if (command == Messages.COMMAND_CHECK) {
				gameEngine.check(row, column);
				break;
			} else if (command == Messages.COMMAND_FLAG) {
				gameEngine.flag(row, column);
				break;
			}
		} 
		
	}

	@Override
	public boolean isOpen() {
		return !gameEngine.isGameOver();
	}
	
	private void validateInput(IPrinter printer, IReader reader, IGameEngine gameEngine) {
		if (printer == null || reader == null || gameEngine == null) {
			throw new IllegalArgumentException(Messages.ARGUMENTS_NULL);
		}
	}
}
