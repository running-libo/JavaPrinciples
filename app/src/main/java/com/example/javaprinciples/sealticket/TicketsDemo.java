package com.example.javaprinciples.sealticket;


public class TicketsDemo {

    public static void test() {
        MyRunnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable, "窗口一");
        Thread t2 = new Thread(runnable, "窗口二");
        Thread t3 = new Thread(runnable, "窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
