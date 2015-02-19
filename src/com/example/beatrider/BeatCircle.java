package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint.Style;

import com.kilobolt.framework.Graphics;

public class BeatCircle extends GameObject {
	
	int xLocation, yLocation, radius;
	
	void draw(Graphics g) {
		int color = 0xFFFF0000; //Alpha = 0xFF; Red = 0xFF
		g.drawCircle(xLocation, yLocation, radius, color,  Style.STROKE);
	}
}