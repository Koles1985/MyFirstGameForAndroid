package com.koles.gamedev.screens;

import android.content.Context;
import android.view.SurfaceView;
import android.view.View;

import com.koles.gamedev.engin.Game;

public abstract class Screen{
    protected final Game game;
    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);
    public abstract void present(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
