package com.example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-11-01
 * @version  1.0.0
 */
@ComponentScan("com.example")
@Configuration
public class DemoMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoMain.class);
        context.getBean("serviceOne");
        context.getBean("serviceTwo");
        context.close();
    }

    //@Bean
    public CustomerBeanPostProcessor customerBeanPostProcessor() {
        return new CustomerBeanPostProcessor();
    }

    /**
     * note: 默认容器创建的bean都是单例的，可以通过Scope注解指定，prototype,request,session
     * prototype的bean容器只会执行初始化方法，不会执行销毁的方法，因为每次都是创建新的bean,
     * 因此bean创建以后的生命周期由客户端代码自行管理。
     */
}
