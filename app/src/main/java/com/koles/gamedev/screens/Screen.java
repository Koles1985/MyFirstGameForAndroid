package com.koles.gamedev.screens;

import android.content.Context;
import android.view.SurfaceView;
import android.view.View;

import com.koles.gamedev.engin.Game;
import com.koles.gamedev.input.Input.TouchEvent;

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
    public boolean inBounds(TouchEvent event, int x, int y, int x2,
                                   int y2){
        if(event.getX() >= x & event.getX() <= x2 &
                event.getY() >= y && event.getY() <= y2){
            return true;
        }else{
            return false;
        }
    }
}
