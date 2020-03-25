package com.example.demo.io;

import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @program demo1
 * @description 可以读取到行号的流，不能通过设置行号改变流读取的位置
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class LineNumberReaderDemo {
    public static void main(String[] args) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader("f://copySequence02.java"));
        String data = reader.readLine();
        while(data != null){
            int lineNumber = reader.getLineNumber();
            System.out.println("当前行号:"+lineNumber);
            data = reader.readLine();
            if (StringUtils.isBlank(data)) {
                continue;
            }
            if (lineNumber == 150) {
                reader.setLineNumber(140);
            }
            //char dataChar = (char) data;
            //data = reader.read();


        }

    }
}
