package com.example.javaprinciples.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCreate {

    public void createThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, // 核心线程数
                16, // 最大线程数
                60, TimeUnit.MILLISECONDS, // 线程空闲时间
                new LinkedBlockingDeque<>(1024), // 使用有界阻塞队列
                // 自定义拒绝策略，一般和降级策略配合使用
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                         // 队列已满，拒绝执行
                        throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + executor.toString());
                    }
                }
        );
        threadPoolExecutor.submit(() -> System.out.println("线程池开始执行任务"));
    }
}
