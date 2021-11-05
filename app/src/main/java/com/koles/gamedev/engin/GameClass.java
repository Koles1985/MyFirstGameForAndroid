package com.koles.gamedev.engin;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.koles.gamedev.graphic.Assets;
import com.koles.gamedev.graphic.GameGraphics;
import com.koles.gamedev.graphic.Graphics;
import com.koles.gamedev.io.FileIO;
import com.koles.gamedev.io.GameFileIO;
import com.koles.gamedev.media.GameMusicPlayer;
import com.koles.gamedev.media.Media;
import com.koles.gamedev.media.Music;
import com.koles.gamedev.screens.Screen;

import java.io.File;
import java.io.IOException;

public class GameClass extends Activity implements Game{
    private Graphics graphics;
    private Screen screen;
    private Bitmap frameBuffer;
    private RenderView renderView;
    private FileIO fileIO;
    private Music player;
    private AssetFileDescriptor descriptor;
    private String path = Environment.getExternalStorageDirectory().getPath();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        boolean isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 1280 : 720;
        int frameBufferHeight = isLandscape ? 720 : 1280;
        float scaleX = (float)frameBufferWidth / displayMetrics.widthPixels;
        float scaleY = (float)frameBufferHeight / displayMetrics.heightPixels;

        frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight,
                Bitmap.Config.ARGB_8888);
        System.out.println("framaBufferWidth = " + frameBufferWidth);
        System.out.println("framaBufferHeight = " + frameBufferHeight);
        System.out.println("scaleX = " + scaleX);
        System.out.println("scaleY = " + scaleY);
        System.out.println("displayMetrics.widthPixels = " + displayMetrics.widthPixels);
        System.out.println("displayMetrics.heightPixels = " + displayMetrics.heightPixels);
        fileIO = new GameFileIO(getAssets());
        graphics = new GameGraphics(frameBuffer, getAssets());
        screen = getStartScreen();
        renderView = new RenderView(this, frameBuffer);
        System.out.println("GameClass.path = " + path);
        setContentView(renderView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        renderView.resume();
        screen.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        renderView.pause();
        player.pause();
        if(isFinishing()){
            dispose();
        }
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }

    @Override
    public void setScreen(Screen screen) {
        if(screen == null){
            System.out.println("GameClass.setScreen() - screen == null");
            return;
        }
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }


    @Override
    public void setDescriptor(String fileName){
        descriptor = null;
        try{
            descriptor = getAssets().openFd("sound" + File.separator + fileName);
        }catch (IOException e){
            Log.d("GameClass", e.getMessage() + " GameClass.getDescriptor - ex");
        }
    }

    public AssetFileDescriptor getDescriptor(){
        return descriptor;
    }

    @Override
    public void dispose() {
        frameBuffer.recycle();
    }

}
