package com.lujiahao.queue;

/**
 * 这种是有缺陷的  不太好
 * simulation a circlar queue by an array
 * Created by ljh on 2017/1/14.
 */
public class MyCircularQueue {
    // underlying implements is an array
    private long[] arr;
    // effective data size
    private int elements;
    // queue header
    private int front;
    // queue tail
    private int end;

    /**
     * constructor without parameter
     */
    public MyCircularQueue(){
        this(10);
    }

    /**
     * constructor with a parameter that define array size
     * @param maxSize the maxsize of array
     */
    public MyCircularQueue(int maxSize){
        arr = new long[maxSize];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * insert an element from the end of the queue
     * @param value
     */
    public void insert(long value){
        if (end == arr.length -1) {
            end = -1;
        } else {
            arr[++end] = value;
            elements++;
        }
    }

    /**
     * remove an element from the queue header
     * @return
     */
    public long remove(){
        long value = arr[front++];
        if (front == arr.length) {
            front = 0;
        }
        elements--;
        return value;
    }

    /**
     * peek element from the queue header
     * @return
     */
    public long peek(){
        return arr[front];
    }

    /**
     * check whether the stack is empty
     * @return
     */
    public boolean isEmpty(){
        return 0 == elements;
    }

    /**
     * check whether the stack is full
     * @return
     */
    public boolean isFull(){
        return elements == arr.length;
    }
}
