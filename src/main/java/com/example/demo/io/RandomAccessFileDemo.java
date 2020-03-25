package com.example.demo.io;


import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @program demo1
 * @description 允许来回读写文件，允许替换某部分内容
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        //rw表示以读写的方式打开文件
        RandomAccessFile file = new RandomAccessFile("F://copySequence02.java", "rw");
        //移动指针
        file.seek(200);
        //获取指针当前位置
        final long filePointer = file.getFilePointer();
        byte[] by = new byte[1024];
        int len = by.length;
        while (file.read(by, 0, len) != -1) {
            String s = new String(by);
            System.out.println(s);
        }
    }
}
