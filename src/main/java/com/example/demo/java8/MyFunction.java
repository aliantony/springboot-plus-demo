package com.example.demo.java8;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-19
 * @version  1.0.0
 * 3个参数的方法引用
 */
@FunctionalInterface
public interface MyFunction<N, A, S, R> {
    R apply(N n, A a, S s);
}
