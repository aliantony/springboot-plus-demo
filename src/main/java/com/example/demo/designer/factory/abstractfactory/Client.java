package com.example.demo.designer.factory.abstractfactory;
/**
 * @program demo1
 * @description 测试抽象工厂模式
 * 简单来说：工厂方法模式的工厂是创建出一种产品，而抽象工厂是创建出一类产品。
 * 抽象工厂模式多了一层抽象，减少了工厂的数量。
 * 难以扩展产品族--->如果我再要宠物猪的话，就要修改AnimalFactory、FemaleAnimalFactory、MaleAnimalFactory这些类
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class Client {
    public static void main(String[] args) {
        // 需要性别为母的就去找母工厂
        AnimalFactory af = new FemaleAnimalFactory();

        // 需要一只母猫
        af.createCat().gender();

        // 需要一只母狗
        af.createDog().gender();

        // 需要性别为公的就去找公工厂
        AnimalFactory aff = new MaleAnimalFactory();

        // 需要一只公狗
        aff.createDog().gender();

        // 需要一只公猫
        aff.createCat().gender();
    }
}
