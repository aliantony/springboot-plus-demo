package com.example.demo.designer.factory.abstractfactory;

public abstract class Dog extends Animal {

    // 狗喜欢吃肉
    @Override
    public void eat() {
        System.out.println("狗吃肉");
    }

}