package minesweeper.contracts;

import minesweeper.constants.Difficulty;

public interface IGameEngineFactory {
	IGameEngine createGameEngine(Difficulty difficulty);
}
