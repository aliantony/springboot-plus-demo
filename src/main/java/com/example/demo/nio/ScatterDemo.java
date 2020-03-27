package com.example.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-26
 * @version  1.0.0
 * Scatter从channel分散到多个buffer
 * Gather 从多个buffer汇总到channel
 */
public class ScatterDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("F://nio-data.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body   = ByteBuffer.allocate(1024);
        //必须先把第一个header的buffer填满，才能填body,按数组中的顺序填充，不适合大小不固定的消息
        ByteBuffer[] bufferArray = { header, body };
        channel.read(bufferArray);

        ByteBuffer header1 = ByteBuffer.allocate(128);
        ByteBuffer bod1   = ByteBuffer.allocate(1024);

        //write data into buffers

        ByteBuffer[] bufferArray1 = { header, body };

        //Gather将多个buffer的数据写入channel,可以较好的处理动态消息，因为只会写入position到limit的数据
        //即只写入buffer中的已有的数据
        channel.write(bufferArray1);
    }
}
