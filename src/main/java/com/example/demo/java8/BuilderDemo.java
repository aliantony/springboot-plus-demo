package com.example.demo.java8;


import com.example.entity.GirlFriend;

/**
 * @program demo1
 * @description java8 builder
 * @author wangqian
 * created on 2020-04-02
 * @version  1.0.0
 */
public class BuilderDemo {
    public static void main(String[] args) {
        GirlFriend myGirlFriend = Builder.of(GirlFriend::new)
                .with(GirlFriend::setName, "小美")
                .with(GirlFriend::setBirthday, 18).build();
                //.with(GirlFriend::set, 33, 23, 33);
    }
}
