package com.lujiahao.stack;

/**
 * simulation a stack by an array
 * Created by ljh on 2017/1/14.
 */
public class MyStack {
    // underlying implements is an array
    private long[] arr;
    // the cursor for push&pop
    private int top;

    /**
     * constructor without parameter
     */
    public MyStack() {
        arr = new long[10];
        top = -1;
    }

    /**
     * constructor with a parameter that define array size
     *
     * @param maxSize the maxsize of array
     */
    public MyStack(int maxSize) {
        arr = new long[maxSize];
        top = -1;
    }

    /**
     * push element into stack
     *
     * @param value element need to add
     */
    public void push(int value) {
        arr[++top] = value;
    }

    /**
     * pop element outof stack
     * @return stack top element
     */
    public long pop(){
        return arr[top--];
    }

    /**
     * peek stack top element
     * @return
     */
    public long peek(){
        return arr[top];
    }

    /**
     * check whether the stack is empty
     * @return
     */
    public boolean isEmpty(){
        return -1 == top;
    }

    /**
     * check whether the stack is full
     * @return
     */
    public boolean isFull(){
        return arr.length -1 == top;
    }
}
