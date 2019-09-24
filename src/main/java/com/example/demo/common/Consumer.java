package com.example.demo.common;

import java.util.concurrent.BlockingQueue;

/**
 * @program demo1
 * @description 消费者
 * @author wangqian
 * created on 2019-09-24
 * @version  1.0.0
 */
public class Consumer implements Runnable {

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
