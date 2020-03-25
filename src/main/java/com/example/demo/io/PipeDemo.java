package com.example.demo.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @program demo1
 * @description 管道流
 * 管道用于同一进程中的两个线程之间传递数据，可以通过connect连接两个管道，
 * 此处的管道不同于linux/unix下的管道，linux/unix下管道可以用于两个进程之间通信
 * 必须显示关闭，不能使用try-resources
 * @author wangqian
 * created on 2020-03-25
 * @version  1.0.0
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        PipedOutputStream output = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream(output);
            //把输出的数据通过管道连接到输入管道
            //output.connect(input);
            Thread t1 = new Thread(() -> {
                try {
                    output.write("hello words大".getBytes());
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    int data = input.read();
                    while (data != -1) {
                        System.out.println((char) data);
                        data = input.read();
                    }
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
            t2.start();

        }

}
