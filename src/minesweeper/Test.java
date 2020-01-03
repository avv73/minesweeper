package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import minesweeper.contracts.IFileParseInfo;
import minesweeper.contracts.IPrinter;
import minesweeper.contracts.IReader;

public class Test {
	public static void main(String[] args) {
		IReader fileReader = new FileParser("log.txt");
		IPrinter consolePrinter = new ConsolePrinter();
		
		printHighscores(fileReader, consolePrinter);
	}
	
	private static void printHighscores(IReader fileReader, IPrinter consolePrinter) {
		// TODO: Find solution here. Extracted info from file, but how to store and sort it?
		String[] contents = fileReader.readInput().split(System.lineSeparator());
		
		String capturePattern = "(?<dif>[A-Za-z]+) (?<min>\\d+):(?<sec>\\d+)";
		List<IFileParseInfo> fileInfos = new ArrayList<IFileParseInfo>();
		
		Pattern r = Pattern.compile(capturePattern);
		
		for (String string : contents) {
			Matcher m = r.matcher(string);
			if (m.find()) {
				String difficulty = m.group("dif");
				long minutes = Long.parseLong(m.group("min"));
				long seconds = Long.parseLong(m.group("sec"));
				
				fileInfos.add(new FileParseInfo(difficulty, minutes, seconds));
			}
		}
		
		List<IFileParseInfo> beginnerInfo = fileInfos.stream().filter(e -> e.getDifficulty().equals("beginner")).sorted().collect(Collectors.toList());
		List<IFileParseInfo> advancedInfo = fileInfos.stream().filter(e -> e.getDifficulty().equals("advanced")).sorted().collect(Collectors.toList());
		List<IFileParseInfo> professionalInfo = fileInfos.stream().filter(e -> e.getDifficulty().equals("professional")).sorted().collect(Collectors.toList());
		List<IFileParseInfo> customInfo = fileInfos.stream().filter(e -> e.getDifficulty().equals("custom")).sorted().collect(Collectors.toList());
		
		if (beginnerInfo.size() == 0 && advancedInfo.size() == 0 && professionalInfo.size() == 0 && customInfo.size() == 0) {
			return;
		}
		
		consolePrinter.printLine("Highscores:\n");
		printListInfo(beginnerInfo, "Beginner", consolePrinter);
		printListInfo(advancedInfo, "Advanced", consolePrinter);
		printListInfo(professionalInfo, "Professional", consolePrinter);
		printListInfo(customInfo, "Custom", consolePrinter);
	}
	
	private static void printListInfo(List<IFileParseInfo> info, String diffName, IPrinter consolePrinter) {
		int counter = 1;
		
		if (info.size() == 0) {
			return;
		}
		
		consolePrinter.printLine(diffName + ":");
		for (int i = 0; i < info.size(); i++) {
			IFileParseInfo currentInfo = info.get(i);
			
			consolePrinter.printLine(String.format("	%d.    %d:%d", counter, currentInfo.getMinutes(), currentInfo.getSeconds()));
			counter++;
		}
	}
}
