package com.example.beatrider;

import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends AndroidGame {
	
    @Override
    public Screen getInitScreen() {
        return new GameScreen(this); 
    }
    
}
