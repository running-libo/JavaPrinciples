package com.example.javaprinciples.threadpool;

public class MyRunable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "  " + "处理任务");
    }
}
