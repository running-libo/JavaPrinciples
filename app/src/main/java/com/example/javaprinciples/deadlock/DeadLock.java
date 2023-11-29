package com.example.javaprinciples.deadlock;

import static com.example.javaprinciples.MainActivity.TAG;

import android.util.Log;

/**
 * 必然死锁的例子
 */
public class DeadLock {
    //2个对象2把锁
    Object o1 = new Object();
    Object o2 = new Object();

    public void deadLock() {

        //创建2个线程，首先获取自己的对象锁，确保获取了锁，然后去获取对方的锁

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (o1) {
                    //拿o1对象锁
                    Log.i(TAG, Thread.currentThread().getName() + "获的o1对象锁");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Log.i(TAG, Thread.currentThread().getName() + "等待o2锁释放...");
                    synchronized (o2) {
                        //再去拿o2对象锁
                        Log.i(TAG, Thread.currentThread().getName() + "获的o2对象锁");
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (o2) {
                    //拿o2对象锁
                    Log.i(TAG, Thread.currentThread().getName() + "获的o2对象锁");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Log.i(TAG, Thread.currentThread().getName() + "等待o1锁释放...");
                    synchronized (o1) {
                        //再去拿o1对象锁
                        Log.i(TAG, Thread.currentThread().getName() + "获的o1对象锁");
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
