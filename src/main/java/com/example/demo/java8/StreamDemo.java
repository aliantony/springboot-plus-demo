package com.example.demo.java8;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-20
 * @version  1.0.0
 */
public class StreamDemo {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "word", "helloword");
        List<String> result = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        result.forEach(System.out::println);
        //stream.collect(Collectors.toCollection(ArrayList::new));
        //对集合元素排序
        //stream.collect(Collectors.toCollection(TreeSet::new));
        Stream<List<Integer>> stream1 = Stream.of(Arrays.asList(1),Arrays.asList(5),Arrays.asList(11));
        //多个流融合为一个流
        stream1.flatMap(list -> list.stream()).map(e -> e * e).forEach(System.out::println);
        Stream<String> stream2 = Stream.generate(UUID.randomUUID()::toString);
        //System.out.println(stream2.findFirst());
        //stream2.findFirst().ifPresent(System.out::println);
        Stream.iterate(1, x -> x + 2).limit(6).forEach(System.out::println);
        IntStream.rangeClosed(21, 30).forEach(System.out::println);
        List<String> zh = Arrays.asList("hi", "hello", "welcome");
        List<String> xm = Arrays.asList("zhangsan", "li", "wangwu");
        zh.stream().flatMap(e -> xm.stream().map(x -> e + " " + x)).forEach(System.out::println);
        Set<String> set = new HashSet<>(zh);
        Map<String, String> re = set.parallelStream().collect(new MySetToMapCollector<>());
        System.out.println(re);
        StringBuilder b1 = new StringBuilder();
        StringBuilder b2 = new StringBuilder();
        b1.append(b2);
    }
}
