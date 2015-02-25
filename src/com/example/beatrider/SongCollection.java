package com.example.beatrider;

import java.util.ArrayList;

import com.example.beatrider.Beat.BeatType;


public class SongCollection {
	
	static Song BestDay; //American Authors - Best Day of my Life
	
	static {
		ArrayList<Beat> bestDayBeatPattern = new ArrayList<Beat>();
	
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"140", "757", "0, 500.2,600.25", "3"}, 626.757f));		
		BestDay = new Song(58, 120, "BestDayCut.mp3", bestDayBeatPattern);
		
	}
	
	
}