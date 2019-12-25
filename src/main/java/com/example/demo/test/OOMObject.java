package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 发生oomJVM不退出的情况，因为只是子线程死了，主线程依然存活
 */
public class OOMObject {
    // 为快速发生oom，设置堆大小; VM args: -Xms20m -Xmx20m
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            List<OOMObject> list = new ArrayList<>();
            while (true) {
                list.add(new OOMObject());
            }
        }).start();

        /*while (true) {
            System.out.println(Thread.currentThread().getName() + " continuing...");
            Thread.sleep(1000L);
        }*/
    }
}
