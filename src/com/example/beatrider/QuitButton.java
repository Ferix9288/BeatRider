package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class QuitButton extends HoldButtonUI {
	
	static final int DOOR_WIDTH = 120;
	
	public QuitButton(Graphics g) {
		super(g);
				
		this.hitBoxLeft = g.getWidth()/2 + GAP ;
		this.hitBoxRight = g.getWidth()/2 + (GAP+DOOR_WIDTH);
		this.hitBoxTop = g.getHeight()/2-HEIGHT/2;
		this.hitBoxBottom = g.getHeight()/2+HEIGHT/2;
		
		
		this.state = DISPLAY;
		
	}

	
	void drawTheDoorFrame(Graphics g) {
		paint.setStrokeWidth(2);
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		g.drawRect(this.hitBoxLeft, this.hitBoxTop, DOOR_WIDTH, HEIGHT, Color.WHITE, Style.STROKE);
	}
	
	void drawDoor(Graphics g) {
    	paint.setColor(Color.WHITE);
    	paint.setStyle(Style.FILL_AND_STROKE);
    	paint.setAntiAlias(true);

    	Path path = new Path();
    	path.setFillType(Path.FillType.EVEN_ODD);
    	path.moveTo(this.hitBoxLeft, this.hitBoxTop);
    	path.lineTo(this.hitBoxLeft, this.hitBoxBottom);
    	path.lineTo( (this.hitBoxLeft+this.hitBoxRight)/2, this.hitBoxBottom-HEIGHT/4);
    	path.lineTo((this.hitBoxLeft+this.hitBoxRight)/2, this.hitBoxTop);
    	path.close();
    	
    	g.drawPath(path);
	}


	@Override
	void drawTheUI(Graphics g) {
		drawTheDoorFrame(g);
		drawDoor(g);
	}


	@Override
	void drawTheLabel(Graphics g) {
		// TODO Auto-generated method stub
		drawLabel(g, "Quit!");
	}

	
	
}