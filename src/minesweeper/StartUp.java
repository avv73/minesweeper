package minesweeper;

import minesweeper.contracts.*;

public class StartUp {
	public static void main(String[] args) {
		IPrinter printer = new ConsolePrinter();
		IReader reader = new ConsoleReader();
		
		IGameEngine gameEngine = new GameEngine(6, 3); // think of way user to input...
		IInterfaceController interfaceController = new InterfaceController(printer, reader ,gameEngine);
		IGameController gameController = new GameController(interfaceController);
		gameController.start();
	}
}
	