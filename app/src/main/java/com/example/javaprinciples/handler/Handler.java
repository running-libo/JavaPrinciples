package com.example.javaprinciples.handler;

public class Handler {

    private Message message;
    private Looper mLooper;

//    public Handler() {
//        Looper looper = Looper.threadLocal.get();
//        if (looper == null) {
//            throw new Error("looper not exist");
//        }
//        this.mLooper = looper;
//    }

    public Handler() {
        this.mLooper = Looper.getMainLooper();
    }

    public void sendMessage(Message message) {
        message.when = System.currentTimeMillis();
        message.target = this;
        mLooper.getMessageQueue().enqueueMessage(message);
    }

    protected void handleMessage(Message message) {

    }

    public Looper getLooper() {
        return mLooper;
    }

    public void setLooper(Looper looper) {
        mLooper = looper;
    }
}
