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
	static final float LENIENCY = 10;
	static final float ON_DURATION = 200; //2 seconds
	

	static final float OK_TIMING = ON_DURATION*.6f;
	static final float GOOD_TIMING = ON_DURATION*.8f;
	static final float PERFECT_TIMING = ON_DURATION*.95f;
	
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
	int alpha;
	
	
	public BeatCircle() {
		this.visible = true;
		
        // Defining a paint object
        this.paint = new Paint();
        this.paint.setTextSize(50);
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
		this.yDown = y + radius;
		this.yUp = y - radius;
		
		this.state = ON;
		this.lifeSpan = 0;
		this.wordLifeSpan = 0;
		this.alpha = 0xFF;
	}
	
	void draw(Graphics g) {
		int color = 0xFFFF0000; //Alpha = 0xFF; Red = 0xFF
		
		switch(this.state) {
			case ON: {
				g.drawCircle(this.xLocation, this.yLocation, this.radius, color,  Style.STROKE);
				float sweepAngle = 360 * (lifeSpan/ON_DURATION);
				g.drawArc(xLeft, yUp, xRight, yDown, -90, sweepAngle, true, paint);
				break;
				//else time runs out
			}
			
			case RATING: {
				
				alpha = 0xF0 - (0xF0/FLOAT_TIME)*this.wordLifeSpan;
				this.paint.setAlpha(alpha);	
				
				switch (this.rating) {
					case Miss:
						paint.setARGB(alpha, 0xFF, 0x00, 0x00); //Red        
						g.drawString("Miss!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					case Bad:
						paint.setARGB(alpha, 0xFF, 0x00, 0xFF); //Magenta        
						g.drawString("Bad!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					case Ok:
						paint.setARGB(alpha, 0x00, 0xFF, 0xFF); //Cyan
						g.drawString("Ok!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;						
					case Good:
						paint.setARGB(alpha, 0x00, 0x00, 0xFF); //Blue
						g.drawString("Good!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					case Perfect:
				        this.paint.setColor(Color.GREEN);
				        paint.setARGB(alpha, 0x00, 0xFF, 0x00); //Green
						g.drawString("Perfect!", this.xLocation, this.yLocation-this.wordLifeSpan, paint);
						break;
					default:
						break;
				} //end switch
							
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
				
				if (lifeSpan > ON_DURATION + LENIENCY){
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
		if (x >= xLeft && x <= xRight) {
			if (y >= yUp && y <= yDown) {
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