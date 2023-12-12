package com.example.javaprinciples.handler;

public class Handler {
    private Looper mLooper;

    public Handler() {
        this.mLooper = Looper.getMainLooper();
    }

    public void sendMessage(Message message) {
        message.when = System.currentTimeMillis();
        message.target = this;
        mLooper.getmQueue().enqueueMessage(message);
    }

    public void post(Runnable runnable) {
        Message message = Message.obtain();
        message.callback = runnable;
        message.when = 0;
        message.what = null;
        sendMessage(message);
    }

    public void dispatchMessage(Message msg) {
        if (msg.callback != null) { //postMessage类型
            msg.callback.run();
        } else {
            msg.target.handleMessage(msg);  //sendMessage类型
        }
    }

    public void handleMessage(Message msg) {

    }

    public Looper getLooper() {
        return mLooper;
    }

    public void setLooper(Looper looper) {
        mLooper = looper;
    }
}
