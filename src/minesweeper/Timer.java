package minesweeper;

import java.util.Date;

import minesweeper.contracts.ITimer;

public class Timer implements ITimer {
	private Date beginTime;	
	
	public Timer() {	
	}
	
	@Override
	public void start() {
		beginTime = new Date();
	}

	@Override
	public long getElapsedTicks() {
		Date currentTime = new Date();
		long elapsedTicks = currentTime.getTime() - beginTime.getTime();
		return elapsedTicks;
	}
	
}
