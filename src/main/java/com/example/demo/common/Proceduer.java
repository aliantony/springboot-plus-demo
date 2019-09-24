package com.example.demo.common;

import java.util.concurrent.BlockingQueue;

/**
 * @program demo1
 * @description 生产者
 * @author wangqian
 * created on 2019-09-24
 * @version  1.0.0
 */
public class Proceduer implements Runnable {

    private BlockingQueue<String> queue;

    public Proceduer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            queue.put("hello");
            System.out.println("生产者生产数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}