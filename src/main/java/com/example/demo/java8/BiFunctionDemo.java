package com.example.demo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-18
 * @desc 输入两个参数，返回一个参数
 * @version  1.0.0
 */
public class BiFunctionDemo {

    public static void main(String[] args) {
        BiFunctionDemo demo = new BiFunctionDemo();
        List<Student> list = new ArrayList<>();
        demo.getStudentByAge(30, list, (age, students) -> list.stream().filter(s -> s.getAge() > age).collect(Collectors.toList()));
    }

    private List<Student> getStudentByAge(int age, List<Student> sttudents, BiFunction<Integer, List<Student>, List<Student>> fun) {
        return fun.apply(age, sttudents);
    }
}
