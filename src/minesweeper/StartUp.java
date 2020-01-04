package minesweeper;

import minesweeper.constants.Messages;
import minesweeper.contracts.*;

public class StartUp {
	private static final String LOG_FILE_PATH = "log.txt";
	
	public static void main(String[] args) {
		IPrinter printer = new ConsolePrinter();
		IPrinter filePrinter = new FilePrinter(LOG_FILE_PATH);
		
		IReader reader = new ConsoleReader();
		IReader fileReader = new FileParser(LOG_FILE_PATH);
		
		ITimer timer = new Timer();
		
		IHighscoreController highscoreController = new HighscoreController(printer, filePrinter, fileReader);
		
		IGameStarter gameStarter = new GameStarter(printer, highscoreController, reader, timer);
		gameStarter.startGame();
			
		String choice = null;
		while (true) {
			printer.printLine(Messages.QUESTION_USER);
			choice = reader.readInput();
			if (choice.toLowerCase().equals("n")) {
				return;
			} else if (choice.toLowerCase().equals("y")) {
				gameStarter.startGame();
			}
		}
	}
}
	