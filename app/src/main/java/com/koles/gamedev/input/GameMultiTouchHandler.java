package com.koles.gamedev.input;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import com.koles.gamedev.input.Input.TouchEvent;
import com.koles.gamedev.input.Pool.PoolObjectFactory;

public class GameMultiTouchHandler implements TouchHandler{
    private boolean[] isTouch = new boolean[10];
    private int[] touchX = new int [10];
    private int[] touchY = new int [10];

    float scaleX;
    float scaleY;

    Pool<TouchEvent> gameTouchEventPool;
    List<TouchEvent> gameTouchEventList = new ArrayList<TouchEvent>();
    List<TouchEvent> gameTouchEventListBuffer = new ArrayList<TouchEvent>();

    public GameMultiTouchHandler(View view, float scaleX, float scaleY){
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };

        gameTouchEventPool = new Pool<TouchEvent>(factory, 20);
    }

    @Override
    public boolean isTouched(int pointer) {
        synchronized (this) {
            if(pointer < isTouch.length & pointer >= 0) {
                return isTouch[pointer];
            }else{
                return false;
            }
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            if(pointer < touchX.length & pointer >= 0){
                return touchX[pointer];
            }else{
                return 0;
            }
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            if(pointer < touchY.length & pointer >= 0){
                return touchY[pointer];
            }else{
                return 0;
            }
        }
    }

    @Override
    public List<TouchEvent> getTouchEventList() {
        synchronized (this){
            for(int i = 0; i < gameTouchEventList.size(); i++){
                gameTouchEventPool.tryAddObject(gameTouchEventList.get(i));
            }
            gameTouchEventList.clear();
            gameTouchEventList.addAll(gameTouchEventListBuffer);
            gameTouchEventListBuffer.clear();
            return gameTouchEventList;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            int action = event.getActionMasked();
            int pointerIndex = event.getActionIndex();
            int pointerId = event.getPointerId(pointerIndex);

            TouchEvent gameTouchEvent;

            switch(action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    gameTouchEvent = gameTouchEventPool.newObject();
                    gameTouchEvent.setType(TouchEvent.TOUCH_DOWN);
                    gameTouchEvent.setPointerId(pointerId);
                    gameTouchEvent.setX((int) (event.getX(pointerIndex) * scaleX));
                    touchX[pointerId] = gameTouchEvent.getX();
                    gameTouchEvent.setY((int) (event.getY(pointerIndex) * scaleY));
                    touchY[pointerId] = gameTouchEvent.getY();
                    isTouch[pointerId] = true;
                    gameTouchEventListBuffer.add(gameTouchEvent);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_POINTER_UP:
                    gameTouchEvent = gameTouchEventPool.newObject();
                    gameTouchEvent.setType(TouchEvent.TOUCH_UP);
                    gameTouchEvent.setPointerId(pointerId);
                    gameTouchEvent.setX((int)(event.getX(pointerIndex) * scaleX));
                    gameTouchEvent.setY((int)(event.getY(pointerIndex) * scaleY));
                    touchX[pointerId] = gameTouchEvent.getX();
                    touchY[pointerId] = gameTouchEvent.getY();
                    isTouch[pointerId] = false;
                    gameTouchEventListBuffer.add(gameTouchEvent);
                    break;

                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    for(int i = 0; i < pointerCount; i++){
                        pointerIndex = i;
                        pointerId = event.getPointerId(pointerIndex);
                        gameTouchEvent = gameTouchEventPool.newObject();
                        gameTouchEvent.setType(TouchEvent.TOUCH_MOVE);
                        gameTouchEvent.setPointerId(pointerId);
                        gameTouchEvent.setX((int)(event.getX(pointerIndex) * scaleX));
                        gameTouchEvent.setY((int)(event.getY(pointerIndex) * scaleY));
                        touchX[pointerId] = gameTouchEvent.getX();
                        touchY[pointerId] = gameTouchEvent.getY();
                        isTouch[pointerId] = true;
                        gameTouchEventListBuffer.add(gameTouchEvent);
                    }
                    break;
            }


            return true;
        }
    }
}
