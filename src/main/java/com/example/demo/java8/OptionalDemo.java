package com.example.demo.java8;

import java.util.Optional;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-18
 * @desc 不要将Optional作为方法参数进行定义，也不要在类中声明Optional类型的变量
 * Optional通常只做为方法的返回值来规避空指针问题
 * @version  1.0.0
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> opt = Optional.of("hello");
        //这样麻烦
        if (opt.isPresent()) {
            System.out.println(opt.get());
        }
        //这样简洁
        opt.ifPresent(System.out::println);
    }
}
