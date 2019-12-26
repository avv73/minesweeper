package minesweeper;

import minesweeper.constants.Messages;
import minesweeper.contracts.*;

public class GameController implements IGameController {
	private IInterfaceController interfaceController;
	
	public GameController(IInterfaceController interfaceController) {
		validateInput(interfaceController);
		
		this.interfaceController = interfaceController;
	}
	
	@Override
	public void start() {
		// try-catch block here..
		while (interfaceController.isOpen()) {
			interfaceController.actualize();
			interfaceController.parseCommand();
		}
		
		interfaceController.actualize();
	}
	
	private void validateInput(IInterfaceController interfaceController) {
		if (interfaceController == null) {
			throw new IllegalArgumentException(Messages.ARGUMENTS_NULL);
		}
	}

}
