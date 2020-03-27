package com.example.demo.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-26
 * @version  1.0.0
 */
public class SocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.connect(new InetSocketAddress(Inet4Address.getLocalHost(),8001));
        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        ByteBuffer readBuffer = ByteBuffer.allocate(32);
        socketChannel.register(selector, SelectionKey.OP_READ);
        writeBuffer.put("hello".getBytes());
        writeBuffer.flip();
        if(socketChannel.finishConnect()) {
            socketChannel.write(writeBuffer);
            while (true) {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isReadable()) {
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ByteBuffer bf = ByteBuffer.allocate(1024);
                        SocketChannel othersc = (SocketChannel) key.channel();
                        while (othersc.read(bf) > 0) {
                            bf.flip();
                            while (bf.hasRemaining()) {
                                bos.write(bf.get());
                            }
                            bf.clear();
                        }
                        System.out.println("服务端返回的数据：" + bos.toString());
                    }
                }
                selector.selectedKeys().clear();
            }
        }
    }
}
