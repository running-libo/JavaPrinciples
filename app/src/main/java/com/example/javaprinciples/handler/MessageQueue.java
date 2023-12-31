package com.example.javaprinciples.handler;

public class MessageQueue {

    /**
     * 队列大小
     */
    private int size;
    /**
     * 队列头节点
     */
    private Node head;
    /**
     * 队列尾节点
     */
    private Node tail;

    public MessageQueue() {
        size = 0;
        head = null;
        tail = null;
    }

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

    private boolean insertToLinkTail(Node node) {
        if (head == null) {
            head = tail = node;
            return true;
        }

        Node p = head;
        while (p != null) {
            if (p.message.when > node.message.when) {
                break;
            }
            p = p.next;
        }
        if (p == head) {
            //头部
            p.pre = node;
            node.next = p;
            head = node;
            return true;
        } else if (p == null) {
            //尾部
            tail.next = node;
            node.pre = tail;
            tail = node;
            return true;
        } else {
            //中间
            Node pre = p.pre;
            pre.next = node;
            node.next = p;
            node.pre = pre;
            p.pre = node;
            return true;
        }
    }

    /**
     * 每次获取头结点
     * @return
     */
    private Node removeFromLinkHead() {
        if (head == null) return null;

        Node p = head;
        Node newHead = head.next;  //第二个节点
        if (newHead == null) {
            head = tail = null;  //将唯一节点取完，头尾节点为空
        } else {
            newHead.pre = null;
            head = newHead; //取出头节点，newHead成新的头节点
        }
        return p;
    }

    /**
     * 添加消息到 MessageQueue 中
     * @param message
     * @return
     */
    public boolean enqueueMessage(Message message) {
        synchronized (this) {
            if (insertToLinkTail(new Node(message))) {
                size++;
                return true;
            }
        }
        return false;
    }

    /**
     * 取出头节点并返回message
     * @return
     */
    public Message next() {
        synchronized (this) {
            Node node = removeFromLinkHead();
            if (node == null) {
                return null;
            }
            size--;
            return node.message;
        }
    }

    public int getSize() {
        synchronized (this) {
            return size;
        }
    }
}
