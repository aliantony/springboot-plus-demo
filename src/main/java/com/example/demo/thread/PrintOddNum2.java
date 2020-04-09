package com.example.demo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangqian
 * created on 2020-04-09
 * @version 1.0.0
 * @program demo1 main也是用户线程，不是守护线程， 只有所有的用户线程运行结束，jvm才退出
 * @description 两个线程交替打印1-100的奇偶数,程序运行不会退出，因为偶数线程打印完了没通知奇数线程，奇数线程还阻塞住的
 */
public class PrintOddNum2 {
    public static CountDownLatch latch = new CountDownLatch(2);

    private static Object lock = new Object();

    private static volatile Integer i = 0;
    private static final int TOTAL = 100;


    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (i <= TOTAL) {
                    synchronized (lock) {
                        if (i % 2 == 1 && i <= TOTAL) {
                            System.out.println(Thread.currentThread().getName() + "打印：   " + i++);
                        } else {
                            lock.notifyAll();
                            try {
                                if (i < TOTAL) {
                                    lock.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        };
        thread1.setName("奇数线程");
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (i <= TOTAL) {
                    synchronized (lock) {
                        if (i % 2 == 0) {
                            // 优化，打印完100后通知奇数线程，避免阻塞
                            System.out.println(Thread.currentThread().getName() + "打印：  " + i++);
                            if (i == TOTAL) {
                                lock.notify();
                            }
                        } else {
                            lock.notifyAll();
                            try {
                                if (i < TOTAL) {
                                    lock.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        };
        thread2.setName("偶数线程");
        thread1.start();
        thread2.start();
        System.out.println("程序运行结束");
    }
}

