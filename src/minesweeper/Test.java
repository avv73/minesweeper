package minesweeper;

import minesweeper.contracts.IReader;

public class Test {
	public static void main(String[] args) {
		IReader fileReader = new FileParser("log.txt");
		String n = fileReader.readInput();
		
		System.out.println(n);
	}
}
