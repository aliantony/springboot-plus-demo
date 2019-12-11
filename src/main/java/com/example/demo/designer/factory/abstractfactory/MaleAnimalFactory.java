package com.example.demo.designer.factory.abstractfactory;


public class MaleAnimalFactory implements AnimalFactory {

    // 生产公狗和公猫

    @Override
    public Animal createDog() {
        return new MaleDog();
    }

    @Override
    public Animal createCat() {
        return new MaleCat();
    }

}