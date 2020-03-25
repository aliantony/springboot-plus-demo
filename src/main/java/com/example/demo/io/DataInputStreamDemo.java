package com.example.demo.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @program demo1
 * @description DataInputStream可以使你从输入流中读取Java基本类型数据，而不必每次读取字节数据
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class DataInputStreamDemo {
    public static void main(String[] args) throws IOException {
        DataInputStream input = new DataInputStream(new FileInputStream("binary.data"));
        input.readInt();
        input.readDouble();
        input.readFloat();
        input.close();
    }
}
