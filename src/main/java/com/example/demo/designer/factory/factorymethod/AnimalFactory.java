package com.example.demo.designer.factory.factorymethod;
/**
 * @program demo1
 * @description 号称什么宠物都有
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public interface AnimalFactory {
    // 可以获取任何的宠物
    Animal createAnimal();
}
