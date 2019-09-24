package com.example.demo.common;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program demo1
 * @description 客户端
 * @author wangqian
 * created on 2019-09-24
 * @version  1.0.0
 */
public class Client {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        Proceduer proceduer = new Proceduer(queue);
        Consumer consumer = new Consumer(queue);
        for (int i = 0; i < 10 ; i++) {
            new Thread(proceduer, "生产者" + i).start();
        }

        for (int i = 0; i < 10 ; i++) {
            new Thread(consumer, "消费者" + i).start();
        }
    }
}
