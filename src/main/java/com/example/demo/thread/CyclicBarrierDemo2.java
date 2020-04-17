package com.example.demo.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author wwj
 * 使用CyclicBarrier(回环栅栏)实现线程按顺序运行
 * 应用场景:公司组织春游,等待所有的员工到达集合地点才能出发，每个人到达后进入barrier状态。都到达后，唤起大家一起出发去旅行。
 * CountDownLatch是一个任务等待其它任务执行完后才执行，CyclicBarrier是等待所有的任务都满足条件再一起执行
 */
public class CyclicBarrierDemo2 {

    static final int COUNT = 5;
    static CyclicBarrier cb = new CyclicBarrier(COUNT, new Singer());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < COUNT; i++) {
            new Thread(new Staff(i, cb)).start();
        }
    }

    static class Singer implements Runnable {

        @Override
        public void run() {
            System.out.println("为大家唱歌。。。");
        }

    }

    static class Staff implements Runnable {

        CyclicBarrier cb;
        int num;

        Staff(int num, CyclicBarrier cb) {
            this.num = num;
            this.cb = cb;
        }

        @Override
        public void run() {
            System.out.println(String.format("员工(%d)出发。。。", num));
            doingLongTime();
            System.out.println(String.format("员工(%d)到达地点一。。。", num));
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("员工(%d)再出发。。。", num));
            doingLongTime();
            System.out.println(String.format("员工(%d)到达地点二。。。", num));
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("员工(%d)再出发。。。", num));
            doingLongTime();
            System.out.println(String.format("员工(%d)到达地点三。。。", num));
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("员工(%d)结束。。。", num));
        }

        public void doingLongTime() {
            try {
                Thread.sleep((long)(Math.random() * 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}