package com.example.demo.java8;

import java.util.function.Supplier;

/**
 * @program demo1
 * @description java8 consumer
 * @author wangqian
 * created on 2019-09-18
 * @version  1.0.0
 */
public class SupplierDemo {
    public static void main(String[] args) {
        //方法引用调用默认无参构造
        Supplier<Student> supplier = Student::new;
        System.out.println(supplier.get());
    }
}
