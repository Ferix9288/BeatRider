package com.example.beatrider;

import java.util.ArrayList;

import com.example.beatrider.Beat.BeatType;


public class SongCollection {
	
	static Song BestDay; //American Authors - Best Day of my Life
	
	static {
		ArrayList<Beat> bestDayBeatPattern = new ArrayList<Beat>();

		bestDayBeatPattern.add( new Beat(BeatType.Drag, new String[] {"120", "922", "500,500,1000,1000", "1523.81, 3000"}, 1000f));

		BestDay = new Song(58, 120, "BestDayCut.mp3", bestDayBeatPattern);
		
	}
	
	
}