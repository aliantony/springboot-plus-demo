package com.example.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program demo1
 * @description 本质就是把创建中的bean通过map提前曝光出来
 * @author wangqian
 * created on 2020-05-27
 * @version  1.0.0
 */
@Component
//@Scope("prototype")
public class BeanA {
    @Autowired
    private BeanB b;

   /* //@Autowired
    public BeanA(BeanB b) {
    }*/

    public String getName() {
        return "wangwu";
    }
}
