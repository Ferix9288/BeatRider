package com.example.beatrider;

import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;

public class GameActivity extends AndroidGame {
	
    @Override
    public Screen getInitScreen() {
        return new GameScreen(this); 
    }
    
    
}
