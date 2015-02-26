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
	ArrayList<Point> dragPoints = new ArrayList<Point>();
	float[] dragTimes;
	int dragPointer;
	int resilience;
	
	ArrayList<Point> dragUserPath = new ArrayList<Point>();
	ArrayList<Point> dragTemplatePath = new ArrayList<Point>();
	int dragIndex;

	
	public DragCircle() {
		super();
		this.type = BeatType.Drag;
	}
	
	public DragCircle(int x, int y, ArrayList<Point> dragPoints, float[] dragTimes) {
		setInitialization(x, y, dragPoints, dragTimes);
	}
	
	void setInitialization(int x, int y, ArrayList<Point> dragPoints, float[] dragTimes) {
		super.setInitialization(x, y);
		this.dragUserDuration = 0;
		this.dragPoints = dragPoints;
		this.dragTimes = dragTimes;
		this.resilience = 0;
		
		dragUserPath.clear();
		dragTemplatePath.clear();
		dragIndex = 0;
		
		//createDragPath();				
		this.type = BeatType.Drag;
	}
	
	void createDragPath() {
		Random r = new Random();
		int previousX = startingX; 
		int previousY = startingY;
		for (int i = DRAG_DIVIDER; i < (int) this.dragTimes[dragIndex]; i+= DRAG_DIVIDER) {
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
				float DRAG_OK_TIMING = dragTimes[dragTimes.length-1]*.6f; //Last Drag Time
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
		
		for (int i = 0; i < dragPoints.size(); i++) {
			if (i <= dragIndex) {
				Point p = dragPoints.get(i);
				paint.setStrokeWidth(1);
				g.drawCircle(p.x, p.y, CIRCLE_RADIUS, 0xFFFFFF00,  Style.STROKE);
				paint.setStrokeWidth(3);
				g.drawLine(this.startingX, this.startingY, p.x, p.y, Color.WHITE);
			}
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
							createDragPath2();
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
				if (dragUserDuration > dragTimes[dragTimes.length-1] + LENIENCY) {
					if (DEBUG) Log.i(TAG, "In State Drag: Expired Drag.");
					rating = GameUtil.Rating.Miss;
					this.state = RATING;
				} 	else {
					updateLocation();
					if (e != null) {
						if (e.pointer == this.dragPointer) {
							if (e.type == TouchEvent.TOUCH_DRAGGED && isTouched(e.x, e.y)) {
								if (DEBUG) Log.i(TAG, "In State Drag: Touched.");
								if (dragUserDuration > dragTimes[dragIndex] && dragIndex < (dragPoints.size()-1)) {
									Log.e(TAG, "GOT HERE. " + (dragPoints.size()-1));
									dragIndex++;
									createDragPath2();
									
								}
								this.resilience = 0;
							} else { //User Lifted Pointer Up/Missed Circle
								if (dragIndex == dragPoints.size()-1) {
									setDragRating();
									this.state = RATING;
								} else {
									rating = GameUtil.Rating.Miss;
									this.state = RATING;									
								}
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
		
		float DRAG_OK_TIMING = dragTimes[dragPoints.size()-1]*.6f;
		float DRAG_GOOD_TIMING = dragTimes[dragPoints.size()-1]*.8f;
		float DRAG_PERFECT_TIMING = dragTimes[dragPoints.size()-1]*.95f;
		
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
	
	
	void createDragPath2() {

		Point destinationPoint = dragPoints.get(dragIndex);
		Point previousPoint;
		if (dragIndex >= 1) {
			previousPoint = dragPoints.get(dragIndex-1);
		} else {
			previousPoint = new Point(this.startingX, this.startingY);
		}

		//Total of DRAG_DIVIDER frames
		int x = previousPoint.x;
		int y = previousPoint.y;

		int xIncrement = (int) ( (destinationPoint.x - previousPoint.x) / DRAG_DIVIDER);
		int yIncrement = (int) ( (destinationPoint.y - previousPoint.y) / DRAG_DIVIDER);
		
		dragUserPath.clear();
		for (int i = 0 ; i < DRAG_DIVIDER; i++) {
			x += xIncrement;
			y += yIncrement;
			dragUserPath.add(new Point(x,y));
		}
		
		dragUserPath.add(new Point(destinationPoint.x, destinationPoint.y));

			
	}
	void updateLocation() {
		
		int currentIndex;
		if (dragIndex == 0) {
			currentIndex = (int) (dragUserDuration / dragTimes[dragIndex] * (dragUserPath.size()-1) );
		} else {
			currentIndex = (int) ( (dragUserDuration-dragTimes[dragIndex-1]) / dragTimes[dragIndex] * (dragUserPath.size()-1) );
		}
		
		if (currentIndex >= dragUserPath.size()) {
			currentIndex = dragUserPath.size()-1;
		}
		
		Point currentLocation = dragUserPath.get(currentIndex);
		this.xLocation = currentLocation.x;
		this.yLocation = currentLocation.y;
		
		this.xLeft = this.xLocation - CIRCLE_RADIUS;
		this.xRight = this.xLocation + CIRCLE_RADIUS;
		this.yDown = this.yLocation + CIRCLE_RADIUS;
		this.yUp = this.yLocation - CIRCLE_RADIUS;
		
	}

}