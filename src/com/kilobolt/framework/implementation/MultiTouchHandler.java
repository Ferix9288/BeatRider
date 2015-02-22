package com.kilobolt.framework.implementation;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kilobolt.framework.Pool;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Pool.PoolObjectFactory;

public class MultiTouchHandler implements TouchHandler {
	private static final int MAX_TOUCHPOINTS = 10;

	private static final boolean DEBUG = true;

	private static final String TAG = "Multi Touch";
	
	boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
	int[] touchX = new int[MAX_TOUCHPOINTS];
	int[] touchY = new int[MAX_TOUCHPOINTS];
	int[] id = new int[MAX_TOUCHPOINTS];
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	float scaleX;
	float scaleY;

	public MultiTouchHandler(View view, float scaleX, float scaleY) {
		PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
			@Override
			public TouchEvent createObject() {
				return new TouchEvent();
			}
		};
		touchEventPool = new Pool<TouchEvent>(factory, 100);
		view.setOnTouchListener(this);

		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		
		//Get Action Index 
		int actionIndex = event.getActionIndex();
		//get Pointer ID that caused the action 
		int pointerId = event.getPointerId(actionIndex);
		//Figure out what that action was
		int maskedAction = event.getActionMasked();
	
		String actionString = "";
		
		int pointerCount = event.getPointerCount();
		
		int x = (int) event.getX(actionIndex);
		int y = (int) event.getY(actionIndex);
		TouchEvent touchEvent = touchEventPool.newObject();
		touchEvent.pointer = pointerId;
		id[pointerId] = pointerId;

		
		if (pointerCount > 1) { //Multiple Touch Event  
	
			switch (maskedAction) {
				case MotionEvent.ACTION_POINTER_DOWN: 
					actionString = "POINTER DOWN";
					touchEvent.type = TouchEvent.TOUCH_DOWN;;
					isTouched[pointerId] = true;
					break;
				
				case MotionEvent.ACTION_POINTER_UP:
					actionString = "POINTER UP";
					touchEvent.type = TouchEvent.TOUCH_UP;
					isTouched[pointerId] = false;
					break;
					
				case MotionEvent.ACTION_MOVE:
					actionString = "MOVE";
					touchEvent.type = TouchEvent.TOUCH_DRAGGED;
					isTouched[pointerId] = true;
					break;
				
				default:
					actionString = "Default";
					touchEvent.type = TouchEvent.TOUCH_UNKNOWN;
					isTouched[pointerId] = false;
					break;
			}
			
			String touchStatus = "Multi Touch Action: " + actionString + " actionIndex: " + actionIndex + " pointerID: " + pointerId + " X: " + x + " Y: " + y;
    		if (DEBUG) Log.i(TAG, touchStatus);
    		
		}  else {
			
			switch (maskedAction) {
				case MotionEvent.ACTION_DOWN: 
					actionString = "DOWN";
					touchEvent.type = TouchEvent.TOUCH_DOWN;;
					isTouched[pointerId] = true;
					break;
				
				case MotionEvent.ACTION_UP:
					actionString = "UP";
					touchEvent.type = TouchEvent.TOUCH_UP;
					isTouched[pointerId] = false;
					break;
					
				case MotionEvent.ACTION_MOVE:
					actionString = "MOVE";
					touchEvent.type = TouchEvent.TOUCH_DRAGGED;
					isTouched[pointerId] = true;
					break;
				
				default:
					actionString = "Default";
					touchEvent.type = TouchEvent.TOUCH_UNKNOWN;
					isTouched[pointerId] = false;
					break;
			
			} //end switch

			String touchStatus = "Single Touch Action: " + actionString + " actionIndex: " + actionIndex + " pointerID: " + pointerId + " X: " + x + " Y: " + y;
    		if (DEBUG) Log.i(TAG, touchStatus);	
		}

		touchEvent.x = (int) (x*scaleX);
		touchEvent.y = (int) (y*scaleY);
		touchEventsBuffer.add(touchEvent);

		return true;

	}

	@Override
	public boolean isTouchDown(int pointer) {
		synchronized (this) {
			int index = getIndex(pointer);
			if (index < 0 || index >= MAX_TOUCHPOINTS)
				return false;
			else
				return isTouched[index];
		}
	}

	@Override
	public int getTouchX(int pointer) {
		synchronized (this) {
			int index = getIndex(pointer);
			if (index < 0 || index >= MAX_TOUCHPOINTS)
				return 0;
			else
				return touchX[index];
		}
	}

	@Override
	public int getTouchY(int pointer) {
		synchronized (this) {
			int index = getIndex(pointer);
			if (index < 0 || index >= MAX_TOUCHPOINTS)
				return 0;
			else
				return touchY[index];
		}
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		synchronized (this) {
			int len = touchEvents.size();
			for (int i = 0; i < len; i++)
				touchEventPool.free(touchEvents.get(i));
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;
		}
	}
	
	// returns the index for a given pointerId or -1 if no index.
	private int getIndex(int pointerId) {
		for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
			if (id[i] == pointerId) {
				return i;
			}
		}
		return -1;
	}
}
