package com.example.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-26
 * @version  1.0.0
 * 如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel传输到另外一个channel。
 */
public class ChannelTransferDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);


        RandomAccessFile fromFile1 = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel      fromChannel1 = fromFile.getChannel();

        RandomAccessFile toFile1 = new RandomAccessFile("toFile.txt", "rw");
        FileChannel      toChannel1 = toFile.getChannel();

        long position1 = 0;
        long count1 = fromChannel.size();

        fromChannel.transferTo(position1, count1, toChannel1);
    }
}
