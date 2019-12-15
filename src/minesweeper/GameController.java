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
		while(interfaceController.isOpen()) {
			interfaceController.actualize();
			interfaceController.parseCommand();
		}
	}
	
	private void validateInput(IInterfaceController interfaceController) {
		if (interfaceController == null) {
			throw new IllegalArgumentException(Messages.ARGUMENTS_NULL);
		}
	}

}
