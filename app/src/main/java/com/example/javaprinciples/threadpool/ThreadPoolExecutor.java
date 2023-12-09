package com.example.javaprinciples.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 自实现简单线城市
 */
public class ThreadPoolExecutor {

    // 利用阻塞队列实现生产者-消费者模式
    BlockingQueue<Runnable> workQueue;
    //管理内部工作线程
    final List<Thread> threads = new ArrayList<>();

    public ThreadPoolExecutor(int coreSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;

        //创建核心线程
        for (int i=0;i<coreSize;i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            threads.add(workerThread);
        }
    }

    /**
     * 生产者  提交任务
     * @param runnable
     */
    public void execute(Runnable runnable) {
        try {
            // 队列已满，put 会一直等待
            workQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 工作线程负责消费任务，并执行任务
     */
    class WorkerThread extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    Runnable runnable = workQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void createThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, new LinkedBlockingDeque<>());
        threadPoolExecutor.execute(() -> System.out.println("线程池开始执行任务"));
    }
}
