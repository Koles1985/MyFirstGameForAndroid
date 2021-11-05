package com.koles.gamedev.graphic;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

public class GameGraphics implements Graphics{
    private AssetManager assetManager;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint = new Paint();
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();
    public GameGraphics(Bitmap frameBuffer, AssetManager assetManager){
        this.frameBuffer = frameBuffer;
        this.assetManager = assetManager;
        this.canvas = new Canvas(frameBuffer);
    }

    @Override
    public Bitmap newBitmap(String imageName){
        InputStream in = null;
        Bitmap bitmap = null;

        try{
            in = assetManager.open("images/" + imageName);
            bitmap = BitmapFactory.decodeStream(in);
        }catch(IOException e1){
            e1.printStackTrace();
            System.out.println(e1.getMessage() + " - GameGraphics.newBitmap() - e1 - exception");
        }finally {
            if(in != null){
                try{
                    in.close();
                }catch(IOException e2){
                    e2.printStackTrace();
                    System.out.println(e2.getMessage() + " - GameGraphics.newBitmap() - e2" +
                            " - exception");
                }
            }
        }
        return bitmap;
    }

    @Override
    public void drawBitmap(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    @Override
    public void drawBitmap(Bitmap bitmap, int x, int y, int srcX, int srcY,
                           int width, int height) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + width - 1;
        srcRect.bottom = srcY + height - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width - 1;
        dstRect.bottom = y + height - 1;

        canvas.drawBitmap(bitmap, srcRect, dstRect,null);
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
