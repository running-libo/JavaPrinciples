package com.example.javaprinciples.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrantlock解决死锁处理
 */
public class ReleaseDeadLock {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void deathLock() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (lock1.tryLock(10, TimeUnit.MILLISECONDS)) {
                            try {
                                //如果获取成功则执行业务逻辑，如果获取失败，则释放lock1的锁，自旋重新尝试获得锁
                                if (lock2.tryLock(10, TimeUnit.MILLISECONDS)) {
                                    System.out.println("Thread1：已成功获取 lock1 and lock2 ...");
                                    break;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                if(lock2.isHeldByCurrentThread()){
                                    lock2.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if(lock1.isHeldByCurrentThread()){
                            lock1.unlock();
                        }
                    }
                    System.out.println("Thread1：获取锁失败，重新获取---");
                    try {
                        TimeUnit.NANOSECONDS.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (lock2.tryLock(10, TimeUnit.MILLISECONDS)) {
                            try {
                                //如果获取成功则执行业务逻辑，如果获取失败，则释放lock1的锁，自旋重新尝试获得锁
                                if (lock1.tryLock(10, TimeUnit.MILLISECONDS)) {
                                    System.out.println("Thread2：已成功获取 lock2 and lock1 ...");
                                    break;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                if(lock1.isHeldByCurrentThread()){
                                    lock1.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if(lock2.isHeldByCurrentThread()){
                            lock2.unlock();
                        }
                    }
                    System.out.println("Thread2：获取锁失败，重新获取---");
                    try {
                        TimeUnit.NANOSECONDS.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void test() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            deathLock();
        }
    }
}
