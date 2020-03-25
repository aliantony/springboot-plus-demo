package com.example.demo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @program demo1
 * @description 序列化,如果你希望类能够序列化和反序列化，必须实现Serializable接口
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class ObjectInputStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("object.data"));
        MyClass object = (MyClass) input.readObject(); //etc.
        input.close();
    }
    public static class MyClass {
        String name = "巡航";

        public String getName() {
            return name;
        }
    }
}
