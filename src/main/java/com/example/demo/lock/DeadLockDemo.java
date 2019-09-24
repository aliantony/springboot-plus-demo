package com.example.demo.lock;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-24
 * @version  1.0.0
 */
public class DeadLockDemo {
    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 =  new Thread(() -> {
            synchronized (lock1) {
                System.out.println("持有锁1，等待锁2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("成功获取到锁2");
                }
            }
        });
        Thread t2 =  new Thread(() -> {
            synchronized (lock2) {
                System.out.println("持有锁2，等待锁1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("成功获取到锁1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
