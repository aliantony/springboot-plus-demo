package com.example.demo.classload;

/**
 * @program demo1
 * @description 外部类的初始化不回导致静态内部类的初始化
 * @author wangqian
 * created on 2020-05-07
 * @version  1.0.0
 */
public class StaticInnerClass {
    private static int a = 1;
    private static class Inner {
        static {
            System.out.println("Inner loading ...");
        }
    }
    public StaticInnerClass() {
        System.out.println("执行构造函数初始化类");
    }
    public static void main(String[] args) {
        System.out.println(StaticInnerClass.a);
    }
}
