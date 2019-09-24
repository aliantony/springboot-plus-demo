package com.example.demo.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program demo1
 * @description 正则表达式
 * @author wangqian
 * created on 2019-09-24
 * + 前面的字符至少出现一次或多次
 * * 前面的字符可出现0次或多次
 * ？前面的字符可出现1次或0次
 * . 单个字符，匹配除\n外的任何字符
 * {n} 匹配n次
 * {n，m} 最少匹配n次，最多m次
 * [0-9 | a-z] 0到9的数字或a-z的字母
 *js中正则表达式写在/<.*>/中，/是js里的符号，表示这个字符串不对中间内容转义，它不是正则表达式的一部分
 * /pattern/flag  flag可以是i、不区分大小写 g全局匹配 m多行匹配
 * ?s 忽略换行符，看成单行处理
 * @version  1.0.0
 */
public class RegexpDemo {


    /**
    *  *、+限定符都是贪婪的，因为它们会尽可能多的匹配文字，
    * 只有在它们的后面加上一个?就可以实现非贪婪或最小匹配。
    * 通过在 *、+ 或 ? 限定符之后放置 ?，该表达式从"贪心"表达式转换为"非贪心"表达式或者最小匹配
    * @param []
    * @return void
    * @author wangqian
    * @date 2019/9/24
    */
    public static void test(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
               // System.out.println(matcher.group(i));
            }
            String string = matcher.group(); // 处理
            System.out.println(string);
        }
    }

    //全局匹配
    public static void global(Pattern pattern, String str) {
        Matcher m = pattern.matcher(str);
        //循环向后搜索，全局匹配
        while(m.find()){
            String string = m.group(); // 处理
            System.out.println(string);
        }
    }

    public static void global2() {
        String str =
                "<table>                \n" +
                        "  <tr>                 \n" +
                        "    <TD>               \n" +
                        "       Hello World!    \n" +
                        "    </td>              \n" +
                        "  </tr>                \n" +
                        "</table>";
        String regex = "<td>(.+?)</td>";
        //String regex = "(?s)<td>(.+?)</td>";
        //(?s)多行看成单行，(?i)不区分大小写 //多行匹配(?s)
        //String regex = "(?is)<td>(.+?)</td>";    // 合并写法
        //String regex = "(?s)(?i)<td>(.+?)</td>";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
            System.out.println(matcher.group(1).trim());
        }
    }

    /**
    * 匹配单词边界
     * -b 单词边界：单词和空格之间的位置
     * -B 非边界
    * @param [pattern, str]
    * @return void
    * @author wangqian
    * @date 2019/9/24
    */
    public static void test1(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    public static void main(String[] args) {
        //贪婪模式
        //test(Pattern.compile("<.*>"), "<H1>Chapter 1 - 介绍正则表达式</H1>");
        //非贪婪模式
        //test(Pattern.compile("<.*?>"), "<H1>Chapter 1 - 介绍正则表达式</H1>");
        //test1(Pattern.compile("<\\w+?>"), "<H1>Chapter 1 - 介绍正则表达式</H1>");
        //apt是单词的边界
        //test(Pattern.compile("\\Bapt"), "aptitude");
        //test(Pattern.compile("\\Bapt"), "Chapter");
        //\b单词间隙，\1捕获分组，相同的两个单词挨着的
        test(Pattern.compile("\\b([a-z]+) \\1 \\b", Pattern.CASE_INSENSITIVE | Pattern.DOTALL), "Is is the cost of of gasoline going up up");
        //global2();
    }

}
