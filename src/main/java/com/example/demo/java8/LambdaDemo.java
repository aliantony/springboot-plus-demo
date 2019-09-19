package com.example.demo.java8;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-19
 * @version  1.0.0
 */
public class LambdaDemo {

    public static void main(String[] args) {
        Function<String, Integer> fun = (String s) -> s.length();
        System.out.println(fun.apply("309"));
        Predicate<Student> fun1 = (Student s) -> s.getAge() > 18;
        fun1.test(new Student());
        Runnable r2 = () -> System.out.println("hello");
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello word");
        Supplier<String> stu = () -> "123";
        System.out.println(stu.get());
        Function<String, Integer> fun2 = Integer::parseInt;
        fun2.apply("3333");
        BiFunction<String, Integer, Character> biFunction = String::charAt;
        biFunction.apply("abcdefghijklmn", 3);
        String var = "hello word";
        //第一个参数已经确定是var,此处是String的实例对象
        Function<Integer, Character> fun3 = var::charAt;
        fun3.apply(3);
        //存在两个入参的构造函数方法引用
        BiFunction<String, Integer, Student> fun4 = Student::new;
        fun4.apply("zhangsan", 33);
        //自定义函数式接口支持3个参数
        MyFunction<String, Integer, Integer, Student> fun5 = Student::new;
        fun5.apply("lisi", 30, 30);
        List<Student> list = new ArrayList<>();
        list.sort(Comparator.comparing(Student::getScore));
    }
}
