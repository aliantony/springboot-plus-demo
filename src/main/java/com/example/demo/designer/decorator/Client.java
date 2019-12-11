package com.example.demo.designer.decorator;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new MusicPhoneDecorator(new GiveCurrentTimePhoneDecorator(new IPhoneX()));
        phone.call();
    }
}
