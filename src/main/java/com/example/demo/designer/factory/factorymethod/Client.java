package com.example.demo.designer.factory.factorymethod;
/**
 * @program demo1
 * @description 测试工厂方法模式
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class Client {
    public static void main(String[] args) {
        // 去找狗工厂拿一只狗过来
        AnimalFactory f = new DogFactory();

        // 店主就拿到了一只狗给Java3y
        Animal a = f.createAnimal();
        a.eat();

        AnimalFactory ff = new CatFactory();

        // 店主就拿到了一只猫给Java3y
        Animal aa = ff.createAnimal();
        aa.eat();
    }
}
