package com.example.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program demo1
 * @author wangqian
 * created on 2020-03-26
 * @version  1.0.0
 * @description clear方法清空缓存，compact方法只会清空已经读过的数据,
 * 任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
 * capacity：buffer的容量，可以写入capacity个byte/long/short
 * position: 当前写或读入数据的位置，最大为capacity-1，当从写切换到读时，position重置为0，写入一个数据，
 * position会移到下一个可以写入的位置。
 * limit:可以写入或读取多少数据，写模式limit=capacity,读模式limit=已经写入数据的position
 * Buffer.rewind()将position设回0,limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）
 * Buffer.mark()方法，可以标记Buffer中的一个特定position。
 * 之后可以通过调用Buffer.reset()方法恢复到这个position
 * Buffer的equals和comparTo方法都是比较buffer中剩余元素（从position到limit）,个数，类型，值
 */
public class BufferDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("F://nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf); //read into buffer.
        //read from buffer into channel.
        //int bytesWritten = inChannel.write(buf);
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
            //清空缓冲区
            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
