package com.example.javaprinciples.semaphore;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;
import java.util.concurrent.Semaphore;

/**
 * 信号量 Semaphore使用例子
 */
public class SemaphoreUtil {

    private static Semaphore semaphore = new Semaphore(10);  //设置10个信号量

    public void test() {
        //循环创建10个线程，来并发访问
        for (int i=0;i<10;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, Thread.currentThread().getName() + "到停车场门口");

                    if (semaphore.availablePermits() == 0) {
                        Log.i(TAG, Thread.currentThread().getName() + "车位已满");
                    }

                    //否则车位未满
                    try {
                        semaphore.acquire();
                        Log.i(TAG, Thread.currentThread().getName() + "进入了停车场");
                        Thread.sleep(1000); //模拟车辆在停车场停留的时间
                        Log.i(TAG, Thread.currentThread().getName() + "驶出了停车场");
                        semaphore.release(); //释放令牌，更新空车位
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, ("Thread" + i));
            thread.start();
        }
    }
}
