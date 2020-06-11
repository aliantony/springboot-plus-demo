package com.example.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program demo1
 * @description 
 * @author wq
 * created on 2020-06-11
 * @version  1.0.0
 */

/**
 * 生产一个消费一个
 * 生产者可以调用这个transfer方法，从而等待消费者调用take或者poll方法从Queue中拿取数据
 */
public class TransferQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
        ExecutorService exService = Executors.newFixedThreadPool(10);
        Producer producer = new Producer(transferQueue, "ProducerOne", 5);
        Consumer consumer = new Consumer(transferQueue, "ConsumerOne", 2);
        exService.execute(producer);
        exService.execute(consumer);

        exService.awaitTermination(50000, TimeUnit.MILLISECONDS);
        exService.shutdown();
    }
}

@Slf4j
@Data
@AllArgsConstructor
class Producer implements Runnable {
    private TransferQueue<String> transferQueue;

    private String name;

    private Integer messageCount;

    public static final AtomicInteger messageProduced = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                boolean added = transferQueue.tryTransfer( "第"+i+"个", 2000, TimeUnit.MILLISECONDS);
                log.info("transfered {} 是否成功: {}","第"+i+"个",added);
                if(added){
                    messageProduced.incrementAndGet();
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }
        log.info("total transfered {}",messageProduced.get());
    }
}

@Slf4j
@Data
@AllArgsConstructor
class Consumer implements Runnable {

    private TransferQueue<String> transferQueue;

    private String name;

    private int messageCount;

    public static final AtomicInteger messageConsumed = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                String element = transferQueue.take();
                log.info("take {}",element );
                messageConsumed.incrementAndGet();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }
        log.info("total consumed {}",messageConsumed.get());
    }

}
