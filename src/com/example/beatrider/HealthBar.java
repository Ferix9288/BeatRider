package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class HealthBar extends GameObject {

	static final int HEIGHT = 20;
	
	static final int MISS_HEALTH = -20;
	static final int BAD_HEALTH = -5;
	static final int OK_HEALTH = 0;
	static final int GOOD_HEALTH = 10;
	static final int PERFECT_HEALTH = 20;
	
	static final float MAX_HEALTH = 500;
	static final float MULTIPLER_DEFAULT = 1f;
	static final float MULTIPLIER_INCREMENT = .3f;

	private static final String TAG = "HealthBar";

	int MAX_WIDTH = 0;
	
	int healthBarWidth;
	int healthBarColor;
	float missMultipler;
	float userHealth; 
	
	Paint paint;
	
	public HealthBar (Graphics g) {
		
		this.MAX_WIDTH = g.getWidth()/2;
		this.healthBarWidth = MAX_WIDTH;
		this.healthBarColor = Color.GREEN;
	
		this.userHealth = MAX_HEALTH;
		this.paint = g.getPaint();
		this.missMultipler = MULTIPLER_DEFAULT;

	}
	
	void reset() {
		this.healthBarWidth = MAX_WIDTH;		
		this.userHealth = MAX_HEALTH;
		this.healthBarColor = Color.GREEN;
		this.missMultipler = MULTIPLER_DEFAULT;

	}
	
	@Override
	void draw(Graphics g, float deltaTime) {
		// TODO Auto-generated method stub
		
		paint.setStrokeWidth(1);
        g.drawRect(g.getWidth()/4, g.getHeight() - HEIGHT, 
        		this.healthBarWidth, HEIGHT, this.healthBarColor, Style.FILL_AND_STROKE);

	}

	@Override
	void update(TouchEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void update(GameUtil.Rating rating) {
		
		switch(rating) {
			case Miss:
				this.userHealth += MISS_HEALTH * this.missMultipler;
				this.missMultipler += MULTIPLIER_INCREMENT;
				break;
			case Bad:
				this.userHealth += BAD_HEALTH;
				this.missMultipler = MULTIPLER_DEFAULT;
				break;
			case Ok:
				this.userHealth += OK_HEALTH;
				this.missMultipler = MULTIPLER_DEFAULT;
				break;
			case Good:
				this.userHealth += GOOD_HEALTH;
				this.missMultipler = MULTIPLER_DEFAULT;
				break;
			case Perfect:
				this.userHealth += PERFECT_HEALTH;
				this.missMultipler = MULTIPLER_DEFAULT;
				break;
			default:
				break;
		} //end switch
		
		if (this.userHealth > MAX_HEALTH) {
			this.userHealth = MAX_HEALTH;
		} 
		
		if (this.userHealth < 0) {
			this.userHealth = 0;
		}
		
		this.healthBarWidth = (int) ( (this.userHealth/MAX_HEALTH) * MAX_WIDTH);
		
		int green = (int) ((float) (this.userHealth/MAX_HEALTH) * 0xFF);
		int red = 0xFF - green;
		
		this.healthBarColor = 0xFF000000 + (red << 16) + (green << 8);
		Log.e(TAG, "Health :: " + this.userHealth + " | HealthWidth :: " + this.healthBarWidth);
		Log.e(TAG, "Multiplier::  " + this.missMultipler);

	} 
	
	boolean dead() {
		return this.userHealth == 0; 
	}
	
}