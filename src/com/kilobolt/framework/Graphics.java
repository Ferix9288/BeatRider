package com.kilobolt.framework;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Point;

public interface Graphics {
	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Image newImage(String fileName, ImageFormat format);

	public void clearScreen(int color);

	public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint);
	
	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color, Style style);
	
	public void drawCircle(int x, int y, int radius, int color, Style style);
	
	public void drawTriangle(Point p1, Point p2, Point p3, int color, Style style);
	
	 public void drawPath(Path path);
	
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);

	public void drawImage(Image Image, int x, int y);

	void drawString(String text, int x, int y, Paint paint);

	public Canvas getCanvas();
	
	public Paint getPaint();
	
	public int getWidth();

	public int getHeight();

	public void drawARGB(int i, int j, int k, int l);

}
