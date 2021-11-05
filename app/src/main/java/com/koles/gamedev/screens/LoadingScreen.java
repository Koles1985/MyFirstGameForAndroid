package com.koles.gamedev.screens;

import android.content.res.AssetFileDescriptor;

import com.koles.gamedev.engin.Game;
import com.koles.gamedev.graphic.Assets;
import com.koles.gamedev.graphic.GameGraphics;
import com.koles.gamedev.graphic.Graphics;
import com.koles.gamedev.media.GameMusicPlayer;

import java.io.IOException;

public class LoadingScreen extends Screen{
    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newBitmap("background.png");
        Assets.logo = g.newBitmap("logo.png");
        Assets.mainMenu = g.newBitmap("main_menu.png");
        Assets.buttons = g.newBitmap("buttons.png");


        game.setScreen(new MainScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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
