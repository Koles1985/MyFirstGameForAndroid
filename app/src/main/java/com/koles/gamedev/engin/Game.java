package com.koles.gamedev.engin;

import android.content.res.AssetFileDescriptor;

import com.koles.gamedev.graphic.Graphics;
import com.koles.gamedev.io.FileIO;
import com.koles.gamedev.media.GameMusicPlayer;
import com.koles.gamedev.media.Media;
import com.koles.gamedev.media.Music;
import com.koles.gamedev.screens.Screen;

public interface Game {
    Screen getCurrentScreen();
    Screen getStartScreen();
    void setScreen(Screen screen);


    Graphics getGraphics();
    FileIO getFileIO();
    void setDescriptor(String fileName);
    AssetFileDescriptor getDescriptor();

    void dispose();
}
