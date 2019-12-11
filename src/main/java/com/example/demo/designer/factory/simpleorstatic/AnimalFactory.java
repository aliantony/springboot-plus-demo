package com.example.demo.designer.factory.simpleorstatic;

import com.example.demo.designer.factory.factorymethod.Animal;
import com.example.demo.designer.factory.factorymethod.Cat;
import com.example.demo.designer.factory.factorymethod.Dog;

public class AnimalFactory {
    public static Dog createDog() {
        return new Dog();
    }

    public static Cat createCat() {
        return new Cat();
    }


    // 外界想要猫要狗，这里创建就好了
    public static Animal createAnimal(String type) {
        if ("dog".equals(type)) {
            return new Dog();
        } else if ("cat".equals(type)) {
            return new Cat();
        } else {
            return null;
        }
    }
}