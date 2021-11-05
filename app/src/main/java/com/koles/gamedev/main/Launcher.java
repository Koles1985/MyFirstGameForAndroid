package com.koles.gamedev.main;


import com.koles.gamedev.engin.Game;
import com.koles.gamedev.engin.GameClass;
import com.koles.gamedev.screens.LoadingScreen;
import com.koles.gamedev.screens.Screen;

public class Launcher extends GameClass {

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
