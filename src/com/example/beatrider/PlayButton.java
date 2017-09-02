package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class PlayButton extends HoldButtonUI {
	
	static final int ARROW_HEIGHT = 80;
	static final int ARROW_WIDTH = 110;
	
	//3 Points for Arrow Tip
	Point arrowTopLeft, arrowBottomLeft, arrowRight;

	public PlayButton(Graphics g, int x, int y) {
		super(g);
				
		this.hitBoxLeft = x-ARROW_WIDTH/2;
		this.hitBoxRight = x+ARROW_WIDTH/2;
		this.hitBoxTop = y-ARROW_HEIGHT/2;
		this.hitBoxBottom = y+ARROW_HEIGHT/2;
		
		//Play Button
		arrowTopLeft = new Point((this.hitBoxLeft+this.hitBoxRight)/2,this.hitBoxTop-ARROW_HEIGHT);
		arrowBottomLeft = new Point((this.hitBoxLeft+this.hitBoxRight)/2, this.hitBoxTop+ARROW_HEIGHT);
		arrowRight = new Point( (this.hitBoxLeft+this.hitBoxRight)/2 + ARROW_WIDTH, this.hitBoxTop);
		
		this.state = DISPLAY;
		
	}
	
	void updatePosition(int x, int y ) {
		this.hitBoxLeft = x-ARROW_WIDTH/2;
		this.hitBoxRight = x+ARROW_WIDTH/2;
		this.hitBoxTop = y-ARROW_HEIGHT/2;
		this.hitBoxBottom = y+ARROW_HEIGHT/2;
		
		this.arrowTopLeft.x = this.hitBoxLeft;
		this.arrowTopLeft.y = this.hitBoxTop;
		
		this.arrowBottomLeft.x = this.hitBoxLeft;
		this.arrowBottomLeft.y = this.hitBoxBottom;
		
		this.arrowRight.x = this.hitBoxRight;
		this.arrowRight.y = (this.hitBoxTop+this.hitBoxBottom)/2;		
	}

	
	void drawTheArrow(Graphics g) {
		paint.setStrokeWidth(2);
		g.drawTriangle(arrowTopLeft, arrowBottomLeft, arrowRight, Color.GREEN, Style.FILL_AND_STROKE);
	}


	@Override
	void drawTheUI(Graphics g) {
		drawTheArrow(g);
	}

	@Override
	void drawTheLabel(Graphics g) {
		// TODO Auto-generated method stub
		drawLabel(g, "Play!");
	}

	
	
}