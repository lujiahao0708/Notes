package com.lujiahao.queue;

/**
 * simulation a queue by an array
 * Created by ljh on 2017/1/14.
 */
public class MyQueue {
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
    public MyQueue(){
        this(10);
    }

    /**
     * constructor with a parameter that define array size
     * @param maxSize the maxsize of array
     */
    public MyQueue(int maxSize){
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
        arr[++end] = value;
        elements++;
    }

    /**
     * remove an element from the queue header
     * @return
     */
    public long remove(){
        elements--;
        return arr[front++];
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
