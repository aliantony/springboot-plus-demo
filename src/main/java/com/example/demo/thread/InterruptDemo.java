package com.example.demo.thread;

public class InterruptDemo {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!interrupted()) {
                System.out.println(i);
                 ++i;
            }
            System.out.println("<子线程>Thread end, i = " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(10);

        System.out.println("<主线程> before interrupt");
        thread.interrupt();
        System.out.println("<主线程> after interrupt");

        /* 输出
        <主线程> before interrupt
        <主线程> after interrupt
        <子线程>Thread end, i = 5461458
         */
    }
}