package com.example.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-29
 * @version  1.0.0
 * T extends Comparable<? super T> T 要被排序的对象必须是Comparable的实现类，
 * ? super T 排序的对象必须是T或T的父类，比如Dog的父类Object,就用Object.compareTo实现
 */
@Data
@Accessors(chain = true)
//@Builder
@RequiredArgsConstructor(staticName = "of")
public class Dog implements Comparable<Dog> {

    @NonNull private String name;
    private String color;

    @Override
    public int compareTo(Dog o) {
        return o.getName().compareTo(this.name);
    }
}
