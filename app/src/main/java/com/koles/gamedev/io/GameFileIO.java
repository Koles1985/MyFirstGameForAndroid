package com.koles.gamedev.io;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GameFileIO implements FileIO{
    private AssetManager assetManager;
    private String path;
    public GameFileIO(AssetManager assetManager){
        this.assetManager = assetManager;
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
    @Override
    public InputStream readAssets(String assetsName){
        InputStream in = null;
        try{
            in = assetManager.open(assetsName);
        }catch(IOException e1){
            System.out.println(e1.getMessage() + "GameFileIO.reedAssets().e1 - exception");
        }
        return in;
    }


    @Override
    public InputStream readFile(String fileName){
        InputStream in = null;
        try{
            in = new FileInputStream(path + fileName);
        }catch(IOException e2){
            Log.d("GameFileIO", e2.getMessage() + "e2 - readFile");
        }
        return in;
    }

    @Override
    public OutputStream writeFile(String fileName){
        OutputStream out = null;
        try{
            out = new FileOutputStream(path + fileName);
        }catch(IOException e3){
            Log.d("GameFileIO", e3.getMessage() + " - e3 - writeFile");
        }
        return out;
    }
}
