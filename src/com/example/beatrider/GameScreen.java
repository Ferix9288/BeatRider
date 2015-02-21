package com.example.beatrider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.util.Range;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Pool;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Pool.PoolObjectFactory;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

	private static final boolean DEBUG = true;

	private static final String TAG = "Game Screen";

    GameState state = GameState.Ready;
    
    GameReport report;

    /** 
     * Beat Circle Factory.
     */
    static Pool<BeatCircle> beatCirclePool;
	List<BeatCircle> inGameBeatCircles = new ArrayList<BeatCircle>();
	static PoolObjectFactory<BeatCircle> beatCircleFactory;

    /** 
     * Drag Circle Factory.
     */
    static Pool<DragCircle> dragCirclePool;
	List<DragCircle> inGameDragCircles = new ArrayList<DragCircle>();
	static PoolObjectFactory<DragCircle> dragCircleFactory;
	
	static {
		beatCircleFactory = new PoolObjectFactory<BeatCircle>() {
            @Override
            public BeatCircle createObject() {
                return new BeatCircle();
            }            
        };
        
        beatCirclePool = new Pool<BeatCircle>(beatCircleFactory, 50);
        
        dragCircleFactory = new PoolObjectFactory<DragCircle>() {
            @Override
            public DragCircle createObject() {
                return new DragCircle();
            }            
        };
        
        dragCirclePool = new Pool<DragCircle>(dragCircleFactory, 50);
	}
	
    // Variable Setup
    // You would create game objects here.

    int livesLeft = 1;
    Paint paint;

    public GameScreen(Game game) {
        super(game);

        // Initialize game objects here

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        report = new GameReport();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
       
        if (state == GameState.Running) {
        	generateBeats();
            updateRunning(touchEvents, deltaTime);
        }
       
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        
        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins. 
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!
        
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void generateBeats() {
    	int currentBeats = inGameBeatCircles.size();
    	//if (DEBUG) Log.i(TAG, "generateBeats:" + currentBeats);

    	if (currentBeats < 2) {
			BeatCircle newBeatCircle = beatCirclePool.newObject();
			newBeatCircle.setLocation(640, 300, 100);
			inGameBeatCircles.add(newBeatCircle);
			
			BeatCircle newBeatCircle2 = beatCirclePool.newObject();
			newBeatCircle2.setLocation(640, 600, 100);
			inGameBeatCircles.add(newBeatCircle2);
			
			DragCircle dragCircle = dragCirclePool.newObject();
			dragCircle.setLocation(1000, 300, 100);
			dragCircle.setDrag(100);
			inGameBeatCircles.add(dragCircle);

    	}
    }
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        
        //This is identical to the update() method from our Unit 2/3 game.
        
        
    	// 1. All touch input is handled here:
        
        int len = touchEvents.size();
        if (len == 0) {
            if (DEBUG) Log.i(TAG, "updateRunning: Null Touch Events");

            for (int j = 0; j < inGameBeatCircles.size(); j++) {
            	BeatCircle beatCircle = inGameBeatCircles.get(j);
            	beatCircle.update(null, deltaTime);
            	if (beatCircle.isDone()) {
            		inGameBeatCircles.remove(j);
            		report.missCounter ++;
            	}
            }  	
        } else {
            if (DEBUG) Log.i(TAG, "updateRunning: touchEvents" + touchEvents);

	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            //Update Beat Circles
	            for (int j = 0; j < inGameBeatCircles.size(); j++) {
	            	BeatCircle beatCircle = inGameBeatCircles.get(j);
	            	beatCircle.update(event, deltaTime);
	            	if (beatCircle.isDone()) {
	            		inGameBeatCircles.remove(j);
	            		switch(beatCircle.rating) {
							case Bad:
								report.badCounter++;
								break;
							case Ok:
								report.okCounter++;
								break;
							case Good:
								report.goodCounter++;
								break;
							case Perfect:
								report.perfectCounter++;
								break;
							default:
								break;
	            		}
	            	}
	            }
	            
	            
	           
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	
	                if (event.x < 640) {
	                    // Move left.
	                }
	
	                else if (event.x > 640) {
	                    // Move right.
	                }
	
	            }
	
	            if (event.type == TouchEvent.TOUCH_UP) {
	
	                if (event.x < 640) {
	                    // Stop moving left.
	                }
	
	                else if (event.x > 640) {
	                    // Stop moving right. }
	                }
	            }

            
	        }
        }
        
        // 2. Check miscellaneous events like death:
        
        if (livesLeft == 0) {
            state = GameState.GameOver;
        }
        
        
        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
 
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
    				Activity gameActivity = (Activity) game;

                    Intent launchMenu = new Intent(gameActivity, MenuActivity.class);
                    gameActivity.startActivity(launchMenu);
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);
        // First draw the game elements.

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        
        if (state == GameState.Running) {
            drawRunningUI();
        	drawRunning();
        }
        
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }
        
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        
        g.drawString("Tap to begin the game.",
                640, 300, paint);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawString("Pause Button here.",
                game.getGraphics().getWidth() - 50, game.getGraphics().getHeight() - 10, paint);        
    }
    
    private void drawRunning() {
    	int size = inGameBeatCircles.size();
    	for (int i = 0; i < size; i++ ) {
    		BeatCircle currentBeatCircle = inGameBeatCircles.get(i);
    		currentBeatCircle.draw(game.getGraphics());
    	}
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK, Style.FILL);
        g.drawString("GAME OVER.", 640, 300, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }
}