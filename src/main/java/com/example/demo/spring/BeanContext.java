package com.example.demo.spring;

import org.springframework.beans.factory.InitializingBean;
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
public class BeanContext implements InitializingBean {
    @Autowired
    private BeanA a;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(a.getName());
    }
}
