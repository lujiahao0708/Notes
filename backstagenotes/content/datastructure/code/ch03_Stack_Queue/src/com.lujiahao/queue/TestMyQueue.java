package com.lujiahao.queue;

/**
 * Created by ljh on 2017/1/14.
 */
public class TestMyQueue {
    public static void main(String[] args){
        MyCircularQueue myQueue = new MyCircularQueue(4);
        myQueue.insert(1);
        myQueue.insert(2);
        myQueue.insert(3);
        myQueue.insert(4);

        System.out.println("queue is empty : " + myQueue.isEmpty());
        System.out.println("queue is full : " + myQueue.isFull());

        System.out.println("peek element from queue header : " + myQueue.peek());

        System.out.print("remove all elements from queue : ");
        while (!myQueue.isEmpty()){
            System.out.print(myQueue.remove() + ",");
        }
        System.out.println();

        System.out.println("insert new elements into queue");
        myQueue.insert(5);
        myQueue.insert(6);
        myQueue.insert(7);
        myQueue.insert(8);
        myQueue.insert(9);
        System.out.print("remove all elements from queue : ");
        while (!myQueue.isEmpty()){
            System.out.print(myQueue.remove() + ",");
        }


    }
}
