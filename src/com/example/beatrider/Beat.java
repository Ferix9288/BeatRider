package com.example.beatrider;

public class Beat {
    
	enum BeatType {
		SingleTap,
		Drag,
		Hold,
		MultipleTap
	}
	BeatType type; 
	String[] parameters;
	float startTime;
	
	
	public Beat(BeatType t, String[] parameters, float startTime) {
		this.type = t;
		this.parameters = parameters;
		this.startTime = startTime;
	}
		
}