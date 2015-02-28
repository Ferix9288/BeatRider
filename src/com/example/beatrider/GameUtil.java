package com.example.beatrider;

import android.graphics.Color;


public class GameUtil {
    enum Rating {
        Miss, Bad, Ok, Good, Perfect
    }
    
    static final int PERFECT_COLOR = Color.GREEN;
    static final int GOOD_COLOR = 0xADFF2F; //Green Yellow
    static final int OK_COLOR = 0x00FFFF; //Cyan
    static final int BAD_COLOR = 0xFF00FF; //Magenta
    static final int MISS_COLOR = Color.RED;
    
}