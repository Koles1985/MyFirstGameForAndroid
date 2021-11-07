package com.koles.gamedev.media;

import android.content.res.AssetFileDescriptor;

import com.koles.gamedev.graphic.Assets;

public interface Music {
    void play();
    void pause();
    void stop();

    void setLooping(boolean looping);
    void setVolume(float volume);


    boolean isLooping();
    boolean isPlaying();
    boolean isStopped();

    void dispose();
}
