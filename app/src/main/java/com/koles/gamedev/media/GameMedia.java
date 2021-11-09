package com.koles.gamedev.media;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.File;
import java.io.IOException;

public class GameMedia implements Media{

    private AssetManager assetManager;
    private SoundPool soundPool;
    private AudioAttributes audioAttributes;

    public GameMedia(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assetManager = activity.getAssets();
        this.audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        this.soundPool = new SoundPool.Builder()
                .setMaxStreams(20)
                .setAudioAttributes(audioAttributes)
                .build();
    }

    @Override
    public Music newGameMusicPlayer(String longMusicFile){
        GameMusicPlayer gameMusicPlayer = null;
        try{
            AssetFileDescriptor descriptor = assetManager.openFd("sound"
                    + File.separator + longMusicFile);
            gameMusicPlayer = new GameMusicPlayer(descriptor);
        }catch(IOException e){
            System.out.println("GameMedia.newGameMusicPlayer() - IOException - "
                   + e.getMessage());
            e.printStackTrace();
        }
        return gameMusicPlayer;
    }

    @Override
    public SoundEffect newSoundEffect(String soundEffectFile){
        GameSoundEffect gameSoundEffect = null;
        try{
            AssetFileDescriptor descriptor = assetManager.openFd("sound"
                    + File.separator + soundEffectFile);
            int soundId = soundPool.load(descriptor, 0);
            gameSoundEffect = new GameSoundEffect(soundPool, soundId);
        }catch (IOException e){
            System.out.println("GameMedia.newSoundEffect() - IOException - "
                                + e.getMessage());
            e.printStackTrace();
        }
        return gameSoundEffect;
    }
}
