package com.example.beatrider;

public class GameReport  {
	
	int missCounter; 
	int badCounter;
	int okCounter;
	int goodCounter;
	int perfectCounter;
	
	public GameReport() {
		this.missCounter = 0;
		this.badCounter = 0;
		this.okCounter = 0;
		this.goodCounter = 0;
		this.perfectCounter = 0;
	}
	
	void reset() {
		this.missCounter = 0;
		this.badCounter = 0;
		this.okCounter = 0;
		this.goodCounter = 0;
		this.perfectCounter = 0;
	}
	
}
