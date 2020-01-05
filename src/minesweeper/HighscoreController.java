package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import minesweeper.constants.Messages;
import minesweeper.contracts.IFileParseInfo;
import minesweeper.contracts.IHighscoreController;
import minesweeper.contracts.IPrinter;
import minesweeper.contracts.IReader;

public class HighscoreController implements IHighscoreController {
	private static final String PATTERN_LOGFILE = "(?<dif>[A-Za-z]+) (?<min>\\d+):(?<sec>\\d+)";
	
	private IPrinter consolePrinter;
	private IPrinter filePrinter;
	private IReader fileReader;
	
	public HighscoreController(IPrinter consolePrinter, IPrinter filePrinter, IReader fileReader) {
		this.consolePrinter = consolePrinter;
		this.filePrinter = filePrinter;
		this.fileReader = fileReader;
	}
	
	@Override
	public void log(int rows, int columns, int bombs, long elapsedTicks) {
		String difficulty = "custom";
		
		if (rows == 8 && columns == 8 && bombs == 10) {
			difficulty = "beginner";
		} else if (rows == 16 && columns == 16 && bombs == 40) {
			difficulty = "advanced";
		} else if (rows == 30 && columns == 16 && bombs == 99) {
			difficulty = "professional";
		} 
		
		long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTicks);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTicks) % 60;
		
		String content = String.format(Messages.FILE_STATS, difficulty, minutes, seconds);
		filePrinter.printLine(content);
	}
	@Override
	public void printHighscores() {
		String[] contents = fileReader.readInput().split(System.lineSeparator());
		List<IFileParseInfo> fileInfos = new ArrayList<IFileParseInfo>();
		
		Pattern r = Pattern.compile(PATTERN_LOGFILE);
		
		for (String string : contents) {
			Matcher m = r.matcher(string);
			if (m.find()) {
				String difficulty = m.group("dif");
				long minutes = Long.parseLong(m.group("min"));
				long seconds = Long.parseLong(m.group("sec"));
				
				fileInfos.add(new FileParseInfo(difficulty, minutes, seconds));
			}
		}
		
		List<IFileParseInfo> beginnerInfo = sortLogsAboutDifficulty(fileInfos, "beginner");
		List<IFileParseInfo> advancedInfo = sortLogsAboutDifficulty(fileInfos, "advanced");
		List<IFileParseInfo> professionalInfo = sortLogsAboutDifficulty(fileInfos, "professional");
		List<IFileParseInfo> customInfo = sortLogsAboutDifficulty(fileInfos, "custom");
		
		if (beginnerInfo.size() == 0 && advancedInfo.size() == 0 && professionalInfo.size() == 0 && customInfo.size() == 0) {
			return;
		}
		
		consolePrinter.printLine("Highscores:\n");
		printListInfo(beginnerInfo, "Beginner");
		printListInfo(advancedInfo, "Advanced");
		printListInfo(professionalInfo, "Professional");
		printListInfo(customInfo, "Custom");
	}
	
	private void printListInfo(List<IFileParseInfo> info, String diffName) {
		int counter = 1;
		
		if (info.size() == 0) {
			return;
		}
		
		consolePrinter.printLine(diffName + ":");
		for (int i = 0; i < Math.min(info.size(), 5); i++) {
			IFileParseInfo currentInfo = info.get(i);
			
			consolePrinter.printLine(String.format("	%d.    %d:%d", counter, currentInfo.getMinutes(), currentInfo.getSeconds()));
			counter++;
		}
	}
	
	private List<IFileParseInfo> sortLogsAboutDifficulty(List<IFileParseInfo> logsInfo, String difficulty) {
		return logsInfo.stream().filter(e -> e.getDifficulty().equals(difficulty)).sorted().collect(Collectors.toList());
	}
}
