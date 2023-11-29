package com.example.javaprinciples.waitnotify;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;
import java.util.LinkedList;

/**
 * 生产者、消费者模式demo
 */
public class ProductAndCusumer {
    private static LinkedList<String> queue = new LinkedList<>();   //需要将queue作为多线程操作的锁
    public static final int MAX_CAPACITY = 5;

    public static class Producter extends Thread {
        @Override
        public void run() {
            while(true) {
                synchronized (queue) {
                    if (queue.size() >= MAX_CAPACITY) {
                        Log.i(TAG, "队列已满");
                        try {
                            queue.wait();   //停止生产产品，此时在生产者的线程中，调用queue.wait主动去释放锁，让当前消费线程进入等待唤醒去拿锁
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    //可以生产数据
                    String product = "生产：" + Thread.currentThread().getName();
                    queue.add(product);
                    Log.i(TAG, product);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    queue.notifyAll();  //唤醒所有线程拿锁
                }
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while(true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        Log.i(TAG, "队列为空");
                        try {
                            queue.wait();  //通知生产者生产数据
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    //可以消费数据
                    Log.i(TAG, "消费：" + queue.pop());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    queue.notifyAll();
                }
            }
        }
    }
}
