package com.example.demo.nio;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program demo1
 * @description 模拟一个server，浏览器请求
 * @author wangqian
 * created on 2020-03-26
 * @version  1.0.0
 */
public class SelectDemo2 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(Inet4Address.getLocalHost(),8001));
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
         channel = serverChannel.accept();
        //serverChannel.configureBlocking(false);
        channel.configureBlocking(false);
        //Only ServerSocketChannel supports SelectionKey.OP_ACCEPT.OP_CONNECT
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) {
                continue;
            }
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey)keyIterator.next();
                if(key.isAcceptable()) {
                    ///SocketChannel channel = serverChannel.accept();
                    //channel.configureBlocking(false);
                    //SelectionKey connKey = channel.register(selector, SelectionKey.OP_ACCEPT);
                    // a connection was accepted by a ServerSocketChannel.
                    System.out.println("一个连接就绪了");
                } else if (key.isConnectable()) {
                    System.out.println("一个连接被建立了");
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    SocketChannel channel2 = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readLen = 0;
                    StringBuffer sb = new StringBuffer();
                    while ((readLen = channel2.read(buffer)) > 0){
                        buffer.flip();
                        byte [] temp = new byte[readLen];
                        buffer.get(temp,0,readLen);
                        sb.append(new String(temp));
                        buffer.clear();
                    }
                    // 返回就绪的操作
                    //int i = key.readyOps();
                    System.out.println("接收到客户端数据是:" + sb.toString());
                    channel.write(ByteBuffer.wrap(("客户端，你传过来的数据是："+sb.toString()).getBytes()));
                    if(readLen == -1){
                        channel2.close();
                    }
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    SocketChannel channel1 = (SocketChannel)key.channel();
                    String str = "服务器发给客户端的数据";
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.put(str.getBytes());
                    channel1.write(buffer);
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}
