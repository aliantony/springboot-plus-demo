package com.example.demo.nio;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program demo1
 * @description  
 * @author wangqian
 * created on 2020-03-26
 * @version  1.0.0
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * 多路IO复用，异步非阻塞
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        //创建selector
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(Inet4Address.getLocalHost(),8001));
        SocketChannel channel = serverChannel.accept();
        //只有套接字通道可以切换为非阻塞模式，要与selector一起用必须为非阻塞
        //FileChannel不能切换为非阻塞
        channel.configureBlocking(false);
        //把通道注册到selector,OP_READ为selector监测的通道事件
        //事件包括Connect,Accept,Read,Write
        int interest = SelectionKey.OP_ACCEPT | SelectionKey.OP_WRITE;
        //附加对象到SelectionKey
        Object attchObj = new Object();
        //返回的SelectionKey中包含一些感兴趣的属性interest集合,ready集合,Channel,Selector,附加的对象（可选）
        SelectionKey selectionKey = channel.register(selector,
                interest, attchObj);
        int interestSet = selectionKey.interestOps();
        //用“位与”操作interest 集合和给定的SelectionKey常量，可以确定某个确定的事件是否在interest 集合中
        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;
        //是否连接就绪
        selectionKey.isAcceptable();
        //准备好的事件
        int readySet = selectionKey.readyOps();
        Channel channelGet  = selectionKey.channel();
        Selector selectorGet = selectionKey.selector();
        //通过selectionKey附加对象
        selectionKey.attach(attchObj);
        //获得附加对象
        Object attachedObj = selectionKey.attachment();
        //返回感兴趣的事件，阻塞直到事件就绪，有一个通道就绪返回1
        int select = selector.select();
        //有通道变成可选择的，则此方法直接返回零
        int select1 = selector.selectNow();
        //select()方法返回有1个或多个通道就绪，调用selectedKeys
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator keyIterator = selectedKeys.iterator();
        //检测各个键所对应的通道的就绪事件
        while(keyIterator.hasNext()) {
            SelectionKey key = (SelectionKey)keyIterator.next();
            if(key.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel.
            } else if (key.isConnectable()) {
                // a connection was established with a remote server.
            } else if (key.isReadable()) {
                // a channel is ready for reading
            } else if (key.isWritable()) {
                // a channel is ready for writing
            }
            /**
             * Selector不会自己从已选择键集中移除SelectionKey实例
             * 必须在处理完通道时自己移除。下次该通道变成就绪时，
             * Selector会再次将其放入已选择键集中。
             */
            //处理完移除事件
            keyIterator.remove();
        }
        SocketChannel channel1 = (SocketChannel)selectionKey.channel();

        //调用select会阻塞，别的线程用wakeup唤醒，如果调用wakeup时，此线程没有调用select,
        //则下次调用select会立马醒来
        selector.wakeup();
        /**
         * 用完Selector后调用其close()方法会关闭该Selector，
         * 且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭。
         */
        selector.close();
    }
}
