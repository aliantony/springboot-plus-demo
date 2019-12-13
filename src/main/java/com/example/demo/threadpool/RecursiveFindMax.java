package com.example.demo.threadpool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveFindMax {
    //不设置种子随机性更强一些，每次生成的随机数序列不同
    //设置了种子，则每次生成的随机数序列是一样的，称为伪随机数
    private static Random rand = new Random(47);
    private static final int NUMBER = 1000000;

    public static void main(String[] args) {
        double[] array = new double[NUMBER];
        for (int i = 0; i < NUMBER; i++) {
            array[i] = rand.nextDouble();
        }

        ForkJoinPool pool = new ForkJoinPool();
        TaskFindMax task = new TaskFindMax(0, array.length - 1, array);
        pool.invoke(task);
        System.out.println("MaxValue = " + task.join());
    }
}

class TaskFindMax extends RecursiveTask<Double> {
    private final int lo;
    private final int hi;
    private final double[] array;
    //you can change THRESHOLD to get better efficiency
    private final static int THRESHOLD = 10;

    TaskFindMax(int lo, int hi, double[] array) {
        this.lo = lo;
        this.hi = hi;
        this.array = array;
    }

    @Override
    protected Double compute() {
        if ((hi - lo) < THRESHOLD) {
            double max = array[lo];
            for (int i = lo; i < hi; i++) {
                max = Math.max(max, array[i + 1]);
            }
            return max;
        } else {
            int mid = (lo + hi) >>> 1;
            TaskFindMax lhs = new TaskFindMax(lo, mid, array);
            TaskFindMax rhs = new TaskFindMax(mid, hi, array);
            invokeAll(lhs, rhs);
            return Math.max(lhs.join(), rhs.join());
        }
    }
}