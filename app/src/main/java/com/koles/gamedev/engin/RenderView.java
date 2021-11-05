package com.koles.gamedev.engin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.koles.gamedev.graphic.Graphics;

public class RenderView extends SurfaceView implements Runnable {
    private Thread thread = null;
    private Bitmap bitmap;
    volatile boolean isRun = false;
    private SurfaceHolder holder;
    private GameClass game;
    public RenderView(GameClass gameClass, Bitmap bitmap) {
        super(gameClass);
        this.game = gameClass;
        this.bitmap = bitmap;
        holder = getHolder();

    }

    public void resume(){
        isRun = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        render();
    }

    private void render(){
        Rect dstRect = new Rect();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        long startTime = System.nanoTime();

        while(isRun){
            if(!holder.getSurface().isValid()){
                continue;
            }
            float deltaTime = (float)System.nanoTime() - startTime / 1000000000.0f;
            startTime = System.nanoTime();

            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(bitmap , null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);

        }
    }

    public  void pause(){
        isRun = false;
        try {
            while (thread.isAlive()) {
                thread.join();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage() + " - RenderView.pause() - e - exception");
        }
    }

}
