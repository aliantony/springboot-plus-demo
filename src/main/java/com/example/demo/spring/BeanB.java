package com.example.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-05-27
 * @version  1.0.0
 */
@Component
//@Scope("prototype")
public class BeanB {
    @Autowired
    private BeanA a;

    /*//@Autowired
    public BeanB(BeanA a) {
    }*/
}
