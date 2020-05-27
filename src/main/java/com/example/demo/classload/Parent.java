package com.example.demo.classload;

/**
 * @author wangqian
 * created on 2020-05-07
 * @version 1.0.0
 * @program demo1
 * @description
 */
public class Parent {
    public static int abc = 123;

    static {
        System.out.println("Parent is init");
    }
}


