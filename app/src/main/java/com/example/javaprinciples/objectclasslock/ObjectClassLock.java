package com.example.javaprinciples.objectclasslock;

/**
 * 类锁对象锁测试
 */
public class ObjectClassLock {

    public static void test() {
        LockTask lockTask = new LockTask();
        LockTask.Thread1 thread1 = new LockTask.Thread1(lockTask);
        LockTask.Thread2 thread2 = new LockTask.Thread2(lockTask);
        LockTask.Thread3 thread3 = new LockTask.Thread3(lockTask);

        thread1.setName("thread1");
        thread2.setName("thread2");
        thread3.setName("thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
