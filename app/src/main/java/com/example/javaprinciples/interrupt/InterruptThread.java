package com.example.javaprinciples.interrupt;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;

/**
 * 中断一个线程示例
 */
public class InterruptThread extends Thread {

    public void toInterrupt() {
        interrupt();
    }

    @Override
    public void run() {

        if (isInterrupted()) {
            //判断当前线程是否已经被标识为中断
            Log.i(TAG, "线程被中断");
        }
    }
}
