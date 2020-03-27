package com.example.demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-27
 * @version  1.0.0
 * Java NIO 管道是2个线程之间的单向数据连接
 * 用sink通道往管道写
 * 用source通道从管道读
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        int bytesRead = sourceChannel.read(buf);

    }
}
