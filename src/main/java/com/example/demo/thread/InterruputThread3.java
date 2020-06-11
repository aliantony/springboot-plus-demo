package com.example.demo.thread;

import java.util.concurrent.TimeUnit;

public class InterruputThread3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    // 判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
                    while (!Thread.interrupted()) {
                        System.out.println("服务中...");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("中断");
                    // 中断状态被复位
                    System.out.println("interrupt:" + this.isInterrupted());
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
    }
}