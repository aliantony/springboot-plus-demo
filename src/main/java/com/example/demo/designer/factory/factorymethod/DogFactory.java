package com.example.demo.designer.factory.factorymethod;

// 继承着宠物工厂
public class DogFactory implements AnimalFactory {

    // 创建狗
    @Override
    public Animal createAnimal() {
        return new Dog();
    }

}