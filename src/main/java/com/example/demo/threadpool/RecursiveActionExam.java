package com.example.demo.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 这段代码的目的是计算一个大型数组中每个元素x的一个公式的值，这个公式是sin(x)+cos(x)+tan(x)
 * forkjoinpool采用了任务偷取的算法，work-steal，一开始各个线程都有一个任务队列，
 * 都从任务队列的头部取任务执行，当有线程没有任务执行的时候，从别的线程的任务队列
 * 尾部取任务执行，从而提升了性能
 */
public class RecursiveActionExam {
    private final static int NUMBER = 10000000;

    public static void main(String[] args) {
        double[] array = new double[NUMBER];
        for (int i = 0; i < NUMBER; i++) {
            array[i] = i;
        }
        long startTime = System.currentTimeMillis();
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new ComputeTask(array, 0, array.length));
        long endTime = System.currentTimeMillis();
        System.out.println("Time span = " + (endTime - startTime));
    }
}

/**
 * RecursiveAction 无返回结果的任务
 * RecursiveTask 有返回结果的任务
 */
class ComputeTask extends RecursiveAction {
    final double[] array;
    final int lo, hi;

    ComputeTask(double[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (hi - lo < 2) {
            //小于2直接计算
            for (int i = lo; i < hi; ++i)
                array[i] = Math.sin(array[i]) + Math.cos(array[i]) + Math.tan(array[i]);
        } else {
            //>>按位右移，符号位不变,符号位不变，适用于要考虑负数的情况
            //>>>无符号右移，忽略符号位，空位都以0补齐
            int mid = (lo + hi) >>> 1;
            invokeAll(new ComputeTask(array, lo, mid),
                    new ComputeTask(array, mid, hi));
        }
    }
}