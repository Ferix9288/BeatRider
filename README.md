## Description 

Side project after being inspired by Rhythm-based games where player must touch and interact with the screen in time with the beats. Unfortunately, this write-up is almost two years later so forgive me for lack of details. If anyone is particularly interested, feel free to message me, and I can provide more. Also may pick this up again in the future.

[Gameplay](https://www.youtube.com/watch?v=F6VdWBAiNe0&index=1&list=PLFjocFTwYusId9T9TNZ0n57A8yXUcy4Dj) 

**High Overview** 
1. Followed the principles of gaming framework from [Kilobot](http://www.kilobolt.com/game-development-tutorial.html) , i.e. update, run loop, etc. 
2. Created object-oriented classes of circles to tap and how to draw them. 
3. Used Audacity to mark where the beats are in a song. Then utilized a python script to create all the necessary game objects with those time stamps. 

## Status 
+ Main starting game interface
+ Has one song populated. 
+ Over gameplay completed with pause and report done  

## Future Steps 
1. Include more games. 
2. Populate all the other menu items. 
3. Keep track of user's scores and runs. 
4. Perhaps investigate the interface on when users need to tap. 

## Code 

_Organization_
```
  Main Source in src/com/example/beatrider/Gamescreen.java
    GameScreen.java //game code predominantly here
    Lots of other class objects //object oriented programming
  python
    To process labels from Audacity 
  
