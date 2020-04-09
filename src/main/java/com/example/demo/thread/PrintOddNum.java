package com.example.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program demo1
 * @description 两个线程交替打印1-100的奇偶数
 * @author wangqian
 * created on 2020-04-09
 * @version  1.0.0
 */
public class PrintOddNum {
    private static volatile boolean flag = false;
    private static final Integer TOTAL = 100;
    private static AtomicInteger num = new AtomicInteger();
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        long start =  System.currentTimeMillis();
       Thread t1 = new Thread(() -> {
           while (num.get() < TOTAL) {
               if (!flag) {
                   System.out.println("奇数：" + num.incrementAndGet());
                   flag = true;
               }
           }
           latch.countDown();
       });
       t1.setName("奇数线程");
       Thread t2 = new Thread(() -> {
           while (num.get() < TOTAL) {
               if (flag) {
                   System.out.println("偶数：" + num.incrementAndGet());
                   flag = false;
               }
           }
           latch.countDown();
       });
       t2.setName("偶数线程");
       t1.start();
       t2.start();
       latch.await();
       long end =  System.currentTimeMillis();
       System.out.println("一共耗时："+(end - start) + "ms");
    }
}
