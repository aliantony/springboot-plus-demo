package com.example.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-24
 * @version  1.0.0
 */
public class DeadLockDemo1 {
    private final static Lock lock1 = new ReentrantLock();
    private final static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 =  new Thread(() -> {
                try {
                    lock1.lock();
                    System.out.println("持有锁1，等待锁2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock2.lock();
                    System.out.println("成功获取到锁2");
                } finally {
                    lock2.unlock();
                    lock1.unlock();
                }

        });
        Thread t2 =  new Thread(() -> {
            try {
                lock2.lock();
                System.out.println("持有锁2，等待锁1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lock();
                System.out.println("成功获取到锁1");
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });
        t1.start();
        t2.start();
    }
}
