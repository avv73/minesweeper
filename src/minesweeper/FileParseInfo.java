package minesweeper;

import minesweeper.contracts.IFileParseInfo;

public class FileParseInfo implements IFileParseInfo, Comparable {
	private String difficulty;
	private long minutes;
	private long seconds;
	
	public FileParseInfo(String difficulty, long minutes, long seconds) {
		this.difficulty = difficulty;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	@Override
	public String getDifficulty() {
		return difficulty;
	}

	@Override
	public long getMinutes() {
		return minutes;
	}

	@Override
	public long getSeconds() {
		return seconds;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof FileParseInfo) {
			FileParseInfo fi = (FileParseInfo) o;
			return (int)(calculateTotalSeconds() - fi.calculateTotalSeconds());
		}
		
		throw new IllegalArgumentException("Object cannot be compared to!");
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof FileParseInfo) {
			return compareTo(o) == 0;
		}
		
		return false;
	}
	
	private long calculateTotalSeconds() {
		return getMinutes() * 60 + getSeconds();
	}
}
