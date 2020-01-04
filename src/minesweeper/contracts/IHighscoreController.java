package minesweeper.contracts;

public interface IHighscoreController {
	void log(int rows, int columns, int bombs, long elapsedTicks);
	void printHighscores();
}
