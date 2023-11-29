package com.example.javaprinciples.waitnotify;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

public class StrangePrinter {

    Object odd = new Object(); // 奇数条件锁
    Object even = new Object(); // 偶数条件锁
    private int max;

    private AtomicInteger status = new AtomicInteger(1); // AtomicInteger保证可见性，也可以用volatile

    public StrangePrinter(int max) {
        this.max = max;
    }

    public class MyPrinter implements Runnable {
        private String name;
        private int type; // 打印的类型，0：代表打印奇数，1：代表打印偶数

        public MyPrinter(String name, int type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public void run() {
            if (type == 1) {
                while (status.get() <= max) { // 打印奇数
                    if (status.get() % 2 == 0) { // 如果当前为偶数，则等待
                        synchronized (odd) {
                            try {
                                odd.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.i(TAG, name + " - " + status.getAndIncrement()); // 打印奇数
                        synchronized (even) { // 通知偶数打印线程
                            even.notify();
                        }
                    }
                }
            } else {
                while (status.get() <= max) { // 打印偶数
                    if (status.get() % 2 != 0) { // 如果当前为奇数，则等待
                        synchronized (even) {
                            try {
                                even.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.i(TAG, name + " - " + status.getAndIncrement()); // 打印偶数
                        synchronized (odd) { // 通知奇数打印线程
                            odd.notify();
                        }
                    }
                }
            }
        }
    }
}
