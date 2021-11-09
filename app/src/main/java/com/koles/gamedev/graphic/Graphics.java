package com.koles.gamedev.graphic;

import android.graphics.Bitmap;

import java.io.IOException;

public interface Graphics {
    Bitmap newBitmap(String imageName);
    void drawBitmap(Bitmap bitmap, int x, int y);
    void drawBitmap(Bitmap bitmap, int x, int y, int srcX, int srcY, int width, int height);
    void drawText(Bitmap bitmap, String text, int x, int y);

    int getWidth();
    int getHeight();
}
