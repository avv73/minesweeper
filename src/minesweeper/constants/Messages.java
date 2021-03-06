package minesweeper.constants;

public final class Messages {
	private Messages() {
		
	}
	
	public static final String UNRECOGNIZED_SYMBOL = "Catched an unrecognized symbol!";
	public static final String PARSER_INPUT_MESSAGE = "<check/flag> <row> <column>:";
	public static final String ARGUMENTS_NULL = "Arguments cannot be null!";
	public static final String INVALID_DATA = "Invalid input data!";
	public static final String COMMAND_CHECK = "check";
	public static final String COMMAND_FLAG = "flag";
	public static final String STARTUP = "Please enter difficulty (beginner, advanced, professional, custom): "; 
	public static final String GAME_WON = "You have won!";
	public static final String GAME_LOST = "You have lost!";
	public static final String QUESTION_USER = "Do you want to play again? <Y/N>";
	public static final String ELAPSED_TIME = "Total play time: %d:%d";
	public static final String CURRENT_TIME = "Current play time: %d:%d";
	public static final String USER_INFO = "Hidden fields: %d, flagged fields: %d, mines: %d";
	public static final String FILE_STATS = "%s %d:%d";
}
