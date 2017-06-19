package com.lujiahao.thread;

/**
 * 模拟多线程卖票
 * 会出现相同的数和负数
 * Created by ljh on 2017/1/15.
 */
public class TicketThreadTest {
    public static void main(String[] args){
        TicketThread t1 = new TicketThread();
        TicketThread t2 = new TicketThread();
        TicketThread t3 = new TicketThread();
        TicketThread t4 = new TicketThread();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t4.setName("窗口4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
