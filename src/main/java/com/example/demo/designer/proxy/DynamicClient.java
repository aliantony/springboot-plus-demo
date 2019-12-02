package com.example.demo.designer.proxy;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-12-02
 * @version  1.0.0
 */
public class DynamicClient {

    public static void main(String[] args) {
        DynamicProxy proxy = new DynamicProxy(new HelloImpl());
        Hello hello = proxy.getProxy();
        hello.say("mum");
        //hello.say2("daddy");
    }
}
