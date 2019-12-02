package com.example.demo.designer.proxy;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-12-02
 * @version  1.0.0
 */
public class HelloImpl implements Hello{
    @Override
    public void say(String str) {
        System.out.println(str);
        say2(str);
    }

    @Override
    public void say2(String str) {
        System.out.println(str);
    }
}
