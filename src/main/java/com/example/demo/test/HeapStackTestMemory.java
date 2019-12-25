package com.example.demo.test;

public class HeapStackTestMemory {

    public static void main(String[] args) {
        int i = 1;
        Object obj = new Object();
        HeapStackTestMemory mem = new HeapStackTestMemory();
        mem.foo(obj);
    }

    private void foo(Object param) {
        String str = param.toString();
        System.out.println(str);
    }
}
