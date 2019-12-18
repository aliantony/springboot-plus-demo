package com.example.demo.currentqueue;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueExam {
    public static void main(String[] args) {
        //创建一个初始容量为3，排序为字符串排序相反的队列
        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<String>(3, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.compareTo(o2) < 0) {
                    return 1;
                } else if (o1.compareTo(o2) > 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.submit(new Producer("Producer" + i, queue));
        }
        for (int i = 0; i < 5; i++) {
            service.submit(new Consumer("Consumer" + i, queue));
        }
        service.shutdown();
    }

    static class Producer implements Runnable {
        private final String name;
        private final PriorityBlockingQueue<String> queue;
        private static Random rand = new Random(System.currentTimeMillis());

        Producer(String name, PriorityBlockingQueue<String> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String str = "Product" + rand.nextInt(1000);
                //由于队列容量没有上限，指定容量满后会扩容，所以不会阻塞
                queue.put(str);
                System.out.println("->" + name + " put " + str);
                try {
                    TimeUnit.SECONDS.sleep(rand.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " is over");
        }
    }


    static class Consumer implements Runnable {
        private final String name;
        private final PriorityBlockingQueue<String> queue;
        private static Random rand = new Random(System.currentTimeMillis());

        Consumer(String name, PriorityBlockingQueue<String> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    //会阻塞
                    String str = queue.take();
                    System.out.println("<-" + name + " take " + str);
                    TimeUnit.SECONDS.sleep(rand.nextInt(5));
                }
                System.out.println(name + " is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}