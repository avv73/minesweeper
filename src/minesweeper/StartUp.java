package minesweeper;

import minesweeper.constants.Messages;
import minesweeper.contracts.*;

public class StartUp {
	public static void main(String[] args) {
		IPrinter printer = new ConsolePrinter();
		IReader reader = new ConsoleReader();
		IGameEngine gameEngine = null;
		boolean isInputFalty = false;
		
		do {
			isInputFalty = false;
			
			printer.printLine(Messages.STARTUP);
			String difficulty = reader.readInput();
			switch (difficulty.toLowerCase()) {
			case "beginner":
				gameEngine = new GameEngine(8, 8, 10);
				break;
			case "advanced":
				gameEngine = new GameEngine(16, 16, 40);
				break;
			case "professional":
				gameEngine = new GameEngine(30, 16, 99);
				break;
			case "custom":
				printer.printLine("Rows:");
				int userRows = Integer.parseInt(reader.readInput());
				printer.printLine("Columns:");
				int userColumns = Integer.parseInt(reader.readInput());
				printer.printLine("Bombs:");
				int userBombs = Integer.parseInt(reader.readInput());
				
				if (userRows > 30 || userRows <= 0 || userColumns > 24 || userColumns <= 0 || userBombs < 0 || userBombs > 688) {
					isInputFalty = true;
				} else {
					gameEngine = new GameEngine(userRows, userColumns, userBombs);
				}	
				
				break;
			default:
				isInputFalty = true;
				break;
			}
		} while (isInputFalty);
		
		IInterfaceController interfaceController = new InterfaceController(printer, reader ,gameEngine);
		IGameController gameController = new GameController(interfaceController);
		gameController.start();
	}
}
	