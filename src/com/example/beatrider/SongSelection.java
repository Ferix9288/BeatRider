package com.example.beatrider;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;


public class SongSelection extends GameObject {
	
	public final int FRAME_WIDTH = 800;
	public final int FRAME_HEIGHT = 800;
	public final int SONG_SPACING = 100;
	public final int INFO_SPACING = 50;

	Song song;
	String title;
	String difficulty;
	Point center;
	Paint paint;
	PlayButton play;
	
	boolean isFocused = true;

	public SongSelection() {
		
	}
	
	public SongSelection(Song song, Graphics g) {
		this.song = song;
		this.paint = g.getPaint();
		this.center = new Point();
		this.play = new PlayButton(g, 0, 0);
	}
	
	@Override
	void draw(Graphics g, float deltaTime) {
		//Draw White Outer Box
		drawWhiteFrame(g, deltaTime);
		
		//Draw Song Title 
		drawSongTitle(g, deltaTime);

		//Draw Song Author 
		drawSongArtist(g, deltaTime);
		
		//Draw BPM 
		drawBPM(g, deltaTime);
		
		//Draw Score
		drawTopScore(g, deltaTime);

		
		//Draw Difficulty
		//drawDifficulty(g, deltaTime);
		
		//Draw Play Button 
		//if (isFocused) {
		play.draw(g, deltaTime);
		//}
		
	}
	
	void drawWhiteFrame(Graphics g, float deltaTime) {
		paint.setStrokeWidth(2);
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		g.drawRect(this.center.x-FRAME_WIDTH/2, this.center.y-FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT, Color.WHITE, Style.STROKE);

	}
	
	void drawSongTitle(Graphics g, float deltaTime) {
		this.paint.setColor(Color.RED);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(100);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString("- " + song.name + " -", this.center.x, this.center.y-FRAME_HEIGHT/4, paint);		
	}

	void drawSongArtist(Graphics g, float deltaTime) {
		this.paint.setColor(Color.RED);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString(song.artist, this.center.x, this.center.y-FRAME_HEIGHT/4+SONG_SPACING, paint);		
	}
	
	void drawBPM(Graphics g, float deltaTime) {
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString("BPM : " + Integer.toString(song.bpm), this.center.x, this.center.y-FRAME_HEIGHT/4+SONG_SPACING*2, paint);		
		
	}
	
	void drawTopScore(Graphics g, float deltaTime) {
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString("Score : N/A", this.center.x, this.center.y-FRAME_HEIGHT/4+SONG_SPACING*2+INFO_SPACING, paint);	
	}
	
	
	@Override
	void update(TouchEvent t) {
		if (isFocused) {
			this.play.update(t);
		}
	}
	
	void setCenter(int x, int y) {
		this.center.x = x;
		this.center.y = y;
		this.play.updatePosition(this.center.x, this.center.y-FRAME_HEIGHT/4+SONG_SPACING*2+INFO_SPACING*5 );
	}
}