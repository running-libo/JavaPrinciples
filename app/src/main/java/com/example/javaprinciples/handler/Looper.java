package com.example.javaprinciples.handler;

public class Looper {

    public static ThreadLocal<Looper> threadLocal;
    private MessageQueue messageQueue = new MessageQueue();
    private volatile boolean quit = false;

    private static Looper mMainLooper;
    private Message mMessage;

    static {
        threadLocal = new ThreadLocal<>();
    }

    private Looper() {

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
        if (threadLocal.get() != null) {
            throw new Error("current thread already has prepared!");
        }

        threadLocal.set(new Looper());
    }

    public static void prepareMain() {
        prepare();
        mMainLooper = getLooper();
    }

    public static void loop() {
        Looper looper = threadLocal.get();
        if (looper == null) {
            throw new Error("please prepare first!");
        }

        for(;;) {
            //取出消息进行分发
            Message message = looper.messageQueue.next();
            if (message != null) {
                message.target.handleMessage(message);

                //处理完消息，放入缓存池中
                Message.addCache(message);
            } else {

            }
        }
    }

}
