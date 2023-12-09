package com.example.javaprinciples.lrucache;

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryCacheManager {

    private long memory = Runtime.getRuntime().maxMemory() / 8;  //获取手机最大内存的1/8 做缓存容量

    private LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap> ((int) memory) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    };

    /**
     * 将Bitmap放入内存中
     * @param url 利用图片url作为缓存key
     * @param bitmap
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }

    /**
     * 从内存中读图片
     * @param url
     * @return
     */
    public  Bitmap getBitmapFromMemory(String url) {
        return lruCache.get(url);
    }
}
