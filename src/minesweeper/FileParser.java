package minesweeper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import minesweeper.contracts.IReader;

public class FileParser implements IReader {
	private String filePath;
	
	public FileParser(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String readInput() {
		StringBuilder sb = new StringBuilder();
		int ch;
		
		try {
			FileReader fileReader = new FileReader(filePath);
			while ((ch = fileReader.read()) != -1) {
				sb.append((char)ch);
			}
			
			fileReader.close();
		} catch (FileNotFoundException e) { } 
		catch (IOException e) { }
		
		return sb.toString();
	}
}
