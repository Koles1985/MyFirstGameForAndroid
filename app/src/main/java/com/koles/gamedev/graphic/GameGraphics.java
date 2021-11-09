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
    public void drawText(Bitmap bitmap, String text, int x, int y) {
        char character;
        int spaceX = 45;
        for(int i = 0; i < text.length(); i++){
            character = text.charAt(i);
            if(character == ' '){
                x += spaceX;
                continue;
            }
            int srcX = 0;
            int srcWidth = 0;
            if(character == '.'){
                srcX = 485;
                srcWidth = 13;
            }else{
                srcX = (character - '0') * 50;
                srcWidth = 50;
            }

            drawBitmap(bitmap, x, y, srcX, 0, srcWidth, 50);
            x += srcWidth;
        }
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
