package com.example.demo.java8;
import	java.util.Comparator;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-18
 * 参考文献https://www.cnblogs.com/webor2006/p/8243874.html
 * @version  1.0.0
 */
public class FunctionDemo {

    public static void main(String[] args) {
        FunctionDemo demo = new FunctionDemo();
        System.out.println(demo.compute3(2, 3, (a, b) -> a + b));
        System.out.println(demo.compute4(2, 3, (a, b) -> a + b));
        System.out.println(demo.getMin("hello123", "word", (a, b) -> a.length() - b.length()));
        //组合
        System.out.println(demo.compute(2, s -> s*3, s -> s * s));
    }

    private int compute(int a, Function<Integer, Integer> fun1, Function<Integer, Integer> fun2) {
        return fun1.compose(fun2).apply(a);
    }

    private int compute3(int a, int b, BiFunction<Integer, Integer, Integer> fun1) {
        return fun1.apply(a, b);
    }


    private int compute4(int a, int b, BinaryOperator<Integer> fun1) {
        return fun1.apply(a, b);
    }

    private String getMin(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(a, b);
    }
}
