package com.lujiahao.stack;

/**
 * Test MyStack
 * Created by ljh on 2017/1/14.
 */
public class TestMyStack {
    public static void main(String[] args){
        MyStack myStack = new MyStack(4);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println("stack is empty : " + myStack.isEmpty());
        System.out.println("stack is full : " + myStack.isFull());

        System.out.println("peek stack top element : " + myStack.peek());

        System.out.print("pop stack all elements : ");
        while (!myStack.isEmpty()){
            System.out.print(myStack.pop() + ",");
        }

        System.out.println("\nstack is empty : " + myStack.isEmpty());
        System.out.println("stack is full : " + myStack.isFull());
    }
}
