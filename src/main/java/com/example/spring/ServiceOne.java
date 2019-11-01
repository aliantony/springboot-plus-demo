package com.example.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-11-01
 * @version  1.0.0
 */
@Service
@Scope("singleton") // prototype,request,session
//@Lazy
public class ServiceOne implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("bean销毁时做一些清理工作");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean初始化属性赋值完成后做一些初始化工作");
    }

    /**
     * 不能带参数
     */
    @PostConstruct
    public void init() {
        System.out.println("bean的属性赋值之后初始化");
    }

    /**
     * 不能带参数，注解优先级高于实现接口
     */
    @PreDestroy
    public void annotationDestroy() {
        System.out.println("bean销毁之前");
    }

}
