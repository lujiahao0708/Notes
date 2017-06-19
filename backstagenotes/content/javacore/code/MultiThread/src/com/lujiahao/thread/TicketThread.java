package com.lujiahao.thread;

/**
 * Created by ljh on 2017/1/15.
 */
public class TicketThread extends Thread {
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tickets > 0) {
                System.out.println(getName() + "正在出第" + tickets-- + "张票");
            }
        }
    }
}
