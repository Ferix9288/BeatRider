package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint.Style;
import android.util.Log;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class DragCircle extends BeatCircle {

	static final int DRAG = 3;
	static final int DRAG_INCREMENT = 2;
	
	static final int DRAG_RESILIENCE = 5;
	
	//Additional Instance Variables
	float dragDuration;
	float dragTotalTime;
	int resilience;
	
	public DragCircle() {
		
	}
	
	void setDrag(float dragTotal) {
		this.dragDuration = 0;
		this.dragTotalTime = dragTotal;
		this.resilience = 0;
		
	}
	
	@Override
	void draw(Graphics g) {
		
		switch(this.state) {
			case ON: {
				g.drawCircle(this.xLocation, this.yLocation, this.radius, Color.RED,  Style.STROKE);
				drawTimeArc(g);
				drawDragPath(g);
				break;
				//else time runs out
			}
			
			case DRAG: {
				g.drawCircle(this.xLocation, this.yLocation, this.radius, Color.RED,  Style.STROKE);
				drawDragPath(g);
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
	
	void drawDragPath(Graphics g) {
		for (int i = DRAG_INCREMENT; i < (int) this.dragTotalTime; i+= DRAG_INCREMENT) {
			g.drawCircle(this.startingX+i, this.startingY+i, this.radius, 0x3000FFFF,  Style.STROKE);
		}
	}
	
	@Override
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
						setRating();
						if (this.rating == GameUtil.Rating.Good || this.rating == GameUtil.Rating.Perfect) {
							this.state = DRAG;
						} else {
							this.state = RATING;
						}
					} 
				} 			
				break;
				//else time runs out
			}
			
			case DRAG: {
				if (e != null) {
					if (DEBUG) Log.i(TAG, "In State Drag: " + e.toString());
					if (isTouched(e.x, e.y)) {
						this.resilience = 0;
						if (DEBUG) Log.i(TAG, "Touched: " + this.lifeSpan);
						updateLocation(e.x, e.y);
					} 
				} else {
					if (DEBUG) Log.i(TAG, "In State Drag: Null Event.");
					if (this.resilience > DRAG_RESILIENCE) {
						this.state = RATING;
					}  else {
						this.resilience++;
					}
				}
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
	
	void updateLocation(int x, int y) {
		this.xLocation = x;
		this.yLocation = y;
		
		this.xLeft = x - radius;
		this.xRight = x + radius;
		this.yDown = y + radius;
		this.yUp = y - radius;
	}

}