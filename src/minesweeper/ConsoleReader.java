package minesweeper;

import java.util.Scanner;
import minesweeper.contracts.*;


public class ConsoleReader implements IReader {
	Scanner sc;
	
	public ConsoleReader() {
		sc = new Scanner(System.in);
	}
	
	@Override
	public String readInput() {
		return sc.nextLine();
	}	
}
