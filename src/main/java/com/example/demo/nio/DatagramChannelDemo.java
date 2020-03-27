package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-27
 * @version  1.0.0
 * 通过UDP通道发送接收数据，无连接，不可靠
 */
public class DatagramChannelDemo {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        //从通道接收数据
        channel.receive(buf);
        //发送数据到通道，不管对方接没接到
        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));
    }
}
