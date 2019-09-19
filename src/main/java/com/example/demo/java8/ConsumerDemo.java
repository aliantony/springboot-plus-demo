package com.example.demo.java8;

import java.util.function.Consumer;

/**
 * @program demo1
 * @description java8 consumer
 * @author wangqian
 * created on 2019-09-18
 * @version  1.0.0
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consum = s -> System.out.println(s);
        consum.accept("hello");
    }
}
