package com.koles.gamedev.media;

public interface SoundEffect {

    void play(float volume);
    void dispose();
    int getSoundId();
}
