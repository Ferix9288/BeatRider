package com.example.beatrider;


import android.graphics.Paint.Style;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class BeatCircle extends GameObject {
	
	//Instance variables
	int xLocation, yLocation, radius;
	int xLeft, xRight, yDown, yUp; //Hitbox
	boolean visible;
	
	
	public BeatCircle() {
		this.visible = true;		
	}
	
	void setLocation(int x, int y, int radius) {
		this.xLocation = x;
		this.yLocation = y;
		this.radius = radius;

		this.xLeft = x - radius;
		this.xRight = x + radius;
		this.yDown = y - radius;
		this.yUp = y + radius;
	}
	
	void draw(Graphics g) {
		int color = 0xFFFF0000; //Alpha = 0xFF; Red = 0xFF
		if (visible) {
			g.drawCircle(xLocation, yLocation, radius, color,  Style.STROKE);
		}
	}
	
	void update(TouchEvent e) {
		if (isTouched(e.x, e.y)) {
			this.visible = false;
		} 
		
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
}