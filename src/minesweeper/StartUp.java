package minesweeper;

import java.util.concurrent.TimeUnit;

import minesweeper.constants.Messages;
import minesweeper.contracts.*;

public class StartUp {
	public static void main(String[] args) {
		IPrinter printer = new ConsolePrinter();
		IPrinter filePrinter = new FilePrinter("log.txt");
		
		IReader reader = new ConsoleReader();
		ITimer timer = new Timer();
		IGameEngine gameEngine = null;
		
		startGame(printer, filePrinter, reader, gameEngine, timer);
			
		String choice = null;
		while (true) {
			printer.printLine(Messages.QUESTION_USER);
			choice = reader.readInput();
			if (choice.toLowerCase().equals("n")) {
				return;
			} else if (choice.toLowerCase().equals("y")) {
				startGame(printer,filePrinter, reader, gameEngine, timer);
			}
		}
	}

	private static void startGame(IPrinter printer, IPrinter filePrinter, IReader reader, IGameEngine gameEngine, ITimer timer) {
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
		
		IInterfaceController interfaceController = new InterfaceController(printer, reader, gameEngine, timer);
		IGameController gameController = new GameController(interfaceController);
		
		timer.start();
		gameController.start();
		
		writeToFile(filePrinter, gameEngine.getBoard().length, gameEngine.getBoard()[0].length, gameEngine.getCountOfBombs(), timer.getElapsedTicks());
	}
	
	private static void writeToFile(IPrinter filePrinter, int rows, int columns, int bombs, long elapsedTicks) {
		String difficulty = "custom";
		
		if (rows == 8 && columns == 8 && bombs == 10) {
			difficulty = "beginner";
		} else if (rows == 16 && columns == 16 && bombs == 40) {
			difficulty = "advanced";
		} else if (rows == 30 && columns == 16 && bombs == 99) {
			difficulty = "professional";
		} 
		
		long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTicks);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTicks) % 60;
		
		String content = String.format(Messages.FILE_STATS, difficulty, minutes, seconds);
		filePrinter.printLine(content);
	}
}
	