package com.example.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @program demo1
 * @description 条件lambda
 * @author wangqian
 * created on 2019-09-18
 * @desc 函数式编程是传递行为，而传统的编程是传递值。
 * @version  1.0.0
 */
public class PredicateDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        PredicateDemo demo = new PredicateDemo();
        demo.conditonFilter(list, x-> x > 5);
        demo.conditonFilter(list, x-> x < 3);
        demo.conditonFilter(list, x-> x % 2 == 0);
        demo.conditonFilter(list, x-> x % 2 != 0);
        demo.conditonFilter(list, x -> true);
        demo.conditonFilter2(list, x -> x > 5, x -> x % 2 == 0);
    }

    private void conditonFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private void conditonFilter2(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate1) {
        for (Integer integer : list) {
            if (predicate.and(predicate1).test(integer)) {
                System.out.println(integer);
            }
        }
    }
}
