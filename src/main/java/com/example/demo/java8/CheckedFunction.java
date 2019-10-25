package com.example.demo.java8;

@FunctionalInterface
interface CheckedFunction<T, R> {
    R apply(T t) throws Throwable;
}