package com.example.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * 处于运行期且非阻塞的状态的线程，这种情况下，
 * 直接调用Thread.interrupt()中断线程是不会得到任响应的
 * 因为处于非阻塞状态的线程需要我们手动进行中断检测并结束程序
 */
public class InterruputThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("未被中断");
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

    }
}