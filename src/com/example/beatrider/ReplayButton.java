package com.example.beatrider;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;

public class ReplayButton extends HoldButtonUI {
	
	static final int ARROW_HEIGHT = 10;
	static final int ARROW_WIDTH = 10;

	//3 Points for Arrow Tip
	Point arrowTopRight, arrowBottomRight, arrowLeft;
	
	public ReplayButton(Graphics g, int x, int y) {
		super(g);
				
		this.hitBoxLeft = x - (GAP+WIDTH) ;
		this.hitBoxRight = x -GAP;
		this.hitBoxTop = y-HEIGHT/2;
		this.hitBoxBottom = y+HEIGHT/2;
		
		//Play Button
		arrowTopRight = new Point((this.hitBoxLeft+this.hitBoxRight)/2,this.hitBoxTop-ARROW_HEIGHT);
		arrowBottomRight = new Point((this.hitBoxLeft+this.hitBoxRight)/2, this.hitBoxTop+ARROW_HEIGHT);
		arrowLeft = new Point( (this.hitBoxLeft+this.hitBoxRight)/2 - ARROW_WIDTH, this.hitBoxTop);
		
		this.state = DISPLAY;
		
	}

	
	void drawTheArc(Graphics g) {
		paint.setStrokeWidth(2);
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		g.drawArc(this.hitBoxLeft, this.hitBoxTop, this.hitBoxRight, this.hitBoxBottom, -120, -330, false, paint);
	}

	void drawTheArrow(Graphics g) {
		paint.setStrokeWidth(2);
		g.drawTriangle(arrowTopRight, arrowBottomRight, arrowLeft, Color.WHITE, Style.FILL_AND_STROKE);
	}


	@Override
	void drawTheUI(Graphics g) {
		drawTheArc(g);
		drawTheArrow(g);		
	}


	@Override
	void drawTheLabel(Graphics g) {
		// TODO Auto-generated method stub
		drawLabel(g, "Replay!");
	}

	
	
}