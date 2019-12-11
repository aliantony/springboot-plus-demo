package com.example.demo.designer.factory.abstractfactory;

public abstract class Cat extends Animal {
    // 猫喜欢吃鱼
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}