package minesweeper;

import minesweeper.contracts.IGameStarter;
import minesweeper.contracts.IPrinter;
import minesweeper.contracts.IReader;
import minesweeper.contracts.ITimer;

public class Test {
	private static final String LOG_FILE_PATH = "log.txt";
	public static void main(String[] args) {
			
		IPrinter printer = new ConsolePrinter();
		IPrinter filePrinter = new FilePrinter(LOG_FILE_PATH);
		
		IReader reader = new ConsoleReader();
		IReader fileReader = new FileParser(LOG_FILE_PATH);
		
		ITimer timer = new Timer();
		
		//IGameStarter gameStarter = new GameStarter(printer, filePrinter, reader, fileReader, timer);
		//gameStarter.startGame();
	}
}
