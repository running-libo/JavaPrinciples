package com.example.javaprinciples.handler;

public class Handler {
    private Looper mLooper;

    public Handler() {
        this.mLooper = Looper.getMainLooper();
    }

    public void sendMessage(Message message) {
        message.when = System.currentTimeMillis();
        message.target = this;
        mLooper.getMessageQueue().enqueueMessage(message);
    }

    public void post(Runnable runnable) {
        Message message = Message.obtain();
        message.callback = runnable;
        message.when = 0;
        message.what = null;
        sendMessage(message);
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
