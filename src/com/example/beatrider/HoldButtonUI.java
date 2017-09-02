package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public abstract class HoldButtonUI extends GameObject {
			
	public static final int GAP = 40;
	public static final int WIDTH = 150;
	public static final int HEIGHT = 150;

	public static final int DISPLAY = 0;
	public static final int PRESSED = 1;
	public static final int ACTION_COMPLETE = 2;
	
	public static final int DURATION_COMPLETE = 1000; //Hold for one second

	static final int TIME_ARC_DIST = 20;
	static final int LABEL_LOCATION = TIME_ARC_DIST*2;
	
	public int state;
	public float userDuration;
	int holdPointer;
	boolean redraw;
	
	public int hitBoxLeft;
	public int hitBoxRight;
	public int hitBoxTop;
	public int hitBoxBottom;
	
	public Paint paint;

	
	public HoldButtonUI(Graphics g) {
		super();
		
		this.paint = g.getPaint();				
		this.state = DISPLAY;
		this.redraw = false;
		
	}
	@Override
	void draw(Graphics g, float deltaTime) {
		// TODO Auto-generated method stub
		   //Pause Button
		
		switch (state) {
		
			case DISPLAY: 
				drawTheUI(g);
				break;

			case PRESSED:
				userDuration += deltaTime;
				drawTheUI(g);
				drawTimeArc(g);
				drawTheLabel(g);
				break;

			case ACTION_COMPLETE: 
				drawTheUI(g);
				drawTimeArc(g);
				drawTheLabel(g);
				break;
			
		} //end switch
		
	}

	abstract void drawTheUI(Graphics g);
	abstract void drawTheLabel(Graphics g);

	void drawTimeArc(Graphics g) {
		float sweepAngle = 360 * (userDuration/DURATION_COMPLETE);
        this.paint.setColor(Color.YELLOW);        
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(3);
		g.drawArc(this.hitBoxLeft-TIME_ARC_DIST, this.hitBoxTop-TIME_ARC_DIST, 
				this.hitBoxRight+TIME_ARC_DIST, this.hitBoxBottom+TIME_ARC_DIST, -90, -sweepAngle, false, paint);
	}
	
	void drawLabel(Graphics g, String word) {
		this.paint.setColor(Color.WHITE);
		this.paint.setStyle(Style.FILL_AND_STROKE);
		this.paint.setStrokeWidth(1);
		this.paint.setTextSize(40);
		this.paint.setTextAlign(Align.CENTER);
		g.drawString(word, (this.hitBoxLeft+this.hitBoxRight)/2, this.hitBoxTop-LABEL_LOCATION, paint);		
	}
	
	@Override
	void update(TouchEvent e) {
		if (e != null && e.type == TouchEvent.TOUCH_DOWN && isTouched(e.x, e.y)) {
			this.holdPointer = e.pointer;
			this.state = PRESSED;
		} 
		
		if (this.state == PRESSED) {
			if (userDuration >= DURATION_COMPLETE) {
				this.state = ACTION_COMPLETE;
			} else if (e != null && e.pointer == this.holdPointer && (!isTouched(e.x, e.y) || e.type == TouchEvent.TOUCH_UP)) {
				this.userDuration = 0;
				this.redraw = true;
				this.state = DISPLAY;
			} 		
				
		}
	}
	
	boolean isTouched(int x, int y) {
		if (x >= hitBoxLeft && x <= hitBoxRight) {
			if (y >= hitBoxTop && y <= hitBoxBottom) {
				return true;
			}
		}
		return false;
	}
	
	boolean actionTriggered() {
		return state == ACTION_COMPLETE;
	}
	
	public void reset() {
		state = DISPLAY;
		this.userDuration = 0;
		this.redraw = false;
	}
	
	public boolean redraw() {
		return this.redraw;
	}
		
}