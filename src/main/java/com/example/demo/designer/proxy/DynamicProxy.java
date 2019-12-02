package com.example.demo.designer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program demo1
 * @description 动态代理类
 * @author wangqian
 * created on 2019-12-02
 * @version  1.0.0
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;
    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("say")) {
            System.out.println("*****漂亮的分割符******");
        }
        Object result = method.invoke(target, args);
        return result;
    }

    public <T> T getProxy() {
        T proxy = (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return proxy;
    }
}
