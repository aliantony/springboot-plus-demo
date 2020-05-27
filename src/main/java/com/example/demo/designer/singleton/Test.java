package com.example.demo.designer.singleton;

import java.lang.reflect.Constructor;

/**
 * @author wangqian
 * created on 2020-05-07
 * @version 1.0.0
 * @program demo1
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        SingletonDemo s1 = SingletonDemo.getInstance();
        System.out.println(s1);
        Constructor<SingletonDemo> constructor = SingletonDemo.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        SingletonDemo s2 = constructor.newInstance();
        System.out.println(s2);
    }
}
