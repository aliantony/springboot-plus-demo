package com.example.demo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program demo1
 * @description 先进先出队列
 * @author wangqian
 * created on 2019-09-24
 * @version  1.0.0
 */
public class FIFODemo {
    private static final int cacheSize = 5;
    private static final LinkedHashMap<Integer, String> lru = new LinkedHashMap<Integer, String>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
            return size() > cacheSize;
        }
    };

    public static void main(String[] args) {
        lru.put(1, "1");
        lru.put(2, "2");
        lru.put(3, "3");
        lru.put(4, "4");
        lru.put(5, "5");
        System.out.println(lru.toString());
        lru.put(6, "6");
        System.out.println(lru.toString());
    }
}
