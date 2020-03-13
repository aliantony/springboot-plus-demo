package com.example.demo.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * park和unpark不同于wait和notify，可以不用在synchronized块内
 * wait和notify有顺序限制，容易死锁，wait必须先于notify调用
 * park和unpark没有先后关系，0或1，一个许可，不能累加
 */
public class LockSupportDemo {

    private static Thread mainThread;

    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ta");
        // 获取主线程
        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" start ta");
        ta.start();
        System.out.println(Thread.currentThread().getName()+" block");
        // 主线程阻塞
        LockSupport.park(mainThread);
        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class ThreadA extends Thread{
        public ThreadA(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" wakup others");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }
}