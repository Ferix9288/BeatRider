package com.example.beatrider;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

import com.example.beatrider.Beat.BeatType;
import com.example.beatrider.GameUtil.Rating;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class BeatCircle extends GameObject {
	
	static final int WAIT = 0;
	static final int ON = 1;
	static final int RATING = 2;
	static final int DONE = 3;
	
	static final int FLOAT_TIME = 50;
	static final float LENIENCY = 100;
	static final float ON_DURATION = 1500; //1.5 seconds
	
	static final float OK_TIMING = ON_DURATION*.6f;
	static final float GOOD_TIMING = ON_DURATION*.8f;
	static final float PERFECT_TIMING = ON_DURATION*.95f;
	
	static final int CIRCLE_RADIUS = 100;
	static final int TIME_ARC_DIST = 20;
	static final int LABEL_LOCATION = CIRCLE_RADIUS+TIME_ARC_DIST*2;
	
	protected static final boolean DEBUG = true;
	protected static final String TAG = "Beat Circle";
	
	//Instance variables
	int xLocation, yLocation, radius;
	int xLeft, xRight, yDown, yUp; //Hitbox
	int startingX, startingY;
	int state;
	BeatType type;

	float startTime;
	Rating rating;
	int wordLifeSpan;
	float lifeSpan; 
	Paint paint;
	int alpha;
	int comboNumber;
	
	boolean handled = false; 
	boolean drawCombo = false;
	
	
	public BeatCircle() {
		this.type = BeatType.SingleTap;		
	}
	
	public BeatCircle(int x, int y) {
		setInitialization(x,y);
	}
	
	void setInitialization(int x, int y) {
		this.startingX = x;
		this.startingY = y;
		this.xLocation = x;
		this.yLocation = y;
		
		this.xLeft = x - CIRCLE_RADIUS;
		this.xRight = x + CIRCLE_RADIUS;
		this.yDown = y + CIRCLE_RADIUS;
		this.yUp = y - CIRCLE_RADIUS;
		
		this.state = WAIT;
		this.lifeSpan = 0;
		this.wordLifeSpan = 0;
		this.comboNumber = 0;
		this.alpha = 0xFF;
		this.handled = false;
		this.drawCombo = false;
	}
	
	void setPaint(Paint globalPaint) {
		this.paint = globalPaint;
		this.paint.setTextSize(50);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.WHITE);
	}
 			
	@Override
	void draw(Graphics g, float deltaTime) {
		
		switch(this.state) {
		
			case WAIT: {
				break;
			}
			case ON: {
				lifeSpan += deltaTime;
				drawBeatCircle(g);
				drawTimeArc(g);
				break;
				//else time runs out
			}
			
			case RATING: {
				drawRating(g);
				if (this.drawCombo) drawCombo(g);
				this.wordLifeSpan++;
				break;
			}
			
			case DONE: {
				break;
			}
		} //end switch
	}

	void drawBeatCircle(Graphics g) {
		this.paint.setStrokeWidth(1);
		g.drawCircle(this.xLocation, this.yLocation, CIRCLE_RADIUS, Color.RED,  Style.STROKE);
	}
	void drawTimeArc(Graphics g) {
		float sweepAngle = 360 * (lifeSpan/ON_DURATION);
        this.paint.setColor(Color.YELLOW);        
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(10);
        g.drawArc(xLeft-TIME_ARC_DIST, yUp-TIME_ARC_DIST, 
        		xRight+TIME_ARC_DIST, yDown+TIME_ARC_DIST, -90, sweepAngle, true, paint);		
	}
	
	void drawLabel(Graphics g, String word) {
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(50);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString(word, this.xLocation, this.yLocation-LABEL_LOCATION, paint);		
	}
	
	void drawRating(Graphics g) {
		alpha = 0xF0 - (0xF0/FLOAT_TIME)*this.wordLifeSpan;
		this.paint.setTextSize(50);
        this.paint.setTextAlign(Paint.Align.CENTER);
		this.paint.setAlpha(alpha);	
        this.paint.setStrokeWidth(1);	
        this.paint.setStyle(Style.FILL_AND_STROKE);
        
		switch (this.rating) {
			case Miss:
				paint.setARGB(alpha, GameUtil.MISS_COLOR >> 16 & 0xFF, GameUtil.MISS_COLOR >> 8 & 0xFF, GameUtil.MISS_COLOR & 0xFF); //Red        
				g.drawString("Miss!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
				break;
			case Bad:
				paint.setARGB(alpha, GameUtil.BAD_COLOR >> 16 & 0xFF, GameUtil.BAD_COLOR >> 8 & 0xFF, GameUtil.BAD_COLOR & 0xFF); //Red        
				g.drawString("Bad!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
				break;
			case Ok:
				paint.setARGB(alpha, GameUtil.OK_COLOR >> 16 & 0xFF, GameUtil.OK_COLOR >> 8 & 0xFF, GameUtil.OK_COLOR & 0xFF); //Red        
				g.drawString("Ok!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
				break;						
			case Good:
				paint.setARGB(alpha, GameUtil.GOOD_COLOR >> 16 & 0xFF, GameUtil.GOOD_COLOR >> 8 & 0xFF, GameUtil.GOOD_COLOR & 0xFF); //Red        
				g.drawString("Good!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
				break;
			case Perfect:
				paint.setARGB(alpha, GameUtil.PERFECT_COLOR >> 16 & 0xFF, GameUtil.PERFECT_COLOR >> 8 & 0xFF, GameUtil.PERFECT_COLOR & 0xFF); //Red        
				g.drawString("Perfect!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
				break;
			default:
				break;
		}
	}
	
	void drawCombo(Graphics g) {
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(50);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString("COMBO! " + this.comboNumber, this.xLocation, this.yLocation-this.wordLifeSpan-LABEL_LOCATION, paint);		
		
	}
	
	void update(TouchEvent e) {
		switch(this.state) {
		
			case WAIT: {
				break;
			}
			
			case ON: {
				
				if (lifeSpan > ON_DURATION + LENIENCY){
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} else if (e != null) {
					if (isTouched(e.x, e.y)) {
						if (DEBUG) Log.i(TAG, "Touched: " + this.lifeSpan);
						setRating();						
						this.state = RATING;
					} 
				} 			
				break;
				//else time runs out
			}
			
			case RATING: {
				//if (DEBUG) Log.i(TAG, "State Rating: " + this.wordLifeSpan);
 				if (this.wordLifeSpan > FLOAT_TIME) {
					this.state = DONE;
				} 
				break;
			}
			
			case DONE: {
				break;
			}
						
		} //end switch
		
	}
	
	void setRating() {
		if (lifeSpan >= PERFECT_TIMING) {
			rating = GameUtil.Rating.Perfect;
		} else if (lifeSpan >= GOOD_TIMING) {
			rating = GameUtil.Rating.Good;
		} else if (lifeSpan >= OK_TIMING) {
			rating = GameUtil.Rating.Ok;
		} else {
			rating = GameUtil.Rating.Bad;
		}
	}
	
	/**
	 * Hit Box Detection for the Beat Circle.
	 * @param x
	 * @param y
	 */
	boolean isTouched(int x, int y) {
		if (x >= xLeft && x <= xRight) {
			if (y >= yUp && y <= yDown) {
				return true;
			}
		}

		return false;
	}

	void start() {
		this.state = ON;
	}
	
	boolean isInRating() {
		return this.state == RATING;
	}
	
	boolean isHandled() {
		return this.handled;
	}
	
	void setHandled() {
		this.handled = true;
	}
	
	void setDrawCombo(int comboNumber) {
		this.drawCombo = true;
		this.comboNumber = comboNumber;
	}
	
	boolean isDone(){
		return this.state == DONE;
	}
}