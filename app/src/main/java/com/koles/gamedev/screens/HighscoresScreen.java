package com.koles.gamedev.screens;

import com.koles.gamedev.engin.Game;
import com.koles.gamedev.engin.Settings;
import com.koles.gamedev.graphic.Assets;
import com.koles.gamedev.graphic.Graphics;
import com.koles.gamedev.input.Input.TouchEvent;

import java.util.List;

public class HighscoresScreen extends Screen {
    private Graphics g;
    public HighscoresScreen(Game game){
        super(game);
        g = game.getGraphics();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> highscoreScreenEvent = game.getInput().getTouchEventsList();
        for(int i = 0; i < highscoreScreenEvent.size(); i++){
            if(highscoreScreenEvent.get(i).getType() == TouchEvent.TOUCH_UP){
                if(inBounds(highscoreScreenEvent.get(i), g.getWidth() - 72,
                        g.getHeight() - 72, g.getWidth(), g.getHeight())){
                    if(Settings.soundEnabled){
                        Assets.getClick().play(1.0f);
                    }//close if(soundEnabled)
                    game.setScreen(new MainScreen(game));
                }//close if(inBounds)
            }//close if(type)
        }//close for()
    }//close update()

    @Override
    public void present(float deltaTime) {
        g.drawBitmap(Assets.getBackground(), 0, 0);
        g.drawBitmap(Assets.getButtons(), g.getWidth() - 72, g.getHeight() - 72,
                72, 144, 72, 72);

        g.drawBitmap(Assets.getMainMenu(), 10, 100, 0, 205,
                700, 205);

        g.drawText(Assets.getNumbers(), "1. 1034 658", 10, 500);

    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
