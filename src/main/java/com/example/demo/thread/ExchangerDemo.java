package com.example.demo.thread;

import java.util.concurrent.Exchanger;

/**
 * @author wangqian
 * created on 2020-04-16
 * @version 1.0.0
 * @program demo1
 * @description 两个线程在预设点交换变量，先到达的等待对方。两个人扫地，第一个人扫一半，把扫帚给另一个人扫剩下的
 */
public class ExchangerDemo {

    static Exchanger<Tool> ex = new Exchanger<>();

    public static void main(String[] args) throws Exception {
        new Thread(new Staff("大胖", new Tool("笤帚", "扫地"), ex)).start();
        new Thread(new Staff("小白", new Tool("抹布", "擦桌"), ex)).start();
        synchronized (ExchangerDemo.class) {
            ExchangerDemo.class.wait();
        }
    }

    static class Staff implements Runnable {

        String name;
        Tool tool;
        Exchanger<Tool> ex;

        Staff(String name, Tool tool, Exchanger<Tool> ex) {
            this.name = name;
            this.tool = tool;
            this.ex = ex;
        }

        @Override
        public void run() {
            System.out.println(String.format("%s拿的工具是[%s]，他开始[%s]。。。", name, tool.name, tool.work));
            doingLongTime();
            System.out.println(String.format("%s开始交换工具。。。", name));
            try {
                tool = ex.exchange(tool);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s的工具变为[%s]，他开始[%s]。。。", name, tool.name, tool.work));
        }

        public void doingLongTime() {
            try {
                Thread.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Tool {

        String name;
        String work;

        Tool(String name, String work) {
            this.name = name;
            this.work = work;
        }

    }
}
