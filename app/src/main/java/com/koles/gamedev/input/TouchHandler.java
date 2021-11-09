package com.koles.gamedev.input;

import android.view.View.OnTouchListener;

import java.util.List;

public interface TouchHandler extends OnTouchListener {
    boolean isTouched(int pointer);
    int getTouchX(int pointer);
    int getTouchY(int pointer);
    List<Input.TouchEvent> getTouchEventList();
}
