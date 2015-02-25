package com.example.beatrider;

import java.util.ArrayList;


public class Song {
	
	int duration; //How long the song is
	int bpm; //The BPM
	String songFile;
	ArrayList<Beat> beatPattern; //The Beat Pattern for this song
	
	
	public Song() {
		
	}
	
	public Song(int duration, int bpm, String songFile, ArrayList<Beat> beatPattern ) {
		this.duration = duration;
		this.bpm = bpm;
		this.songFile = songFile;
		this.beatPattern = beatPattern;
	}
}