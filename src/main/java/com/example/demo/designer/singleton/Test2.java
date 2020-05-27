package com.example.demo.designer.singleton;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @program demo1
 * @description 防止通过反序列化破坏单例
 * @author wangqian
 * created on 2020-05-07
 * @version  1.0.0
 */
public class Test2 {
    public static void main(String[] args) throws Exception{
        SingletonDemo s1 = SingletonDemo.getInstance();
        System.out.println(s1);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(s1);
        ObjectInputStream inp = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        SingletonDemo s2 = (SingletonDemo)inp.readObject();
        System.out.println(s2);
    }
}
