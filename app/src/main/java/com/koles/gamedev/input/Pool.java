package com.koles.gamedev.input;

public class Pool <T>{
    public interface PoolObjectFactory<T>{
        T createObject();
    }

    
}
