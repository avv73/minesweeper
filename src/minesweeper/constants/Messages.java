package minesweeper.constants;

public final class Messages {
	private Messages() {
		
	}
	
	public static final String UNRECOGNIZED_SYMBOL = "Catched an unrecognized symbol!";
	public static final String FALTY_PARSE_GAME_OVER = "Unable to parse when game over!";
	public static final String PARSER_INPUT_MESSAGE = "<check/flag> <row> <column>:";
	public static final String ARGUMENTS_NULL = "Arguments cannot be null!";
	public static final String INVALID_DATA = "Invalid input data!";
	public static final String BOMBS_BIGGER_SCALE = "Bombs cannot be bigger than scale!";
	public static final String COMMAND_CHECK = "check";
	public static final String COMMAND_FLAG = "flag";
	public static final String STARTUP = "Please enter difficulty: (beginner, advanced, professional, custom):"; 
}
