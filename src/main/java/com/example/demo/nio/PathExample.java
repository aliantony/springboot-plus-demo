package com.example.demo.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-03-27
 * @version  1.0.0
 */
public class PathExample {
    public static void main(String[] args) {
        Path path = Paths.get("c:\\data\\myfile.txt");
        //相对路径d:\data\projects
        Path projects = Paths.get("d:\\data", "projects");
        //d:\data\projects\a-project\myfile.txt
        Path file     = Paths.get("d:\\data", "projects\\a-project\\myfile.txt");
        Path currentDir = Paths.get(".");
        System.out.println(currentDir.toAbsolutePath());
        String path1 = "d:\\data\\projects\\a-project\\..\\another-project";
        //d:\data\projects\another-project
        Path parentDir2 = Paths.get(path1);
        //去掉path字符串中间的点
        parentDir2.normalize();
    }
}
