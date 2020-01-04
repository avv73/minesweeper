package minesweeper;

import minesweeper.constants.Messages;
import minesweeper.contracts.*;

public class GameStarter implements IGameStarter {
	private IPrinter consolePrinter;
	private IHighscoreController highscoreController;
	private IReader consoleReader;
	private IGameEngine gameEngine;
	private ITimer timer;
	
	public GameStarter(IPrinter consolePrinter, IHighscoreController highscoreController, 
			IReader consoleReader, ITimer timer) {
		this.consolePrinter = consolePrinter;
		this.consoleReader = consoleReader;
		this.highscoreController = highscoreController;
		this.timer = timer;
	}
	
	@Override
	public void startGame() {
		highscoreController.printHighscores();
		consolePrinter.printLine("");
		
		boolean isInputFalty = false;
		
		do {
			isInputFalty = false;
			
			consolePrinter.printLine(Messages.STARTUP);
			String difficulty = consoleReader.readInput();
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
				consolePrinter.printLine("Rows:");
				int userRows = Integer.parseInt(consoleReader.readInput());
				consolePrinter.printLine("Columns:");
				int userColumns = Integer.parseInt(consoleReader.readInput());
				consolePrinter.printLine("Bombs:");
				int userBombs = Integer.parseInt(consoleReader.readInput());
				
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
		
		IInterfaceController interfaceController = new InterfaceController(consolePrinter, consoleReader, gameEngine, timer);
		IGameController gameController = new GameController(interfaceController);
		
		timer.start();
		gameController.start();
		
		highscoreController.log(gameEngine.getBoard().length, gameEngine.getBoard()[0].length, gameEngine.getCountOfBombs(), timer.getElapsedTicks());
	}
}