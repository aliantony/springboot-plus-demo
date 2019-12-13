package com.example.demo.threadpool;

public class RecursiveSequenceExam {
    private final static int NUMBER = 10000000;

    public static void main(String[] args) {
        double[] array = new double[NUMBER];
        for (int i = 0; i < NUMBER; i++) {
            array[i] = i;
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUMBER; i++) {
            array[i] = Math.sin(array[i]) + Math.cos(array[i]) + Math.tan(array[i]);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time span = " + (endTime - startTime));
    }
}