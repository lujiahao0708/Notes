package com.lujiahao.queue;

/**
 * 循环队列的顺序存储结构（数组实现）
 * Created by ljh on 2017/1/14.
 */
public class LoopQueue<T> {
    private Object[] arr; // 对象数组，队列最多存储
    private int front; // 记住队首下标位置
    private int rear; // 记住队尾下标的下一个位置
    private static final int DEFAULT_SIZE = 3; // 定义数组默认长度

    public LoopQueue() {
        this(DEFAULT_SIZE);
    }

    public LoopQueue(int size) {
        arr = new Object[size];
        front = 0;
        rear = 0;
    }

    // 将元素追加到队列尾部
    public boolean enqueue(T data) {
        if ((rear + 1) % arr.length == front) { // 判断队列是否已满
            System.out.println("队列已满,无法入队..");
            return false;
        }
        arr[rear] = data; // 将元素data赋值到队尾
        System.out.println(data + "入队..");
        rear = (rear + 1) % arr.length; // real下标向后移一位,若到最后则转到数组头部
        return true;
    }

    // 队列头部的第一个元素出队
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (rear == front) { // 判断队列是否为空
            System.out.println("队列已空,无法出队..");
            return null;
        }
        Object data = arr[front];
        System.out.println(data + "出队..");
        front = (front + 1) % arr.length; // front下标向后移一位,若到最后则转到数组头部
        return (T) data;
    }

    // 获取队列中元素个数
    public int size() {
        return (rear - front + arr.length) % arr.length;
    }

    public static void main(String[] args) {
        LoopQueue queue = new LoopQueue();
        queue.enqueue("11");
        queue.dequeue();
        queue.enqueue("22");
        queue.enqueue("33");
        queue.enqueue("44");
        queue.dequeue();
        queue.enqueue("55");
        queue.dequeue();
        queue.enqueue("66");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("队列中元素个数为: " + queue.size());

    }
}
