package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class PauseButton extends GameObject {

	public static final int PAUSE = 0;
	public static final int PLAY = 1; 
	
	public static final int HEIGHT = 60;
	public static final int WIDTH = 20;
	public static final int GAP = 10;
	
	public int state = PAUSE; 
	
	public int hitBoxLeft;
	public int hitBoxRight;
	public int hitBoxTop;
	public int hitBoxBottom;
	
	public Paint paint;
	
	//3 Points for Play Button
	Point playBottomLeft, playTopLeft, playRight;
	
	public PauseButton(Graphics g) {
		super();
		
		this.paint = g.getPaint();
		
		this.hitBoxLeft = g.getWidth()-(WIDTH+GAP)*3;
		this.hitBoxRight = g.getWidth()-(GAP)*2;
		this.hitBoxTop = g.getHeight()-HEIGHT;
		this.hitBoxBottom = g.getHeight();
		
		//Play Button
		playBottomLeft = new Point(this.hitBoxLeft,this.hitBoxBottom);
		playTopLeft = new Point(this.hitBoxLeft, this.hitBoxTop);
		playRight = new Point(this.hitBoxRight, this.hitBoxBottom-HEIGHT/2);
		
	}
	@Override
	void draw(Graphics g, float deltaTime) {
		// TODO Auto-generated method stub
		   //Pause Button
		
		if (state == PAUSE) {
			
			paint.setStrokeWidth(1);
			
	        g.drawRect(g.getWidth()- (WIDTH+GAP)*3 , g.getHeight() - HEIGHT, 
	        		WIDTH, HEIGHT, Color.WHITE, Style.FILL_AND_STROKE);
	        
	        g.drawRect(g.getWidth()-(WIDTH+GAP)*2, g.getHeight() - HEIGHT, 
	        		WIDTH, HEIGHT, Color.WHITE, Style.FILL_AND_STROKE);
		} else if (state == PLAY) {
			paint.setStrokeWidth(1);
			g.drawTriangle(playBottomLeft, playTopLeft, playRight, Color.WHITE, Style.FILL_AND_STROKE);
			
		}
	}

	@Override
	void update(TouchEvent e) {
		if (e != null && e.type == TouchEvent.TOUCH_DOWN && isTouched(e.x, e.y)) {
			if (state == PAUSE) {
				state = PLAY;
			} else {
				state = PAUSE;
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
	
	public boolean pausePressed() {
		if (state == PLAY) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean playPressed() {
		if (state == PAUSE) {
			return true;
		} else {
			return false;
		}
	}
}