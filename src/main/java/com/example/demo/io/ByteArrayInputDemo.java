package com.example.demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.CharBuffer;

/**
 * @program demo1
 * @description 把字节数组或字符数组读取到流中
 * FilterInputStream封装其它的输入流，并为它们提供额外的功能，一般不会用到，它的常用的子类有BufferedInputStream和DataInputStream。
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class ByteArrayInputDemo {
    public static void main(String[] args) throws IOException {
        String str = "大家一起来";
        byte[] bytes = str.getBytes();
        ByteArrayInputStream arrInput = new ByteArrayInputStream(bytes);
        OutputStream out = new FileOutputStream(new File("F://3.txt"));
        ByteArrayOutputStream arrOutput = new ByteArrayOutputStream();
        arrOutput.write(bytes);
        arrOutput.writeTo(out);
        byte[] bytes1 = arrOutput.toByteArray();
        char[] chars = str.toCharArray();
        //从字符数组读入
        CharArrayReader reader = new CharArrayReader(chars);
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("F://2.txt")));
        CharBuffer buffer = CharBuffer.allocate(1024);
        while (fileReader.read(buffer) != -1) {
            fileReader.read(buffer);
        }
        String str1 = new String(buffer.array());
        System.out.println(str1);
        CharArrayWriter chararraywriter = new CharArrayWriter();

        //内部缓冲区的大小默认8K,最好设置成1024的整数倍
        InputStream input = new BufferedInputStream(new FileInputStream("c:\\data\\input-file.txt"), 8 * 1024);

    }
}
