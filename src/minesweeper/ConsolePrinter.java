package minesweeper;

import minesweeper.contracts.IPrinter;

public class ConsolePrinter implements IPrinter {

	public ConsolePrinter() {
		
	}
	
	@Override
	public void print(String input) {
		System.out.print(input);	
	}

	@Override
	public void printLine(String input) {
		System.out.println(input);
	}

	@Override
	public void clear(int offset) {
		for(int i = 0; i < offset; i++) {
			System.out.println();
		}
	}
	
	
}
