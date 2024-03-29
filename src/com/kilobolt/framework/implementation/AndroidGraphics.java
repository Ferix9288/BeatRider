package com.kilobolt.framework.implementation;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;

public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    @Override
    public Image newImage(String fileName, ImageFormat format) {
        Config config = null;
        if (format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;
        
        
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);
    }

    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }


    @Override
	public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
    	//canvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint);
    	RectF rect = new RectF(left, top, right, bottom);
    	
    	canvas.drawArc(rect, startAngle, sweepAngle, useCenter, paint);
    }
    
    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color, Style style) {
        paint.setColor(color);
        paint.setStyle(style);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }
    
    @Override
    public void drawCircle(int x, int y, int radius, int color, Style style) {
    	paint.setColor(color);
    	paint.setStyle(style);
    	canvas.drawCircle(x, y, radius, paint);
    }
    
    @Override
	public void drawTriangle(Point p1, Point p2, Point p3, int color, Style style) {
    	paint.setColor(color);
    	paint.setStyle(style);
    	paint.setAntiAlias(true);

    	Path path = new Path();
    	path.setFillType(Path.FillType.EVEN_ODD);
    	path.moveTo(p1.x, p1.y);
    	path.lineTo(p2.x, p2.y);
    	path.lineTo(p3.x, p3.y);
    	path.lineTo(p1.x, p1.y);
    	path.close();

    	
    	canvas.drawPath(path, paint);
    }
    
    @Override
    public void drawPath(Path path) {
    	canvas.drawPath(path, paint);
    }
    
    
    @Override
    public void drawARGB(int a, int r, int g, int b) {
       paint.setStyle(Style.FILL);
       canvas.drawARGB(a, r, g, b);
    }
    
    @Override
    public void drawString(String text, int x, int y, Paint paint){
    	canvas.drawText(text, x, y, paint);

    	
    }
    

    public void drawImage(Image Image, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        
        
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect,
                null);
    }
    
    @Override
    public void drawImage(Image Image, int x, int y) {
        canvas.drawBitmap(((AndroidImage)Image).bitmap, x, y, null);
    }
    
    public void drawScaledImage(Image Image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
    	
    	srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        
        
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
        
   
        
        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null);
        
    }
    
    @Override
    public Canvas getCanvas(){
    	return this.canvas;
    }
    
    public Paint getPaint() {
    	return this.paint;
    }
   
    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
