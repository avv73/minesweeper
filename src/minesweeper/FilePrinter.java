package minesweeper;

import java.io.FileWriter;
import java.io.IOException;

import minesweeper.contracts.*;

public class FilePrinter implements IPrinter {
	private String filePath;
	
	public FilePrinter(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void print(String input) {
		try {
			FileWriter writer = new FileWriter(filePath, true);	
			writer.write(input);
			writer.close();
		} catch (IOException e) { }
	}

	@Override
	public void printLine(String input) {
		try {
			FileWriter writer = new FileWriter(filePath, true);
			writer.write(input + System.lineSeparator());
			writer.close();
		} catch (IOException e) { }	
	}

	@Override
	public void clear(int offset) {
		try {
			FileWriter writer = new FileWriter(filePath, false);
			String content = new String(new char[offset]).replace('\0', ' ');
			writer.write(content);
			writer.close();
		} catch (IOException e) { }
	}

}
