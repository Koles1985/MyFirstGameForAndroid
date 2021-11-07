package com.koles.gamedev.input;

import java.util.ArrayList;
import java.util.List;

public class Pool <T>{
    public interface PoolObjectFactory<T>{
        T createObject();
    }

    private PoolObjectFactory<T> factory;
    private List<T> freeObjects;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory , int maxSize){
        this.factory = factory;
        this.maxSize = maxSize;
        freeObjects = new ArrayList<>(this.maxSize);
        System.out.println("Pool = " + freeObjects.size());
    }

    public T newObject(){
        T object = null;
        if(freeObjects.size() == 0){
            object = factory.createObject();
        }else{
            object = freeObjects.get(freeObjects.size() - 1);
        }
        return object;
    }

    public void tryAddObject(T object){
        if(freeObjects.size() < maxSize){
            freeObjects.add(object);
        }
    }
}
