package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-27
 * @version  1.0.0
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        while(true){
            SocketChannel socketChannel =
                    serverSocketChannel.accept();
            //在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null
            if(socketChannel != null){
                //do something with socketChannel...
            }
        }
    }
}
