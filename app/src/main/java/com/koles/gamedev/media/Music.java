package com.koles.gamedev.media;

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
