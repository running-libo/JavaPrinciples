package com.example.javaprinciples.threadlocal;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;
import androidx.annotation.Nullable;

public class ThreadLocalUse {

    //创建threadLocal时并初始化
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Nullable
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public static void test() {
        Thread[] threads = new Thread[3];
        for (int i=0;i<threads.length;i++) {
            threads[i] = new Thread(new TestThread(i), "thread" + i);
        }
        for (int i=0;i<threads.length;i++) {
            threads[i].start();
        }
    }

    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            Integer s = threadLocal.get();
            s = s + id;
            threadLocal.set(s);
            Log.i(TAG, Thread.currentThread().getName() + "  threadLocal -> value: " + threadLocal.get());
        }
    }
}
