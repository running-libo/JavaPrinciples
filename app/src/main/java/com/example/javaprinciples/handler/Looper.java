package com.example.javaprinciples.handler;

public class Looper {

    public static ThreadLocal<Looper> threadLocal;
    private MessageQueue mQueue;
    private volatile boolean nativeEpoll = false;

    private static Looper mMainLooper;

    static {
        threadLocal = new ThreadLocal<>();
    }

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static Looper getLooper() {
        return threadLocal.get();
    }

    public static Looper getMainLooper() {
        return mMainLooper;
    }

    public MessageQueue getmQueue() {
        return mQueue;
    }

    public static void prepare() {
        if (threadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }

        threadLocal.set(new Looper());
    }

    public static void prepareMain() {
        prepare();
        mMainLooper = getLooper();
    }

    public static void loop() {
        final Looper me = threadLocal.get();
        if (me == null) {
            throw new Error("No Looper; Looper.prepare() wasn't called on this thread.");
        }

        MessageQueue queue = me.mQueue;

        for(;;) {
            //取出消息进行分发
            Message message = queue.next();
            if (message != null) {
                try {
                    message.target.dispatchMessage(message);
                    //处理完消息，放入缓存池中
                    Message.addCache(message);
                } catch (Exception exception) {

                }
            }

            //退出循环取消息
            if (me.nativeEpoll) {
                return;
            }
        }
    }

    public void setNativeEpoll() {
        nativeEpoll = true;
    }

}
