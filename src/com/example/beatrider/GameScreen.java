package com.example.beatrider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

import com.example.beatrider.Beat.BeatType;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Music;
import com.kilobolt.framework.Pool;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Pool.PoolObjectFactory;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

	private static final boolean DEBUG = true;
	private static final boolean SHOW_FPS = false;
	private static final int MAX_AT_PLAY = 20;

	private static final String TAG = "Game Screen";

    GameState state = GameState.Ready;
    
    GameReport report;
    
    float GameTimer;
    
    private static final int MOCK_TOP = 50;
    int CountDown;
    int currentFPS;
    
    Song selectedSong = SongCollection.BestDay;

    /** 
     * Beat Circle Factory.
     */
    static Pool<BeatCircle> beatCirclePool;
	List<BeatCircle> inQueueBeatCircles = new ArrayList<BeatCircle>();
	List<BeatCircle> inGameBeatCircles = new ArrayList<BeatCircle>();
	static PoolObjectFactory<BeatCircle> beatCircleFactory;

    /** 
     * Drag Circle Factory.
     */
    static Pool<DragCircle> dragCirclePool;
	static PoolObjectFactory<DragCircle> dragCircleFactory;

    /** 
     * Tap Circle Factory.
     */
    static Pool<TapCircle> tapCirclePool;
	static PoolObjectFactory<TapCircle> tapCircleFactory;

    /** 
     * Hold Circle Factory.
     */
    static Pool<HoldCircle> holdCirclePool;
	static PoolObjectFactory<HoldCircle> holdCircleFactory;


    static ArrayList<Beat> BeatPattern = new ArrayList<Beat>();
    static final int BPM = 100;
    static final float BPS = BPM / 60;
    int beatIndex;

	static {
		beatCircleFactory = new PoolObjectFactory<BeatCircle>() {
            @Override
            public BeatCircle createObject() {
                return new BeatCircle();
            }
            
        };
        beatCirclePool = new Pool<BeatCircle>(beatCircleFactory, 50);
        
        dragCircleFactory = new PoolObjectFactory<DragCircle>() {
            public DragCircle createObject() {
                return new DragCircle();
            }            
            
        };
        dragCirclePool = new Pool<DragCircle>(dragCircleFactory, 50);
        
        tapCircleFactory = new PoolObjectFactory<TapCircle>() {
            public TapCircle createObject() {
                return new TapCircle();
            }            
            
        };    
        tapCirclePool = new Pool<TapCircle>(tapCircleFactory, 50);

        holdCircleFactory = new PoolObjectFactory<HoldCircle>() {
            public HoldCircle createObject() {
                return new HoldCircle();
            }            
            
        };    
        holdCirclePool = new Pool<HoldCircle>(holdCircleFactory, 50);
        
        
        //Random Beat Pattern 
        BeatPattern.add(new Beat(BeatType.SingleTap, new String[]{"500", "500"}, 5000));
        BeatPattern.add(new Beat(BeatType.SingleTap, new String[]{"500", "500"}, 10000));
        BeatPattern.add(new Beat(BeatType.SingleTap, new String[]{"700", "500"}, 10000));

        //        BeatPattern.add(new Beat(Beat.BeatType.Beat, BPS*10) );

	}
	
    // Variable Setup
    // You would create game objects here.

    int livesLeft = 1;
    Paint paint;

    public GameScreen(Game game) {
        super(game);

        // Initialize game objects here
        
        //Load Songs and Beats Here 

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        report = new GameReport();
        
        GameTimer = 0;
        CountDown = selectedSong.duration;
        beatIndex = 0;
        
        state = GameState.Ready;
        
//        Assets.song = game.getAudio().createMusic("BestDayCut.mp3");
//        Assets.song.play();
    }

    @Override
    public void update(float deltaTime) {
    	
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call differe5nnt update methods.
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

    	//Log.e(TAG, "Width / Height : " + game.getGraphics().getWidth() + " " + game.getGraphics().getHeight());
        if (touchEvents.size() > 0) {
        	Assets.song = game.getAudio().createMusic(selectedSong.songFile);
        	Assets.song.play();
        	state = GameState.Running;
        }
    }

    private void generateBeats() {
    	//if (DEBUG) Log.i(TAG, "generateBeats:" + currentBeats);

    	while (inQueueBeatCircles.size() < MAX_AT_PLAY && beatIndex < selectedSong.beatPattern.size()) {
    		Beat currentBeat = selectedSong.beatPattern.get(beatIndex);
    		float startTime = currentBeat.startTime;
    		switch(currentBeat.type) {
				case SingleTap:
					BeatCircle newBeatCircle = beatCirclePool.newObject();
					newBeatCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]));
					newBeatCircle.startTime = startTime;
					inQueueBeatCircles.add(newBeatCircle);
					break;

				case MultipleTap:
					TapCircle newTapCircle = tapCirclePool.newObject();
					//Parse the Float Array
					String floatArrayString = currentBeat.parameters[2];
					String[] floatArrayDelimited = floatArrayString.split(",");
					
					float[] floatArray = new float[floatArrayDelimited.length];
					for (int i = 0; i < floatArrayDelimited.length; i++) {
						floatArray[i] = Float.parseFloat(floatArrayDelimited[i]);
					}
					
					newTapCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]), 
							floatArray, 
							Integer.parseInt(currentBeat.parameters[3]));
					newTapCircle.startTime = startTime;
					inQueueBeatCircles.add(newTapCircle);
					break;

				case Hold:
					HoldCircle newHoldCircle = holdCirclePool.newObject();
					newHoldCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]), 
							Float.parseFloat(currentBeat.parameters[2]));					newHoldCircle.startTime = startTime;
					inQueueBeatCircles.add(newHoldCircle);

					break;

				case Drag:
					DragCircle newDragCircle = dragCirclePool.newObject();
					newDragCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]), 
							Float.parseFloat(currentBeat.parameters[2]));					newDragCircle.startTime = startTime;
					inQueueBeatCircles.add(newDragCircle);

					break;

				default:
					break;
    		}
    		beatIndex++;
    	}
    	
    	for (int i = 0; i < inQueueBeatCircles.size(); i++) {
    		BeatCircle currentBeatCircle = inQueueBeatCircles.get(i);
    		if (GameTimer > currentBeatCircle.startTime) {
    			currentBeatCircle.start();
    			inQueueBeatCircles.remove(i);
    			inGameBeatCircles.add(currentBeatCircle);
    		}
    	}
    	
    }
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        
        //This is identical to the update() method from our Unit 2/3 game.
        
        
    	// 1. All touch input is handled here:
        
        int len = touchEvents.size();
        if (len == 0) {
            //if (DEBUG) Log.i(TAG, "updateRunning: Null Touch Events - " + deltaTime);

            for (int j = 0; j < inGameBeatCircles.size(); j++) {
            	BeatCircle beatCircle = inGameBeatCircles.get(j);
            	beatCircle.update(null);
            	if (beatCircle.isDone()) {
            		inGameBeatCircles.remove(j);
            		freeBeatCircle(beatCircle);
            		report.missCounter ++;
            	}
            }  	
        } else {
            //if (DEBUG) Log.i(TAG, "updateRunning: Time - " + deltaTime + "|touchEvents -" + touchEvents);

	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            //Update Beat Circles
	            for (int j = 0; j < inGameBeatCircles.size(); j++) {
	            	BeatCircle beatCircle = inGameBeatCircles.get(j);
	            	beatCircle.update(event);
	            	if (beatCircle.isDone()) {
	            		inGameBeatCircles.remove(j);
	            		freeBeatCircle(beatCircle);
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
	                        
	        }
        }
        
        // 2. Check miscellaneous events like death:
        
        if (livesLeft == 0) {
            state = GameState.GameOver;
        }
        
        
        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
        
        //Update Timer
        GameTimer += deltaTime;
        int deductSeconds = (int) (GameTimer / 1000);
        CountDown = selectedSong.duration - deductSeconds;
        
        //Update FPS
        if (SHOW_FPS) {
        	currentFPS = (int) (1000 / deltaTime); 
        }
    }
    
    private void freeBeatCircle(BeatCircle beatCircle) {
    	switch(beatCircle.type) {
			case SingleTap:
				beatCirclePool.free(beatCircle);
				break;
	
			case MultipleTap:
				tapCirclePool.free( (TapCircle) beatCircle);
				break;
	
			case Hold:
				holdCirclePool.free( (HoldCircle) beatCircle);
				break;
	
			case Drag:
				dragCirclePool.free( (DragCircle) beatCircle);
				break;
	
			default:
				break;
    	} //end switch
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
        
        // First draw the game elements.

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready) {
            g.clearScreen(Color.BLACK);
        	drawReadyUI();
        } else  if (state == GameState.Running) {
            g.clearScreen(Color.BLACK);
        	drawRunningUI();
        	drawRunning(deltaTime);
        } else if (state == GameState.Paused) {
            drawPausedUI();
        } else if (state == GameState.GameOver) {
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
        //g.drawString("Pause Button here.",
        //        game.getGraphics().getWidth() - 50, game.getGraphics().getHeight() - 10, paint);        

        //Pause Button
        g.drawRect(game.getGraphics().getWidth()-50, game.getGraphics().getHeight() - 10, 
        		15, 100, Color.WHITE, Style.FILL_AND_STROKE);
        
        g.drawRect(game.getGraphics().getWidth()-25, game.getGraphics().getHeight() - 10, 
        		15, 100, Color.WHITE, Style.FILL_AND_STROKE);

        //Health Bar
        g.drawRect(game.getGraphics().getWidth()/4, game.getGraphics().getHeight() - 10, 
        		game.getGraphics().getWidth()/2, 30, Color.GREEN, Style.FILL_AND_STROKE);

        //Timer 
        g.drawString(Integer.toString(CountDown), game.getGraphics().getWidth()/2, game.getGraphics().getHeight() - 30, paint);

        //FPS
        if (SHOW_FPS) g.drawString(Integer.toString(currentFPS), game.getGraphics().getWidth()-100, game.getGraphics().getHeight() - 30, paint);

    }
    
    private void drawRunning(float deltaTime) {
    	int size = inGameBeatCircles.size();
    	for (int i = 0; i < size; i++ ) {
    		BeatCircle currentBeatCircle = inGameBeatCircles.get(i);
    		currentBeatCircle.draw(game.getGraphics(), deltaTime);
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
    	Assets.song.dispose();

    }

    @Override
    public void backButton() {
        pause();
    }
}