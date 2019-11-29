package com.example.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangqian
 * created on 2019-11-29
 * @version 1.0.0
 * @program demo1
 * @description 用Jvisualvm的gc++插件测试垃圾回收
 */
public class GcTest {
    private String[] arr = new String[1024];

    public static void main(String[] args) {
        List<GcTest> lists = new ArrayList<>();
        while (true) {
            lists.add(new GcTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}