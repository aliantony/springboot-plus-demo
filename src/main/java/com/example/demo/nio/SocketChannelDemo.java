package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-27
 * @version  1.0.0
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞，可以在异步下调用connect(), read() 和write()
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        //由于使用了非阻塞的，是异步的，connect在连接还没建立就返回了，
        //while确保连接已经建立
        while(!socketChannel.finishConnect() ){
            //wait or do something else
        }
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);
        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        //非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()
        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }
    }
}
