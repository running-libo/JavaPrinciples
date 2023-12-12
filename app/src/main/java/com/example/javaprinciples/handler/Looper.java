package com.example.javaprinciples.handler;

public class Looper {

    public static ThreadLocal<Looper> threadLocal;
    private MessageQueue messageQueue = new MessageQueue();
    private volatile boolean quit = false;

    private static Looper mMainLooper;

    static {
        threadLocal = new ThreadLocal<>();
    }

    public static Looper getLooper() {
        return threadLocal.get();
    }

    public static Looper getMainLooper() {
        return mMainLooper;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    public static void prepare() {

    }

    public static void prepareMain() {

    }

    public static void loop() {

    }

}
