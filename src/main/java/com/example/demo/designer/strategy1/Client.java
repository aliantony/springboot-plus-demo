package com.example.demo.designer.strategy1;
/**
 * @program demo1
 * @description 测试策略模式
 * JDK的线程池拒绝策略用了策略模式
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class Client {
    public static void main(String[] args) {
        PerSon p = new PerSon();
        Way b = new Bike();
        Way d = new Bus();
        p.goHome(b);
        p.goHome(d);
    }
}
