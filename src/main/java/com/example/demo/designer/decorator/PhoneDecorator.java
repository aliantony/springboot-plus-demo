package com.example.demo.designer.decorator;
/**
 * @program demo1
 * @description 装饰者抽象类
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class PhoneDecorator implements Phone{

    private Phone phone;
    public PhoneDecorator(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void call() {
        phone.call();
    }
}
