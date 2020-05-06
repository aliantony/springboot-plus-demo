package com.example.demo.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class MatcherTest {
	
	public static void main(String[] args){
				Pattern pattern = Pattern.compile("\\d{3,5}");
				String charSequence = "123-34345-234-00";
				Matcher matcher = pattern.matcher(charSequence);
		 
				//虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
				print(matcher.matches());
				//测试匹配位置
				matcher.find();
				print(matcher.start());
		 
				//使用reset方法重置匹配位置
				matcher.reset();
		 
				//第一次find匹配以及匹配的目标和匹配的起始位置
				print(matcher.find());
				print(matcher.group()+" - "+matcher.start());
				//第二次find匹配以及匹配的目标和匹配的起始位置
				print(matcher.find());
				print(matcher.group()+" - "+matcher.start());
		 
				//第一次lookingAt匹配以及匹配的目标和匹配的起始位置
				print(matcher.lookingAt());
				print(matcher.group()+" - "+matcher.start());
		 
				//第二次lookingAt匹配以及匹配的目标和匹配的起始位置
				print(matcher.lookingAt());
				print(matcher.group()+" - "+matcher.start());
				
				//使用reset方法重置匹配位置
				matcher.reset();
				//遍历所有匹配的子串
				while(matcher.find()){
					print(matcher.group()+" - "+matcher.start());
				}
			//matches方法匹配整个字符串，完全满足返回true
			Pattern.matches("\\d+","2223");//返回true
			Pattern.matches("\\d+","2223aa");//返回false,需要匹配到所有字符串才能返回true,这里aa不能匹配到
			Pattern.matches("\\d+","22bb23");//返回false,需要匹配到所有字符串才能返回true,这里bb不能匹配到
				
			}
			public static void print(Object o){
				System.out.println(o);
			}
 
 
}