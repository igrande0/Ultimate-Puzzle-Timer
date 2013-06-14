package com.isaacgrande.ultimatepuzzletimer.models;

import java.util.Locale;

public class Timer{
	private long StartTime = 0L;
	private long EndTime = 0L;
	private boolean TimerRunning = false;
	
	public void start() {
		StartTime = System.currentTimeMillis();
		TimerRunning = true;
	}
	
	public void stop() {
		EndTime = System.currentTimeMillis();
		TimerRunning = false;
	}
	
	public boolean isRunning() {
		return TimerRunning;
	}
	
	public long getElapsedTime() {
		long ElapsedTime;
		
		if (TimerRunning)
			ElapsedTime = System.currentTimeMillis() - StartTime;
		else
			ElapsedTime = EndTime - StartTime;
		
		return ElapsedTime;
	}
	
	public String getFormattedElapsedTime() {
		long ElapsedTimeMillis = this.getElapsedTime();
		
		int seconds = (int) (ElapsedTimeMillis / 1000);
        int minutes = seconds / 60;
        int hours	= minutes / 60;
        minutes		= minutes % 60;
        seconds     = seconds % 60;
        int centis	= (int)(ElapsedTimeMillis/10) % 100;
        
        if (minutes == 0 && hours == 0)
        	return String.format(Locale.US,"%02d.%02d", seconds, centis);
        else if (hours == 0)
        	return String.format(Locale.US,"%02d:%02d.%02d", minutes, seconds, centis);
        else
        	return String.format(Locale.US,"%02d:%02d:%02d.%02d", hours, minutes, seconds, centis);
	}
}
