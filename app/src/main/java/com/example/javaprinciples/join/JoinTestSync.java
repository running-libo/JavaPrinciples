package com.example.javaprinciples.join;

import static com.example.javaprinciples.MainActivity.TAG;

import android.util.Log;

public class JoinTestSync {

    public static void test() {
        ThreadJoin threadJoin1 = new ThreadJoin("thread1");
        ThreadJoin threadJoin2 = new ThreadJoin("thread2");
        ThreadJoin threadJoin3 = new ThreadJoin("thread3");

        //通过join方法来确保t1、t2、t3的执行顺序

        try {
            threadJoin1.start();
            threadJoin1.join();

            threadJoin2.start();
            threadJoin2.join();

            threadJoin3.start();
            threadJoin3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ThreadJoin extends Thread {
    public ThreadJoin(String name) {
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            Log.i(TAG, this.getName() + ":" + i);
        }
    }
}
