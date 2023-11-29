package com.example.javaprinciples.objectclasslock;

public class ObjectClassLock2 {

    static class Thread1 extends Thread {
        LockTask lockTask;

        public Thread1(LockTask lockTask) {
            this.lockTask = lockTask;
        }

        @Override
        public void run() {
            lockTask.task1();
        }
    }

    static class Thread2 extends Thread {
        LockTask lockTask;

        public Thread2(LockTask lockTask) {
            this.lockTask = lockTask;
        }

        @Override
        public void run() {
            lockTask.task1();
        }
    }

    public static void test() {
        LockTask lockTask = new LockTask();
        LockTask lockTask2 = new LockTask();
        Thread1 thread1 = new Thread1(lockTask);
        Thread2 thread2 = new Thread2(lockTask2);

        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }
}
