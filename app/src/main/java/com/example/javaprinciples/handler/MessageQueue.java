package com.example.javaprinciples.handler;

public class MessageQueue {

    private int size;

    /**
     * 消息队列的有序队列
     */
    private static class Node {
        Node pre;
        Node next;
        Message message;

        public Node(Message message) {
            this.message = message;
        }
    }
}
