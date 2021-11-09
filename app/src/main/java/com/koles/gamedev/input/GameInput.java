package com.koles.gamedev.input;

import android.content.Context;
import android.view.View;

import java.util.List;

public class GameInput implements Input{
    TouchHandler touchHandler;
    public GameInput(Context context, View view, float scaleX, float scaleY){
        touchHandler = new GameMultiTouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouched(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public List<TouchEvent> getTouchEventsList() {
        return touchHandler.getTouchEventList();
    }
}
