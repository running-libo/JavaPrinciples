package com.example.javaprinciples.objectclasslock;

import static com.example.javaprinciples.MainActivity.TAG;

import android.util.Log;

public class LockTask {
    public synchronized static void task1() {
        Log.i(TAG, Thread.currentThread().getName() + "start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, Thread.currentThread().getName() + "end");
    }

    public synchronized static void task2() {
        Log.i(TAG, Thread.currentThread().getName() + "start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, Thread.currentThread().getName() + "end");
    }

    public synchronized void task3() {
        Log.i(TAG, Thread.currentThread().getName() + "start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, Thread.currentThread().getName() + "end");
    }

    static class Thread1 extends Thread {
        LockTask lockTask;

        public Thread1(LockTask lockTask) {
            this.lockTask = lockTask;
        }

        @Override
        public void run() {
            lockTask.task1();
        }
    }

    static class Thread2 extends Thread {
        LockTask lockTask;

        public Thread2(LockTask lockTask) {
            this.lockTask = lockTask;
        }

        @Override
        public void run() {
            lockTask.task2();
        }
    }

    static class Thread3 extends Thread {
        LockTask lockTask;

        public Thread3(LockTask lockTask) {
            this.lockTask = lockTask;
        }

        @Override
        public void run() {
            lockTask.task3();
        }
    }
}
