package com.koles.gamedev.media;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.koles.gamedev.engin.Settings;
import com.koles.gamedev.graphic.Assets;

import java.io.IOException;

public class GameMusicPlayer implements Music, MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    private boolean isPrepare = false;
    private int count = 0;
    public GameMusicPlayer(AssetFileDescriptor descriptor){
        player = new MediaPlayer();
        count++;
        try{
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
                    descriptor.getLength());
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.prepare();
            isPrepare = true;
        }catch (IOException e1){
            Log.d("GameMusicPlayer", e1.getMessage() + " GameMusicPlayer constructor");
            player = null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void play() {
        if(player.isPlaying()) {
            return;
        }
        synchronized (this){
            try{
                if(!isPrepare){
                    player.prepare();
                }
                player.start();
            }catch(IllegalStateException e2){
                Log.d("GameMusicPlayer", e2.getMessage() + " - e2");
            }catch(IOException e3){
                Log.d("GameMusicPlayer", e3.getMessage() + " - e3");
            }
        }

    }

    @Override
    public void pause() {
        synchronized (this){
            if(player.isPlaying()){
                player.pause();
            }
        }
    }

    @Override
    public void stop() {
        synchronized (this){
            player.stop();
            isPrepare = false;
        }
    }

    @Override
    public void setLooping(boolean looping) {
        player.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        player.setVolume(volume, volume);
    }

    @Override
    public boolean isLooping() {
        return player.isLooping();
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return isPrepare;
    }

    @Override
    public void dispose() {
        synchronized (this) {
            if(player.isPlaying()){
                player.stop();
            }
            player.release();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this){
            isPrepare = false;
        }
    }
}
