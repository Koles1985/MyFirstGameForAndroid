package com.koles.gamedev.screens;

import android.content.res.AssetFileDescriptor;

import com.koles.gamedev.engin.Game;
import com.koles.gamedev.engin.Settings;
import com.koles.gamedev.graphic.Assets;
import com.koles.gamedev.graphic.GameGraphics;
import com.koles.gamedev.graphic.Graphics;
import com.koles.gamedev.input.Pool;
import com.koles.gamedev.media.GameMusicPlayer;
import com.koles.gamedev.media.Music;

import java.io.IOException;

public class LoadingScreen extends Screen{
    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Assets.setAssets(game);
        Settings.save(game.getFileIO());
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
