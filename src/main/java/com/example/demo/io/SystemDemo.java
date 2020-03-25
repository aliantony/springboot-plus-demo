package com.example.demo.io;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * @program demo1
 * @description 输入输出错误流
 * JVM启动时会初始化输入，输出，错误流，可以替换
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class SystemDemo {
    public static void main(String[] args) throws IOException {
        System.err.println("程序出错啦");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        OutputStream output = new FileOutputStream("f://out.txt");
        PrintStream printer = new PrintStream(output);
        System.setOut(printer);
        System.out.println("把控制的文本输出到文件中");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while (StringUtils.isNotBlank(str = reader.readLine())) {
            System.out.println(str);
        }
        reader.close();
    }
}
