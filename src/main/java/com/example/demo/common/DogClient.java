package com.example.demo.common;

import com.example.entity.Dog;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-29
 * @version  1.0.0
 */
public class DogClient {

    public static void main(String[] args) {
        Dog dog = Dog.of("xiaohui");
        Dog dog1 = Dog.of("xiaoming");
        List<Dog> list = Lists.newArrayList();
        list.add(dog);
        list.add(dog1);
        //不允许，Dog没有实现Comparable接口
        Collections.sort(list);
        list.forEach(System.out::println);
    }

}
