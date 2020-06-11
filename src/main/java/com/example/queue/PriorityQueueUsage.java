package com.example.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

@Slf4j
public class PriorityQueueUsage {

    public static void main(String[] args) {
        PriorityQueueUsage u = new PriorityQueueUsage();
        u.usePriorityQueue();
        u.usePriorityQueueWithComparator();
    }

    public void usePriorityQueue(){
        PriorityQueue<Integer> integerQueue = new PriorityQueue<>();

        integerQueue.add(1);
        integerQueue.add(3);
        integerQueue.add(2);

        int first = integerQueue.poll();
        int second = integerQueue.poll();
        int third = integerQueue.poll();

        log.info("{},{},{}",first,second,third);
    }

    public void usePriorityQueueWithComparator(){
        PriorityQueue<Integer> integerQueue = new PriorityQueue<>((a,b)-> b-a);
        integerQueue.add(1);
        integerQueue.add(3);
        integerQueue.add(2);

        int first = integerQueue.poll();
        int second = integerQueue.poll();
        int third = integerQueue.poll();

        log.info("{},{},{}",first,second,third);
    }

}