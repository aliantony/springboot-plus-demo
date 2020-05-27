package com.example.demo.classload;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-05-07
 * @version  1.0.0
 */
public class Test {
    public static void main(String[] args) {
        //常量不回触发类的初始化
        System.out.println(Parent.abc);
        //会触发父类的初始化
        System.out.println(Child.abc);
        System.out.println(Child.gef);
    }
}
