package com.example.beatrider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
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
    PauseButton pauseButton;
    HealthBar healthBar;
    ReplayButton replayButtonPauseScreen;
    ReplayButton replayButtonGameOver;
    QuitButton quitButtonPauseScreen;
    QuitButton quitButtonGameOver;
    
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
        paint = game.getGraphics().getPaint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        Graphics g = game.getGraphics();
        report = new GameReport(g);
        pauseButton = new PauseButton(g);
        healthBar = new HealthBar(g);

        replayButtonPauseScreen = new ReplayButton(g, g.getWidth()/2, g.getHeight()/2);
        quitButtonPauseScreen = new QuitButton(g, g.getWidth()/2, g.getHeight()/2);
        
        replayButtonGameOver = new ReplayButton(g, g.getWidth()/2, g.getHeight()*6/7);
        quitButtonGameOver = new QuitButton(g, g.getWidth()/2, g.getHeight()*6/7);
        
        GameTimer = 0;
        CountDown = selectedSong.duration;
        beatIndex = 0;
        
        state = GameState.Ready;
    	Assets.song = game.getAudio().createMusic(selectedSong.songFile);
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
        if (touchEvents.size() > 0 && touchEvents.get(0).type == TouchEvent.TOUCH_DOWN) {
        	
        	//Reset Song
        	Assets.song.seekBegin();
        	Assets.song.play();
        	
        	report.reset();
        	report.setTotalScore(selectedSong.beatPattern.size());
        	
            GameTimer = 0;
            CountDown = selectedSong.duration;
            beatIndex = 0;            
        	state = GameState.Running;
        	
        	pauseButton.reset();

        	replayButtonPauseScreen.reset();
        	replayButtonGameOver.reset();
        	
        	quitButtonPauseScreen.reset();
        	quitButtonGameOver.reset();
        	
        	inQueueBeatCircles.clear();
			for (int i = 0; i < inGameBeatCircles.size(); i++)
				freeBeatCircle(inGameBeatCircles.get(i));
        	inGameBeatCircles.clear();
        }
    }

    private void generateBeats() {
    	//if (DEBUG) Log.i(TAG, "generateBeats:" + currentBeats);

    	while (inQueueBeatCircles.size() < MAX_AT_PLAY && beatIndex < selectedSong.beatPattern.size()) {
    		Beat currentBeat = selectedSong.beatPattern.get(beatIndex);
    		float startTime = currentBeat.startTime;
    		
    		switch(currentBeat.type) {
				case SingleTap:
		    		if (DEBUG) Log.i(TAG, "SingleTap.");
					BeatCircle newBeatCircle = beatCirclePool.newObject();
					newBeatCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]));
					newBeatCircle.startTime = startTime;
					inQueueBeatCircles.add(newBeatCircle);
					break;

				case MultipleTap:
		    		if (DEBUG) Log.i(TAG, "MultipleTap.");
					TapCircle newTapCircle = tapCirclePool.newObject();
					//Parse the Float Array
					String floatArrayString = currentBeat.parameters[2];
					String[] floatArrayDelimited = floatArrayString.split(",");
					
					float[] tapInterval = new float[floatArrayDelimited.length];
					for (int i = 0; i < floatArrayDelimited.length; i++) {
						tapInterval[i] = Float.parseFloat(floatArrayDelimited[i]);
					}
					
					newTapCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]), 
							tapInterval, 
							Integer.parseInt(currentBeat.parameters[3]));
					newTapCircle.startTime = startTime;
					inQueueBeatCircles.add(newTapCircle);
					break;

				case Hold:
					HoldCircle newHoldCircle = holdCirclePool.newObject();
					newHoldCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]), 
							Float.parseFloat(currentBeat.parameters[2]));					
					newHoldCircle.startTime = startTime;
					inQueueBeatCircles.add(newHoldCircle);

					break;

				case Drag:
					DragCircle newDragCircle = dragCirclePool.newObject();
					
					//Parse the String to create DragPoints
					//Format: x0,y0,x1,y1,etc. 
					String pointsString = currentBeat.parameters[2];
					String[] pointsStringDelimited = pointsString.split(",");
					
					ArrayList<Point> dragPoints = new ArrayList<Point>();
					
					for (int i = 0; i < pointsStringDelimited.length; i+=2) {
						int x = Integer.parseInt(pointsStringDelimited[i]);
						int y = Integer.parseInt(pointsStringDelimited[i+1]);
						dragPoints.add(new Point(x, y));
					}
					
					//Parse the String to create float[] of dragTimes
					//Format: t0, t1, etc.
					String timesString = currentBeat.parameters[3];
					String[] timesStringDelimited = timesString.split(",");
					float[] dragTimes = new float[timesStringDelimited.length];
					for (int i = 0; i < timesStringDelimited.length; i++) {
						dragTimes[i] = Float.parseFloat(timesStringDelimited[i]);
					}

					newDragCircle.setInitialization(Integer.parseInt(currentBeat.parameters[0]),
							Integer.parseInt(currentBeat.parameters[1]),
							dragPoints,
							dragTimes);
					newDragCircle.startTime = startTime;
					inQueueBeatCircles.add(newDragCircle);

					break;

				default:
					break;
    		}
    		beatIndex++;
    	}
    	
    	for (int i = 0; i < inQueueBeatCircles.size(); i++) {
    		BeatCircle currentBeatCircle = inQueueBeatCircles.get(i);
    		currentBeatCircle.setPaint(this.paint);
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
            		handleScore(beatCircle.rating);
            		j--;
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
	            		handleScore(beatCircle.rating);
	            		inGameBeatCircles.remove(j);
	            		freeBeatCircle(beatCircle);
	            		j--;
	            	}
	            }
	            
	            
	            
	            //Update Pause Button
	            pauseButton.update(event);	
	            
	        }
        }
        
        // 2. Check miscellaneous events like death:
                
        //Go to GameOver - if user died or time completed 
        if (CountDown <= 0) {
        	addRemainingScore();
        	gameOver();
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
            
            //Update Pause Button
            pauseButton.update(event);
            if (pauseButton.playPressed()) {
            	resume();
            }
            
            //Update Replay Button
            replayButtonPauseScreen.update(event);
            if (replayButtonPauseScreen.actionTriggered()) {
            	reset();
            }
            
            //Update Quit BUtton
            quitButtonPauseScreen.update(event);
            if (quitButtonPauseScreen.actionTriggered()) {
            	quit();
            }
        }
    }

    //Options: Replay song. Choose next song. Quit.
    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            
            //Update Replay Button
            replayButtonGameOver.update(event);
            if (replayButtonGameOver.actionTriggered()) {
            	reset();
            }
            
            //Update Quit BUtton
            quitButtonGameOver.update(event);
            if (quitButtonGameOver.actionTriggered()) {
            	quit();
            }
        }

    }
    
    void addRemainingScore() {
    	for (int i = 0 ; i < inGameBeatCircles.size(); i++) {
    		BeatCircle beatCircle = inGameBeatCircles.get(i);
    		if (beatCircle.isInRating() || beatCircle.isDone()) {
    			handleScore(beatCircle.rating);
    		}
    		
    		freeBeatCircle(beatCircle);
    		inGameBeatCircles.remove(beatCircle);
    		i--;
    	}
    }

	void handleScore(GameUtil.Rating rating) {
    	switch(rating) {
		case Miss:
			report.missCounter++;
			report.comboBreaker();
			break;
		case Bad:
			report.badCounter++;
			report.runningScore += GameReport.BAD_SCORE;
			report.comboBreaker();
			break;
		case Ok:
			report.okCounter++;
			report.runningScore += GameReport.OK_SCORE;
			report.comboBreaker();
			break;
		case Good:
			report.goodCounter++;
			report.runningScore += GameReport.GOOD_SCORE;
			report.plusCombo();
			break;
		case Perfect:
			report.perfectCounter++;
			report.runningScore += GameReport.PERFECT_SCORE;
			report.plusCombo();
			break;
		default:
			break;
    	} // end switch
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
        	drawRunningUI(deltaTime);
        	drawRunning(deltaTime);
        	//Draw updated Pause button first
            if (pauseButton.pausePressed()) {
            	pause();
            }
            
        } else if (state == GameState.Paused) {
            drawPausedUI(deltaTime);
        } else if (state == GameState.GameOver) {
            drawGameOverUI(deltaTime);
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
        
        paint.setStyle(Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
        paint.setTextSize(65);
        
        g.drawString("Tap to begin the game.",
                g.getWidth()/2, 300, paint);
    }

    private void drawRunningUI(float deltaTime) {
        Graphics g = game.getGraphics();
        //g.drawString("Pause Button here.",
        //        game.getGraphics().getWidth() - 50, game.getGraphics().getHeight() - 10, paint);        

        pauseButton.draw(g, deltaTime);
        
        //Health Bar
        g.drawRect(game.getGraphics().getWidth()/4, game.getGraphics().getHeight() - 10, 
        		game.getGraphics().getWidth()/2, 30, Color.GREEN, Style.FILL_AND_STROKE);

        //Timer
        this.paint.setColor(Color.WHITE);
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(1);
        this.paint.setTextSize(50);
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

    private void drawPausedUI(float deltaTime) {
        Graphics g = game.getGraphics();
        
        //Redraw Running Screen
        if (replayButtonPauseScreen.redraw() || quitButtonPauseScreen.redraw()) {
            g.clearScreen(Color.BLACK);
	        drawRunning(0);
	        drawRunningUI(0);
	        replayButtonPauseScreen.reset();
	        quitButtonPauseScreen.reset();
        }
        
        //Gray Filter
        g.drawARGB(10, 0x88, 0x88, 0x88); 
 
        //Pause Button
        pauseButton.draw(g, deltaTime);
        
        //Replay Button
        replayButtonPauseScreen.draw(g, deltaTime);
        
        //Quit Button
        quitButtonPauseScreen.draw(g, deltaTime);
        
    }

    private void drawGameOverUI(float deltaTime) {
        Graphics g = game.getGraphics();
        
        //Redraw Running Screen
        if (replayButtonGameOver.redraw() || quitButtonGameOver.redraw()) {
            g.clearScreen(Color.BLACK);
	        drawRunning(0);
	        drawRunningUI(0);
	        replayButtonGameOver.reset();
	        quitButtonGameOver.reset();
        }
        
        //Gray Filter
        g.drawARGB(12, 0x88, 0x88, 0x88); 
        
        report.draw(g, deltaTime);
        
        //Replay Button
        replayButtonGameOver.draw(g, deltaTime);
        
        //Quit Button
        quitButtonGameOver.draw(g, deltaTime);
 
    }

    public void reset() {
    	if (state == GameState.Paused || state == GameState.GameOver) {
    		this.state = GameState.Ready; 
    	}
    }
    
    public void quit() {
    	game.destroy();
    }
    
    public void gameOver() {
    	Assets.song.pause();
    	report.setScore();
    	if (state == GameState.Running) {
    		this.state = GameState.GameOver;
    	}
    }
    
    @Override
    public void pause() {
        if (state == GameState.Running) {
        	Assets.song.pause();
            state = GameState.Paused;
        }
    }

    @Override
    public void resume() {
    	if (state == GameState.Paused) {
    		Assets.song.play();
    		state = GameState.Running;
    	}
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