package com.example.demo.designer.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author wangqian
 * created on 2020-05-07
 * @version 1.0.0
 * @program demo1
 * @description
 */
public class SingletonDemo implements Serializable {
    private static SingletonDemo instance = new SingletonDemo();

    /**
     * 防止通过反射创建对象
     */
    private SingletonDemo() {
        if (instance != null) {
            try {
                throw new Exception("只能创建一个对象！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static SingletonDemo getInstance() {
        return instance;
    }

    /** 对象反序列化时在readObject方法之后会调用该方法*/
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
