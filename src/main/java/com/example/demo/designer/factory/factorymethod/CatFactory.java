package com.example.demo.designer.factory.factorymethod;

// 继承着宠物工厂
public class CatFactory implements AnimalFactory {
    @Override
    // 创建猫
    public Animal createAnimal() {
        return new Cat();
    }

}