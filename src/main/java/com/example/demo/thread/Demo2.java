package com.example.demo.thread;
/**
 * @program demo1
 * @description 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10,
 * 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75
 * @author wangqian
 * created on 2020-04-10
 * @version  1.0.0
 */
public class Demo2 {
    //同步块内不用加volitle
    private  static Integer flag = 0;
    private static final Integer total = 75;
    private static final Object lock = new Object();
    private static Integer i = 0;
    public static void main(String[] args) {
      Thread t1 = new Thread(() -> {
          synchronized (lock) {
              while (i < total) {
                  if (flag == 0) {
                      for (int j = 0; j < 5; j++) {
                          System.out.println(Thread.currentThread().getName() + (++i));
                      }
                      flag = 1;
                  }
                  lock.notifyAll();
                  try {
                      if (i >= total) {
                          System.exit(0);
                      }
                      lock.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      });
      t1.setName("线程1：");
      Thread t2 = new Thread(() -> {
          synchronized (lock) {
              while (i < total) {
                  if (flag == 1) {
                      for (int j = 0; j < 5; j++) {
                          System.out.println(Thread.currentThread().getName() + (++i));
                      }
                      flag = 2;
                  }
                  lock.notifyAll();
                  try {
                      if (i >= total) {
                          System.exit(0);
                      }
                      lock.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      });
      t2.setName("线程2：");
      Thread t3 = new Thread(() -> {
          synchronized (lock) {
              while (i < total) {
                  if (flag == 2) {
                      for (int j = 0; j < 5; j++) {
                          System.out.println(Thread.currentThread().getName() + (++i));
                      }
                      flag = 0;
                  }
                  lock.notifyAll();
                  try {
                      if (i >= total) {
                          System.exit(0);
                      }
                      lock.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      });
      t3.setName("线程3：");
      t1.start();
      t2.start();
      t3.start();
    }
}


