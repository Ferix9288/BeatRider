package com.example.beatrider;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.Log;

import com.example.beatrider.Beat.BeatType;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class DragCircle extends BeatCircle {

	static final int DRAG = 4;
	static final int DRAG_DIVIDER = 50;
	static final int DRAG_INCREMENT = 20;
	
	static final int DRAG_RESILIENCE = 5;
	
	static final boolean DEBUG = true;
	
	//Additional Instance Variables
	float dragUserDuration;
	float dragTotalTime;
	int dragPointer;
	int resilience;
	
	ArrayList<Point> dragUserPath = new ArrayList<Point>();
	ArrayList<Point> dragTemplatePath = new ArrayList<Point>();
	int dragIndex;

	
	public DragCircle() {
		super();
		this.type = BeatType.Drag;
	}
	
	public DragCircle(int x, int y, float dragTotal) {
		setInitialization(x, y, dragTotal);
	}
	
	void setInitialization(int x, int y, float dragTotal) {
		super.setInitialization(x, y);
		this.dragUserDuration = 0;
		this.dragTotalTime = dragTotal;
		this.resilience = 0;
		
		dragUserPath.clear();
		dragTemplatePath.clear();
		dragIndex = 0;
		
		createDragPath();				
		this.type = BeatType.Drag;
	}
	
	void createDragPath() {
		Random r = new Random();
		int previousX = startingX; 
		int previousY = startingY;
		for (int i = DRAG_DIVIDER; i < (int) this.dragTotalTime; i+= DRAG_DIVIDER) {
			float sign = r.nextFloat();
			if (sign <= 0.5) {
				sign = -1;
			} else {
				sign = 1;
			}
			
			int randomYIncrement = (int) ( (r.nextInt(5) + 3) * sign);
			previousX = previousX + DRAG_INCREMENT;
			previousY = previousY + randomYIncrement;
			Point newPoint = new Point(previousX, previousY);
			dragTemplatePath.add(newPoint);
		}

	}
	
	@Override
	void draw(Graphics g, float deltaTime) {
		
		switch(this.state) {
			case ON: {
				lifeSpan += deltaTime;
				g.drawCircle(this.xLocation, this.yLocation, CIRCLE_RADIUS, Color.RED,  Style.STROKE);
				drawTimeArc(g);
				drawDragTemplatePath(g);
				if (lifeSpan >= OK_TIMING) {
					drawLabel(g, "Drag!");
				}
				break;
				//else time runs out
			}
			
			case DRAG: {
				dragUserDuration += deltaTime;
				drawDragCircle(g);
				drawDragTemplatePath(g);
				drawUserDragPath(g);
				float DRAG_OK_TIMING = dragTotalTime*.6f;
				if (dragUserDuration >= DRAG_OK_TIMING) {
					drawLabel(g, "Release!");
				}
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
	
	void drawDragCircle(Graphics g) {
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		paint.setStyle(Style.FILL_AND_STROKE);
		 g.drawArc(this.xLocation - CIRCLE_RADIUS, this.yLocation - CIRCLE_RADIUS, 
				 this.xLocation + CIRCLE_RADIUS, this.yLocation + CIRCLE_RADIUS, -90, 360, true, paint);		

	}
	
	void drawDragTemplatePath(Graphics g) {
		paint.setStrokeWidth(1);
		for (int i = dragIndex; i < dragTemplatePath.size()-1; i++) {
			Point p = dragTemplatePath.get(i);
			g.drawCircle(p.x, p.y, CIRCLE_RADIUS, 0x30FFFF00,  Style.STROKE);
		}
		//Draw Very Last One Differently
		Point lastPoint = dragTemplatePath.get(dragTemplatePath.size()-1);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
		paint.setColor(Color.YELLOW);
        g.drawArc(lastPoint.x - CIRCLE_RADIUS, lastPoint.y - CIRCLE_RADIUS, 
        		lastPoint.x + CIRCLE_RADIUS, lastPoint.y + CIRCLE_RADIUS, -90, 360, true, paint);		

	}
	
	void drawUserDragPath(Graphics g) {
		paint.setStrokeWidth(1);
		for (int i = 0; i < dragIndex; i++) {
			Point p = dragTemplatePath.get(i);
			g.drawCircle(p.x, p.y, CIRCLE_RADIUS, 0x50FFFF00,  Style.STROKE);
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
				} else if (e != null && e.type == TouchEvent.TOUCH_DOWN) {
					if (isTouched(e.x, e.y)) {
						//if (DEBUG) Log.i(TAG, "Touched: " + this.lifeSpan);
						setRating();
						if (this.rating == GameUtil.Rating.Good || this.rating == GameUtil.Rating.Perfect) {
							this.dragPointer = e.pointer;
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
				if (dragUserDuration > dragTotalTime + LENIENCY) {
					if (DEBUG) Log.i(TAG, "In State Drag: Expired Drag.");
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} 	else {
					updateLocation();
					if (e != null) {
						if (e.pointer == this.dragPointer) {
							if (e.type == TouchEvent.TOUCH_DRAGGED && isTouched(e.x, e.y)) {
								if (DEBUG) Log.i(TAG, "In State Drag: Touched.");
								this.resilience = 0;
							} else { //User Lifted Pointer Up/Missed Circle
								setDragRating();
								this.state = RATING;
							}
						} //end if pointer							
					} else {
						if (DEBUG) Log.i(TAG, "In State Drag: Touch Event Null.");
						if (this.resilience > DRAG_RESILIENCE) {
							if (DEBUG) Log.i(TAG, "In State Drag: Resilence > Drag Resilience.");
							setDragRating();
							this.state = RATING;
						} 
						this.resilience++;		
					}
				}
				

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
	
	void setDragRating() {
		float DRAG_OK_TIMING = dragTotalTime*.6f;
		float DRAG_GOOD_TIMING = dragTotalTime*.8f;
		float DRAG_PERFECT_TIMING = dragTotalTime*.95f;
		
		if (dragUserDuration >= DRAG_PERFECT_TIMING) {
			rating = GameUtil.Rating.Perfect;
		} else if (dragUserDuration >= DRAG_GOOD_TIMING) {
			rating = GameUtil.Rating.Good;
		} else if (dragUserDuration >= DRAG_OK_TIMING) {
			rating = GameUtil.Rating.Ok;
		} else {
			rating = GameUtil.Rating.Bad;
		}		
	}
	
	void updateLocation() {

		//dragIndex = (int) (dragUserDuration / dragTotalTime)*dragTemplatePath.size();
		dragIndex = (int) (dragUserDuration / DRAG_DIVIDER);
		if (DEBUG) Log.i(TAG, "Update Location: dragUserDuration - " + dragUserDuration
				+ "||| dragIndex -" + dragIndex);
		if (dragIndex >= dragTemplatePath.size()) {
			dragIndex = dragTemplatePath.size() -1;
		}
		
		Point p = dragTemplatePath.get(dragIndex);
		
		this.xLocation = p.x;
		this.yLocation = p.y;
		
		this.xLeft = p.x - CIRCLE_RADIUS;
		this.xRight = p.x + CIRCLE_RADIUS;
		this.yDown = p.y + CIRCLE_RADIUS;
		this.yUp = p.y - CIRCLE_RADIUS;
		
	}

}