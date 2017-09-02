package com.example.beatrider;

import java.util.ArrayList;


public class Song {
	
	String name;
	String artist;
	int duration; //How long the song is
	int bpm; //The BPM
	String songFile;
	ArrayList<Beat> beatPattern; //The Beat Pattern for this song
	
	
	public Song() {
		
	}
	
	public Song(String name, String artist, int duration, int bpm, String songFile, ArrayList<Beat> beatPattern ) {
		this.name = name;
		this.artist = artist;
		this.duration = duration;
		this.bpm = bpm;
		this.songFile = songFile;
		this.beatPattern = beatPattern;
	}
}