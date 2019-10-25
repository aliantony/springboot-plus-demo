package com.example.demo.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @program demo1
 * @description  统计代码行数
 * @author wangqian
 * created on 2019-10-25
 * @version  1.0.0
 */
public class CountCodeLines {

    public static long getLines() {
        Stream<Path> paths = null;
        try {
            paths = Files.walk(Paths.get("D:/asset/antiy-user"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long count = paths                      // 获得项目目录下的所有目录及文件
                .filter(file -> !Files.isDirectory(file))          // 筛选出文件
                .filter(file -> file.toString().endsWith(".java")) // 筛选出 java 文件
                .flatMap(LambdaExceptionDecorator.apply(file -> Files.lines(file), e -> Stream.empty()))                // 按行获得文件中的文本
                .filter(line -> !line.trim().isEmpty())            // 过滤掉空行
                .count();
        System.out.println("代码行数：" + count);
        return count;
    }

    @FunctionalInterface
    interface CheckedFunction<T, R> {
        R apply(T t) throws Throwable;
    }

    static <T, R> Function<T, R> apply(CheckedFunction<T, R> function, Function<Throwable, R> handler) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(handler);
        return t -> {
            try {
                return function.apply(t);
            } catch (Throwable e) {
                return handler.apply(e);
            }
        };
    }

    public static void main(String[] args) {
        CountCodeLines.getLines();
    }
}
