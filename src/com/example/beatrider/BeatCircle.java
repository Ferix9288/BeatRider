package com.example.beatrider;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.util.Log;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class BeatCircle extends GameObject {
	
	static final int ON = 0;
	static final int RATING = 1;
	static final int DONE = 2;
	
	static final int FLOAT_TIME = 100;
	private static final boolean DEBUG = true;
	private static final String TAG = "Beat Circle";
	
	//Instance variables
	int xLocation, yLocation, radius;
	int xLeft, xRight, yDown, yUp; //Hitbox
	int state;
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
				g.drawString("Perfect!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
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
				if (e != null) {
					if (isTouched(e.x, e.y)) {
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