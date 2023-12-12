package com.example.javaprinciples.handler;

import java.util.LinkedList;

public final class Message {

    //Message类处理静态 message 消息池缓存
    private static LinkedList<Message> cache;
    private static final int CACHE_SIZE = 30;

    static {
        cache = new LinkedList<>();
        for (int i=0;i<CACHE_SIZE;i++) {
            cache.addLast(new Message());
        }
    }

    public String what;
    public long when;
    public Object arg1;
    public Object arg2;
    public Handler target;

    /**
     * 获取缓存池消息，不为空从缓存池中取，否则新创建Message
     * @return
     */
    public static Message obtain() {
        if (!cache.isEmpty()) {
            return cache.removeFirst();
        }

        return new Message();
    }

    /**
     * 消息使用完之后添加入缓存池，并且将所持有属性清空
     */
    public static void addCache(Message message) {
        if (message == null) return;

        if (cache.size() >= CACHE_SIZE) return;

        message.when = 0;
        message.what = null;
        message.arg1 = null;
        message.arg2 = null;
        message.target = null;
    }
}
