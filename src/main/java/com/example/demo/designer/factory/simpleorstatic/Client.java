package com.example.demo.designer.factory.simpleorstatic;

import com.example.demo.designer.factory.factorymethod.Animal;

/**
 * @program demo1
 * @description 测试简单工厂模式，简单工厂就一个工厂类负责创建所有的对象
 * 工厂方法模式每种类型的对象各有一个工厂负责创建，更容易扩展。
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class Client {
    public static void main(String[] args) {
        // 拿到狗
        Animal A = AnimalFactory.createAnimal("dog");
        A.eat();

        // 拿到猫
        Animal C = AnimalFactory.createAnimal("cat");
        C.eat();
    }
}
