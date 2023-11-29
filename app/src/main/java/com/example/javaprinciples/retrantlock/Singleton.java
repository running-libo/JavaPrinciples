package com.example.javaprinciples.retrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
    private static Singleton instance;
    private static Lock lock = new ReentrantLock();

    public static Singleton getInstance() {
        lock.lock();

        try {
            if (instance == null) {
                instance = new Singleton();
            }
        } finally {
            lock.unlock();
        }

        return instance;
    }

    /**
     * 可重入性
     */
    public static void relock() {
        ReentrantLock lock = new ReentrantLock();

        for (int i=0;i<3;i++) {
            lock.lock();
        }

        for (int i=0;i<3;i++) {
            lock.unlock();
        }
    }

    public static void tryLock() {
        ReentrantLock lock = new ReentrantLock();
        try {
            if (lock.tryLock(10000, TimeUnit.SECONDS)) {
                //TO DO

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
