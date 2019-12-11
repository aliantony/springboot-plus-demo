package com.example.demo.designer.factory.abstractfactory;

public class FemaleAnimalFactory implements AnimalFactory {

    // 生产母狗和母猫
    @Override
    public Animal createDog() {
        return  new FemaleDog();
    }

    @Override
    public Animal createCat() {
        return new FemaleCat();
    }

}