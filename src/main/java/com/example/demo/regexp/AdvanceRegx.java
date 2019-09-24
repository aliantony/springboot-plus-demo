package com.example.demo.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangqian
 * created on 2019-09-24
 * @version 1.0.0
 * @program demo1
 * @description 高级模式的正则匹配
 * 前瞻：
 * exp1(?=exp2) 查找exp2前面的exp1
 * 后顾：
 * (?<=exp2)exp1 查找exp2后面的exp1
 * 负前瞻：
 * exp1(?!exp2) 查找后面不是exp2的exp1
 * 负后顾：
 * (?<!=exp2)exp1 查找前面不是exp2的exp1
 */
public class AdvanceRegx {
    public static void test() {
        String[] strs = {
                "abc1232", "wwwadsf",
                "awwwfas", "wwadfsf",
                "", "ww", " ", "www"
        };
        //(?!X) 某个字符间隙后面不允许出现字符X
        //不允许以www开头的
        //?: 表示非捕获分组，和捕获分组唯一的区别在于，非捕获分组匹配的值不会保存起来
        //表示捕获分组，()会把每个分组里的匹配的值保存起来,$n或/n引用 n是1-99，
        //(?:)表示非捕获分组
        String regex = "(?:(?!^www).)*";
        for (String str : strs) {
            System.out.printf("%-7s %s%n", str, str.matches(regex));
        }
    }

    /**
     * 前瞻：
     * exp1(?=exp2) 查找exp1后面等于exp2
     * 后顾：
     * (?<=exp2)exp1 查找exp1前面等于exp1
     * 负前瞻：
     * exp1(?!exp2) 查找exp1后面不等于exp2
     * 负后顾：
     * (?<!exp2)exp1 查找exp1前面不等于exp2
     * @param args
     */
    public static void test2() {
      //String reg = "(?<=中国)人";
        String reg = "中国(?=人)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("中国人");
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    public static void test3() {
        //String reg = "(?<=中国)人";
        //查找中国后面不是人的
        String reg = "中国(?!人)";
        Pattern pattern = Pattern.compile(reg);
        //Matcher matcher = pattern.matcher("中国人");
        Matcher matcher = pattern.matcher("中国话");
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    //捕获分组和非捕获分组，举例：千位分割符
    public static void test5() {
        // 数字格式化 1,123,000
        //"1234567890".replace(/\B(?=(?:\d{3})+(?!\d))/g,",") // 结果：1,234,567,890，匹配的是后面是3*n个数字的非单词边界(\B)
        Pattern pattern = Pattern.compile("\\B(?=(?:\\d{3})+(?!\\d))");
        Matcher m = pattern.matcher("1234567890");
        while(m.find()){
            String string = m.group();
            System.out.println(m.start() + "," + m.end());
        }
        String d = "1234567890";
        //非单词边界后面跟着3个数字，(?!\d)代表捕获3位数字之间的间隙
        System.out.println(d.replaceAll("\\B(?=(?:\\d{3})+(?!\\d))", ","));

    }

    /**
     * (?<!exp2)exp1 查找exp1前面不等于exp2
     */
    public static void test4() {
        //String reg = "(?<=中国)人";
        //查找话前不是美国的
        String reg = "(?<!美国)话";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("中国话");
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    public static void test6() {
        /**
         * var str = "http://www.runoob.com:80/html/html-tutorial.html";
         * var patt1 = /(\w+):\/\/([^/:]+)(:\d*)?([^# ]*)/;
         * arr = str.match(patt1);
         * for (var i = 0; i < arr.length ; i++) {
         *     document.write(arr[i]);
         *     document.write("<br>");
         * }
         */
        String str = str = "http://www.runoob.com:80/html/html-tutorial.html";
        String reg = "(\\w+):\\/\\/([^/:]+)(:\\d*)?([^# ]*)";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
            //String string = m.group(); // 处理
            //System.out.println(string);
        }

    }


    public static void main(String[] args) {
        //test();
        //test2();
        //test3();
        //test4();
        //test5();
        test6();
    }
}
