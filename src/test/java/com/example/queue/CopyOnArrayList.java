package com.example.queue;

import com.example.entity.CustBook;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @program demo1
 * @description 
 * @author wq
 * created on 2020-06-11
 * @version  1.0.0
 */
@Slf4j
public class CopyOnArrayList {

    /**
     * 我们看到对象的改变实际上改变了拷贝的源。
     * 而copyList.set(0,“e”)实际上创建了一个新的String对象，并把它赋值到copyList的0位置
     * 底层是Arrays.copyOf native arraycopy是引用拷贝，拷贝之后对象的值发送了变化，源对象也会发生改变
     */
    @Test
    public void withConstructor(){
        List<String> stringList=new ArrayList<>(Arrays.asList("a","b","c"));
        List<String> copyList = new ArrayList<>(stringList);
        copyList.set(0,"e");
        log.info("{}",stringList);
        log.info("{}",copyList);

        List<CustBook> objectList=new ArrayList<>(Arrays.asList(new CustBook("a"),new CustBook("b"),new CustBook("c")));
        List<CustBook> copyobjectList = new ArrayList<>(objectList);
        copyobjectList.get(0).setName("e");
        log.info("{}",objectList);
        log.info("{}",copyobjectList);
    }

    @Test
    public void accessOrder(){
        /**
         * 最后一个参数按照访问书顺序排序，将对象按最老访问到最新访问的顺序排
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<>(16, .75f, true);
        map.put("ddd","desk");
        map.put("aaa","ask");
        map.put("ccc","check");
        map.keySet().forEach(System.out::println);
        map.get("aaa");
        map.keySet().forEach(System.out::println);
    }

    public enum Types {
        RED, GREEN, BLACK, YELLO
    }

    /**
     * 因为在EnumMap中，所有的key的可能值在创建的时候已经知道了，所以使用EnumMap和hashMap相比，可以提升效率。
     * 同时，因为key比较简单，所以EnumMap在实现中，也不需要像HashMap那样考虑一些复杂的情况
     */
    @Test
    public void useEnumMap(){
        EnumMap<Types, String> activityMap = new EnumMap<>(Types.class);
        activityMap.put(Types.BLACK,"black");
        activityMap.put(Types.GREEN,"green");
        activityMap.put(Types.RED,"red");
        activityMap.forEach((k, v) -> System.out.println(k.name() + v));
    }

    @Test
    public void testEnumSet() {
        EnumSet set = EnumSet.noneOf(Types.class);
        EnumSet set1 = EnumSet.allOf(Types.class);
        set1.forEach(e -> System.out.println(e));
    }

    @Test
    public void useForEachRemaining(){
        List<String> stringList= new ArrayList<>();
        Iterator<String> stringIterator= Arrays.asList("a","b","c").iterator();
        stringIterator.forEachRemaining(stringList::add);
        log.info("{}",stringList);
        Iterator<String> stringIterator1= Arrays.asList("f","g","h").iterator();
        Iterable<String> stringIterable = () -> stringIterator1;
        //stringIterable.spliterator().trySplit().forEachRemaining(e -> System.out.println(e));
        List<String> stringList3= StreamSupport.stream(stringIterable.spliterator(),false).collect(Collectors.toList());
        log.info("{}",stringList3);
    }

}
