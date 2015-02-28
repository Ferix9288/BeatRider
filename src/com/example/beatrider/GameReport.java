package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class GameReport extends GameObject  {
	
	static final int CENTER_GAP = 20;
	static final int TEXT_SPACING = 60;
	
	static final int PERFECT_SCORE = 50; 
	static final int GOOD_SCORE = 40;
	static final int OK_SCORE = 30;
	static final int BAD_SCORE = 15;
	
	static final float A_PLUS_RATING = .98f;
	static final float A_RATING = .93f;
	static final float A_MINUS_RATING = .90f;
	
	static final float B_PLUS_RATING = .88f;
	static final float B_RATING = .83f;
	static final float B_MINUS_RATING = .80f;
	
	
	static final float C_PLUS_RATING = .78f;
	static final float C_RATING = .73f;
	static final float C_MINUS_RATING = .70f;
	
	static final int GAME_RUNNING = 0;
	static final int GAME_OVER = 1;
	
	int state;
	
	int missCounter; 
	int badCounter;
	int okCounter;
	int goodCounter;
	int perfectCounter;
	
	int currentStreak;
	int maxStreak;
	int runningScore;
	int totalScore;
	
	String scoreString;
	int scoreColor;
	
	Paint paint;
	
	public GameReport(Graphics g) {
		this.missCounter = 0;
		this.badCounter = 0;
		this.okCounter = 0;
		this.goodCounter = 0;
		this.perfectCounter = 0;
		
		this.currentStreak = 0;
		this.maxStreak = 0;
		
		this.runningScore = 0;
		this.totalScore = 0; 
		
		this.paint = g.getPaint();
		
		this.state = GAME_RUNNING;
	}
	
	void reset() {
		this.missCounter = 0;
		this.badCounter = 0;
		this.okCounter = 0;
		this.goodCounter = 0;
		this.perfectCounter = 0;

		this.currentStreak = 0;
		this.maxStreak = 0;
		
		this.state = GAME_RUNNING;

	}

	@Override
	void draw(Graphics g, float deltaTime) {
		
		if (state == GAME_RUNNING) {
			
		} else if (state == GAME_OVER) {
			//Draw Report Title 
			drawReportTitle(g);
			
			drawScore(g); //A
			
			drawPerfectRating(g);
			drawGoodRating(g);
			drawOkRating(g);
			drawBadRating(g);
			drawMissRating(g);
			drawMaxCombo(g);
		}
		
	}
	
	void drawReportTitle(Graphics g) {
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(5);
		this.paint.setTextSize(100);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString("Report!", g.getWidth()/2, g.getHeight()/6, paint);		
	}
		
	void drawScore(Graphics g) {
		this.paint.setColor(this.scoreColor);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(5);
		this.paint.setTextSize(150);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString(this.scoreString, g.getWidth()/2, g.getHeight()/6+160, paint);		

		this.paint.setTextSize(45);
		this.paint.setStyle(Style.STROKE);
		this.paint.setColor(Color.WHITE);
		this.paint.setStrokeWidth(1);
		g.drawString("(" + this.runningScore + "/" 
				+ this.totalScore + ")",  g.getWidth()/2, g.getHeight()/6+230, paint);
	}
	
	void drawPerfectRating(Graphics g) {
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		paint.setARGB(0xFF, GameUtil.PERFECT_COLOR >> 16 & 0xFF, GameUtil.PERFECT_COLOR >> 8 & 0xFF, GameUtil.PERFECT_COLOR & 0xFF); 
		this.paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString("PERFECT", g.getWidth()/2 - 300, g.getHeight()/6+290, paint);		

		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		g.drawString(Integer.toString(this.perfectCounter), g.getWidth()/2 + 200, g.getHeight()/6+290, paint);		
	}

	void drawGoodRating(Graphics g) {
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		paint.setARGB(0xFF, GameUtil.GOOD_COLOR >> 16 & 0xFF, GameUtil.GOOD_COLOR >> 8 & 0xFF, GameUtil.GOOD_COLOR & 0xFF);        
		this.paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString("GOOD", g.getWidth()/2 - 300, g.getHeight()/6+290+TEXT_SPACING, paint);		

		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		g.drawString(Integer.toString(this.goodCounter), g.getWidth()/2 + 200, g.getHeight()/6+290+TEXT_SPACING, paint);		
	}
	
	void drawOkRating(Graphics g) {
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		paint.setARGB(0xFF, GameUtil.OK_COLOR >> 16 & 0xFF, GameUtil.OK_COLOR >> 8 & 0xFF, GameUtil.OK_COLOR & 0xFF);        
		this.paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString("OK", g.getWidth()/2 - 300, g.getHeight()/6+290+TEXT_SPACING*2, paint);		

		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		g.drawString(Integer.toString(this.okCounter), g.getWidth()/2 + 200, g.getHeight()/6+290+TEXT_SPACING*2, paint);		
	}
	
	
	void drawBadRating(Graphics g) {
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		paint.setARGB(0xFF, GameUtil.BAD_COLOR >> 16 & 0xFF, GameUtil.BAD_COLOR >> 8 & 0xFF, GameUtil.BAD_COLOR & 0xFF);        
		this.paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString("BAD", g.getWidth()/2 - 300, g.getHeight()/6+290+TEXT_SPACING*3, paint);		

		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		g.drawString(Integer.toString(this.badCounter), g.getWidth()/2 + 200, g.getHeight()/6+290+TEXT_SPACING*3, paint);		
	}
	
	void drawMissRating(Graphics g) {
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		paint.setARGB(0xFF, GameUtil.MISS_COLOR >> 16 & 0xFF, GameUtil.MISS_COLOR >> 8 & 0xFF, GameUtil.MISS_COLOR & 0xFF);        
		this.paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString("MISS", g.getWidth()/2 - 300, g.getHeight()/6+290+TEXT_SPACING*4, paint);		

		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		g.drawString(Integer.toString(this.missCounter), g.getWidth()/2 + 200, g.getHeight()/6+290+TEXT_SPACING*4, paint);		
	}

	void drawMaxCombo(Graphics g) {
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString("MAX COMBO", g.getWidth()/2 - 300, g.getHeight()/6+290+TEXT_SPACING*5, paint);		

		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.LEFT);
		g.drawString(Integer.toString(this.maxStreak), g.getWidth()/2 + 200, g.getHeight()/6+290+TEXT_SPACING*5, paint);		
	}

	@Override
	void update(TouchEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void setTotalScore(int numberOfBeats) {
		this.totalScore = numberOfBeats * PERFECT_SCORE;
	}
	void setScore() {
		this.state = GAME_OVER;
		
		float finalScore = this.runningScore/this.totalScore;
		if (finalScore >= A_PLUS_RATING) {
			scoreString= "A+";
			scoreColor = Color.GREEN;
		} else if (finalScore >= A_RATING) {
			scoreString = "A";
			scoreColor = Color.GREEN;
		} else if (finalScore >= A_MINUS_RATING) {
			scoreString = "A-";
			scoreColor = Color.GREEN;
		}

		else if (finalScore >= B_PLUS_RATING) {
			scoreString= "B+";
			scoreColor = Color.CYAN;

		} else if (finalScore >= B_RATING) {
			scoreString = "B";
			scoreColor = Color.CYAN;

		} else if (finalScore >= B_MINUS_RATING) {
			scoreString = "B-";
			scoreColor = Color.CYAN;

		}

		
		else if (finalScore >= C_PLUS_RATING) {
			scoreString= "C+";
			scoreColor = Color.YELLOW;
		} else if (finalScore >= C_RATING) {
			scoreString = "C";
			scoreColor = Color.YELLOW;
		} else if (finalScore >= C_MINUS_RATING) {
			scoreString = "C-";
			scoreColor = Color.YELLOW;
		}

		else {
			scoreString = "F";
			scoreColor = Color.RED;
		}
		
		comboBreaker();
	}
	
	void comboBreaker() {
		if (this.currentStreak > this.maxStreak) {
			this.maxStreak = this.currentStreak;
		}
		this.currentStreak = 0;
	}
	
	void plusCombo() {
		this.currentStreak += 1;
	}
	
}
