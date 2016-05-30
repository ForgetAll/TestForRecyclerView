package com.example.luo_pc.testforrecyclerview.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by luo-pc on 2016/5/30.
 */
public class BitmapMemoryCache {
    private final String TAG = "BitmapMemoryCache";
    int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
    int catchSize = maxMemory / 8;
    LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(catchSize){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight() / 1024;
        }
    };

    public void addBitmapToMemory(String key,Bitmap bitmap){
        if(getBitmapFromMemory(key) == null){
            lruCache.put(key,bitmap);
            Log.i(TAG," "+maxMemory);
        }
    }

    public Bitmap getBitmapFromMemory(String key){

        return lruCache.get(key);
    }
}
