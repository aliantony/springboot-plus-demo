package com.example.demo.designer.decorator;
/**
 * @program demo1
 * @description 被装饰类的实现类
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class IPhoneX implements Phone {
    @Override
    public void call() {
        System.out.println("打电话约她出来");
    }
}
