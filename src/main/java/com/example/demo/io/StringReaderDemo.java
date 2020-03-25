package com.example.demo.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @program demo1
 * @description 把原始字符串转成Reader
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class StringReaderDemo {
    public static void main(String[] args) throws IOException {
        Reader reader = new StringReader("input string...");

        int data = reader.read();

        while(data != -1) {

            //do something with data...

            //doSomethingWithData(data);

            data = reader.read();

        }

        StringWriter writer = new StringWriter();

        //write characters to writer.

        //从writer中获取已经写入的数据
        String data1 = writer.toString();

        StringBuffer dataBuffer = writer.getBuffer();

        reader.close();
    }
}
