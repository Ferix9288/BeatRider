package com.example.beatrider;


import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class HoldCircle extends BeatCircle {

	static final int HOLD = 3;
	
	static final String TAG = "Hold Circle";
	static final boolean DEBUG = true;
	
	
	//Additional Instance Variables
	float holdDuration;
	float holdUserDuration;
	
	public HoldCircle() {
		super();
	}
	
	public HoldCircle(int x, int y, float holdDuration) {
		setInitialization(x, y, holdDuration);		
	}
	
	void setInitialization(int x, int y, float holdDuration) {
		super.setInitialization(x, y);
		
		this.holdDuration = holdDuration;
		this.holdUserDuration = 0;
	}
	
	@Override
	void draw(Graphics g, float deltaTime) {
		
		switch(this.state) {
			case ON: {
				lifeSpan += deltaTime;
				g.drawCircle(this.xLocation, this.yLocation, CIRCLE_RADIUS, Color.RED,  Style.STROKE);
				drawTimeArc(g);
				if (lifeSpan >= OK_TIMING) {
					drawLabel(g,"Hold!");									
				} else {
					drawLabel(g,"Wait!");
				}
				break;
				//else time runs out
			}
			
			case HOLD: {
				holdUserDuration += deltaTime;
				g.drawCircle(this.xLocation, this.yLocation, CIRCLE_RADIUS, Color.RED,  Style.STROKE);
				drawHoldArc(g);
				float HOLD_OK_TIMING = holdDuration*.6f;
				if (holdUserDuration >= HOLD_OK_TIMING) {
					drawLabel(g,"Release!");									
				} else {
					drawLabel(g,"Hold!");
				}
				
				break;
			}
			
			case RATING: {
				drawRating(g);							
				break;
			}
			
			case DONE: {
				break;
			}
		} //end switch
	}
	void drawHoldArc(Graphics g) {
		float sweepAngle = 360 * (holdUserDuration/holdDuration);
		if (sweepAngle >= 360) {
			sweepAngle = 360;
		}
		
        this.paint.setColor(0xFFFF6600); //Orange
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(10);
        g.drawArc(xLeft-TIME_ARC_DIST, yUp-TIME_ARC_DIST, 
        		xRight+TIME_ARC_DIST, yDown+TIME_ARC_DIST, -90, 360-sweepAngle, true, paint);		

	}
	
	void drawLabel(Graphics g, String word) {
		paint.setColor(Color.WHITE);
		this.paint.setStrokeWidth(1);
		paint.setTextSize(50);
		paint.setTextAlign(Align.CENTER);
		paint.setStyle(Style.FILL_AND_STROKE);
		g.drawString(word, this.startingX, this.startingY-LABEL_LOCATION, paint);		
	}
	
	@Override
	void update(TouchEvent e) {
		
//		Log.i(TAG, "DeltaTime - " + deltaTime 
//				+ "LifeSpan: " + this.lifeSpan);

		switch(this.state) {
			case ON: {
				
				if (lifeSpan > ON_DURATION + LENIENCY){
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} else if (e != null) {
					if (isTouched(e.x, e.y)) {
						//if (DEBUG) Log.i(TAG, "Touched: " + this.lifeSpan);
						setRating();
						if (this.rating == GameUtil.Rating.Good || this.rating == GameUtil.Rating.Perfect) {
							this.state = HOLD;
						} else {
							this.state = RATING;
						}
					} 
				} 			
				break;
				//else time runs out
			}
			
			case HOLD: {
				if (holdUserDuration > holdDuration + LENIENCY) {
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} else {
					if (e != null && e.type == TouchEvent.TOUCH_UP && isTouched(e.x, e.y)) {
						setHoldRating();
						this.state = RATING;
					}
				}
				break;
				

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
	
	void setHoldRating() {
		float HOLD_OK_TIMING = holdDuration*.6f;
		float HOLD_GOOD_TIMING = holdDuration*.8f;
		float HOLD_PERFECT_TIMING = holdDuration*.95f;
		
		if (holdUserDuration >= HOLD_PERFECT_TIMING) {
			rating = GameUtil.Rating.Perfect;
		} else if (holdUserDuration >= HOLD_GOOD_TIMING) {
			rating = GameUtil.Rating.Good;
		} else if (holdUserDuration >= HOLD_OK_TIMING) {
			rating = GameUtil.Rating.Ok;
		} else {
			rating = GameUtil.Rating.Bad;
		}		
	}
	
}