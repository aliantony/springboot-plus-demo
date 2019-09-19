package com.example.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-18
 * @desc 你所使用的Lambda表达式的方法体恰好有一个匹配的方法完成相同的功能，
 * 这时候就可以将Lambda表达式替换成方法引用，除此之外是无法用方法引用替换Lambda表达式的，
 * 所以说Lambda表达式是一种更为通用的方式，而方法引用是需要满足一定的条件才能够使用的
 * 类名::静态方法名
 * 类名::实例方法名 用this调用，少传一个参数
 * 引用名/实例对象::实例方法名
 * @version  1.0.0
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "word", "test");
        list.forEach(System.out::println);
        Student s1 = new Student("zhangsan" ,22);
        s1.setScore(80);
        Student s2 = new Student("zhangsan1" ,15);
        s2.setScore(70);
        Student s3 = new Student("zhangsan2" ,33);
        s3.setScore(90);
        List<Student> students = Arrays.asList(s1, s2, s3);
        students.sort((st1, st2) -> Student.comparedStudentByName(st1, st2));
        students.sort(Student::comparedStudentByScore);
        //如上原始写法的第一个参数作为this即调用者，第二个就作为参数
        students.sort(Student::comparedStudent);
        MethodReferenceDemo demo = new MethodReferenceDemo();
        //调用无参构造器
        System.out.println(demo.getString(String::new));
        //符合有参数的构造器，接收一个参数，返回一个参数，hello作为参数传给String构造器
        System.out.println(demo.getString2("hello", String::new));
    }

    private String getString(Supplier<String> supplier) {
        return supplier.get() + "test";
    }

    private String getString2(String str, Function<String, String> function) {
        return function.apply(str);
    }
}
