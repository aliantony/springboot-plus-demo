package com.example.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author wangqian
 * created on 2020-03-26
 * @version 1.0.0
 * @program demo1
 * @description
 * FileChannel 不能直接打开，需通过InputStream、OutputStream或RandomAccessFile获得
 * DatagramChannel 通过udp读取网络中的数据
 * SocketChannel 通过tcp读取网络中的数据
 * ServerSocketChannel 监听tcp连接，每一个新进来的连接都会建立一个SocketChannel
 * 阻塞式socket和file通过stream读取，非阻塞通过channel和buffer交换数据
 */
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        RandomAccessFile aFile = new RandomAccessFile("F://nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        int bytes = inChannel.read(byteBuffer);
        while (bytes != -1) {
            //读写模式切换
            byteBuffer.flip();
            decoder.decode(byteBuffer, charBuffer, false);
            charBuffer.flip();

            System.out.println(charBuffer);
            charBuffer.clear();
            byteBuffer.clear();
            bytes = inChannel.read(byteBuffer);
        }
        if (aFile != null) {
            aFile.close();
        }

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            //无法保证write一次能向通道写入多少字节，所以循环
            inChannel.write(buf);
        }

        long pos = inChannel.position();
        //改变读取的位置
        inChannel.position(pos +123);
        //关联文件的大小
        inChannel.size();

        //截取前1024个字节，1024后面的将删除
        inChannel.truncate(1024);

        //true将文件元数据也写入磁盘，因为出于性能方面的考虑，操作系统会将数据缓存在内存中
        //force立即刷到磁盘
        inChannel.force(true);


    }
}
