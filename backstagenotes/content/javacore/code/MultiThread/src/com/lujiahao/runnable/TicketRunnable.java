package com.lujiahao.runnable;

/**
 * Created by ljh on 2017/1/15.
 */
public class TicketRunnable implements Runnable {
    private int tickets = 200;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出第" + tickets-- + "张票");
            }
        }
    }
}
