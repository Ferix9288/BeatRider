package com.example.beatrider;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;


public abstract class GameObject {
	
	abstract void draw(Graphics g);
	abstract void update(TouchEvent e);
	
}
