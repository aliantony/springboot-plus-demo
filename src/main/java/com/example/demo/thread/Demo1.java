package com.example.demo.thread;
/**
 * @program demo1
 * @description 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10,
 * 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75
 * @author wangqian
 * created on 2020-04-10
 * @version  1.0.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Object o = new Object();
      Thread t1 = new Thread(new PrintRunnable(o, 1));
      Thread t2 = new Thread(new PrintRunnable(o, 2));
      Thread t3 = new Thread(new PrintRunnable(o, 3));
      t1.start();
      t2.start();
      t3.start();
    }
}
class PrintRunnable implements Runnable{
    private final Object o;
    private int threadId;
    private static volatile int num = 1;

    PrintRunnable(Object o, int threadId){
        this.o = o;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while(num < 65){
            synchronized (o){
                while(num/5%3 + 1 != threadId){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                System.out.print("线程"+threadId+": ");
                for(int i=0; i<5; i++, num++) {
                    System.out.print(num + ",");
                }
                System.out.println();

                o.notifyAll();
            }
        }
    }
}

