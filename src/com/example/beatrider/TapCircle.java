package com.example.beatrider;


import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

import com.example.beatrider.Beat.BeatType;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class TapCircle extends BeatCircle {

	static final int TAP = 4;
	
	static final String TAG = "Tap Circle";
	static final boolean DEBUG = false;
	
	
	//Additional Instance Variables
	float[] tapInterval;
	float tapUserDuration;
	int tapCount;
	int tapIndex;
	
	public TapCircle() {
		super();
		this.type = BeatType.MultipleTap;
	}
	
	public TapCircle(int x, int y, float[] tapInterval, int tapCount) {
		setInitialization(x, y, tapInterval, tapCount);		
	}
	
	void setInitialization(int x, int y, float[] tapInterval, int tapCount) {
		super.setInitialization(x, y);
		this.tapInterval = tapInterval;
		this.tapCount = tapCount;
		this.tapUserDuration = 0;	
		this.tapIndex = 0;

		this.type = BeatType.MultipleTap;

	}
	
	@Override
	void draw(Graphics g, float deltaTime) {
		
		switch(this.state) {
			case ON: {
				lifeSpan += deltaTime;
				drawBeatCircle(g);
				drawTapArc(g);
				drawTapCount(g);
				break;
				//else time runs out
			}
			
			case TAP: {
				tapUserDuration += deltaTime;
				lifeSpan += deltaTime;
				drawBeatCircle(g);
				drawTapArc(g);
				drawTapCount(g);
				break;
			}
			
			case RATING: {
				drawRating(g);
				this.wordLifeSpan++;
				break;
			}
			
			case DONE: {
				break;
			}
		} //end switch
	}
	
	void drawTapCount(Graphics g) {
		paint.setColor(Color.WHITE);
		this.paint.setStrokeWidth(1);
		paint.setTextSize(50);
		paint.setTextAlign(Align.CENTER);
		paint.setStyle(Style.STROKE);
		g.drawString(Integer.toString(tapCount-tapIndex), this.startingX, this.startingY-LABEL_LOCATION, paint);
	}

	void drawTapArc(Graphics g) {

		this.paint.setColor(Color.YELLOW);        
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(10);

		for (int i = tapIndex; i < tapCount; i++) {
			
			float sweepAngle = 360 * (lifeSpan/ (ON_DURATION + tapInterval[i]) );
	        g.drawArc(xLeft-TIME_ARC_DIST, yUp-TIME_ARC_DIST, 
	        		xRight+TIME_ARC_DIST, yDown+TIME_ARC_DIST, -90, sweepAngle, true, paint);		
		}
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
					if (e.type == TouchEvent.TOUCH_DOWN && isTouched(e.x, e.y)) {
						//if (DEBUG) Log.i(TAG, "Touched: " + this.lifeSpan);
						setRating();
						if (this.rating == GameUtil.Rating.Good || this.rating == GameUtil.Rating.Perfect) {
							if (tapIndex != tapCount -1) tapIndex++;
							this.state = TAP;
						} else {
							this.state = RATING;
						}
					} 
				} 			
				break;
				//else time runs out
			}
			
			case TAP: {
				if (DEBUG) Log.i(TAG, "In Tap State: Duration - " + tapUserDuration
						+ "|| tapInterval - " + tapInterval);
				if (tapUserDuration > tapInterval[tapIndex] + LENIENCY) {
					//User missed a tap
					if (DEBUG) Log.i(TAG, "Timer Ran out.");
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} else {
					if (e != null && e.type == TouchEvent.TOUCH_DOWN && isTouched(e.x, e.y)) {
						if (tapIndex == tapCount-1) {
							setTapRating();
							this.state = RATING;
						} else {
							setTapRating();
							if (this.rating == GameUtil.Rating.Bad) {
								this.state = RATING;
							}
							if (tapIndex != tapCount -1) tapIndex++;
							tapUserDuration = 0;
						}
					}
				}
				break;
				

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
	
	void setTapRating() {
		float TAP_OK_TIMING = (tapInterval[tapIndex]-tapInterval[tapIndex-1])*.6f;
		float TAP_GOOD_TIMING = (tapInterval[tapIndex]-tapInterval[tapIndex-1])*.8f;
		float TAP_PERFECT_TIMING = (tapInterval[tapIndex]-tapInterval[tapIndex-1])*.95f;
		
		if (tapUserDuration >= TAP_OK_TIMING) {
			rating = GameUtil.Rating.Perfect;
		} else if (tapUserDuration >= TAP_GOOD_TIMING) {
			rating = GameUtil.Rating.Good;
		} else if (tapUserDuration >= TAP_PERFECT_TIMING) {
			rating = GameUtil.Rating.Ok;
		} else {
			rating = GameUtil.Rating.Bad;
		}		
	}
	
}