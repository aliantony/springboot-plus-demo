package com.example.demo.classload;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-05-07
 * @version  1.0.0
 */
public class Child extends Parent {
    public static Integer gef = 123;
    static {
        System.out.println("Child is init");
    }
}
