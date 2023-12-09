package com.example.javaprinciples.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolType {

    /**
     * 缓存线程池
     */
    public static void cachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++) {
            executor.execute(new MyRunable());
        }
    }

    public static void singleThreadPool() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for(int i=0;i<5;i++){
            executor.execute(new MyRunable());
        }
    }

    public static void fixedThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0;i<5;i++){
            executor.execute(new MyRunable());
        }
    }

    public static void scheduledThreadPool() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        executor.scheduleWithFixedDelay(() -> System.out.println(Thread.currentThread().getName() + "  " + "处理任务"),
                4, 4, TimeUnit.SECONDS);
    }
}
