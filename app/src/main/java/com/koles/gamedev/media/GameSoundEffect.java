package com.koles.gamedev.media;

import android.media.SoundPool;

public class GameSoundEffect implements SoundEffect{

    private int soundId;
    private SoundPool soundPool;

    public GameSoundEffect(SoundPool soundPool, int soundId){
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public int getSoundId() {
        return this.soundId;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
        soundPool.release();
    }
}
