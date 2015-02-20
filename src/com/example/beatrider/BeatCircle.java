package com.example.beatrider;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.util.Log;

import com.example.beatrider.GameUtil.Rating;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class BeatCircle extends GameObject {
	
	static final int ON = 0;
	static final int RATING = 1;
	static final int DONE = 2;
	
	static final int FLOAT_TIME = 100;
	static final float ON_DURATION = 300;
	

	static final int BAD_TIMING = 200;
	static final int OK_TIMING = 250;
	static final int GOOD_TIMING = 270;
	static final int PERFECT_TIMING = 285;
	
	
	private static final boolean DEBUG = true;
	private static final String TAG = "Beat Circle";
	
	//Instance variables
	int xLocation, yLocation, radius;
	int xLeft, xRight, yDown, yUp; //Hitbox
	int state;
	Rating rating;
	int wordLifeSpan;
	float lifeSpan; 
	boolean visible;
	Paint paint;
	
	
	public BeatCircle() {
		this.visible = true;
		
        // Defining a paint object
        this.paint = new Paint();
        this.paint.setTextSize(30);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.WHITE);        
	}
	
	void setLocation(int x, int y, int radius) {
		this.xLocation = x;
		this.yLocation = y;
		this.radius = radius;

		this.xLeft = x - radius;
		this.xRight = x + radius;
		this.yDown = y - radius;
		this.yUp = y + radius;
		
		this.state = ON;
		this.wordLifeSpan = 0;
	}
	
	void draw(Graphics g) {
		int color = 0xFFFF0000; //Alpha = 0xFF; Red = 0xFF
		
		switch(this.state) {
			case ON: {
				g.drawCircle(this.xLocation, this.yLocation, this.radius, color,  Style.STROKE);
				break;
				//else time runs out
			}
			
			case RATING: {
				switch (this.rating) {
					case Miss:
						g.drawString("Miss!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					case Bad:
						g.drawString("Bad!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					case Ok:
						g.drawString("Ok!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
						
					case Good:
						g.drawString("Good!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					case Perfect:
						g.drawString("Perfect!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					default:
						break;
				} //end switch
				
				paint.setAlpha(0xF0 - (0xF0/FLOAT_TIME)*this.wordLifeSpan);				
				break;
			}
			
			case DONE: {
				break;
			}
		} //end switch
	}
	
	void update(TouchEvent e, float deltaTime) {
		switch(this.state) {
			case ON: {
				lifeSpan += deltaTime;
				
				if (lifeSpan > ON_DURATION){
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} else if (e != null) {
					if (isTouched(e.x, e.y)) {
						if (DEBUG) Log.i(TAG, "Touched: " + this.lifeSpan);

						if (lifeSpan >= PERFECT_TIMING) {
							rating = GameUtil.Rating.Perfect;
						} else if (lifeSpan >= GOOD_TIMING) {
							rating = GameUtil.Rating.Good;
						} else if (lifeSpan >= OK_TIMING) {
							rating = GameUtil.Rating.Ok;
						} else {
							rating = GameUtil.Rating.Bad;
						}
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
				} else {
					this.wordLifeSpan++;
				}
				break;
			}
			
			case DONE: {
				break;
			}
						
		} //end switch
		
	}
	
	/**
	 * Hit Box Detection for the Beat Circle.
	 * @param x
	 * @param y
	 */
	boolean isTouched(int x, int y) {
		if (x >= xLeft && x < xRight) {
			if (y >= yDown && y <= yUp) {
				return true;
			}
		}

		return false;
	}
	
	boolean isDone(){
		if (this.state == DONE) {
			return true;
		} else {
			return false;
		}
	}
}