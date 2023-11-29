package com.example.javaprinciples.sealticket;

import android.util.Log;

/**
 * Java 多线程之多个窗口售票问题
 */
public class MyRunnable implements Runnable {

    private int tickets = 20;
    @Override
    public void run() {
        while(true) {
            synchronized (this) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Log.i("minfo", Thread.currentThread().getName() + "售出了第" + (tickets--) + "张票");
                }
            }

        }
    }
}
