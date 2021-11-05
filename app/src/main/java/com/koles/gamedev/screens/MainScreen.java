package com.koles.gamedev.screens;
import com.koles.gamedev.engin.Game;
import com.koles.gamedev.engin.Settings;
import com.koles.gamedev.graphic.Assets;
import com.koles.gamedev.graphic.Graphics;

public class MainScreen extends Screen{
    public MainScreen(Game game){
        super(game);

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawBitmap(Assets.background, 0,0);
        g.drawBitmap(Assets.logo, 10, 150);
        g.drawBitmap(Assets.mainMenu, 10, 400 );
        if(Settings.soundEnabled) {
            //soundOn
            g.drawBitmap(Assets.buttons, 0, g.getHeight() - 72, 0, 72,
                    72, 72);
        }else{
            g.drawBitmap(Assets.buttons, 0, g.getHeight() - 72,
                    72, 72, 72, 72);
        }

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
