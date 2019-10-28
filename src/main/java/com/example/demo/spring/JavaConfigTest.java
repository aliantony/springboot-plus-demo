package com.example.demo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class JavaConfigTest {
    public static void main(String[] arg) {
        /**
         * https://www.cnblogs.com/ashleyboy/p/9662119.html
         * 是一个用来管理注解bean的容器
         * 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。
         * 避免使用application.xml进行配置。相比XML配置，更加便捷
         */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        Entitlement ent = (Entitlement)ctx.getBean("entitlement");
        System.out.println(ent.getName());
        System.out.println(ent.getTime());

        Entitlement ent2 = (Entitlement)ctx.getBean("entitlement2");
        System.out.println(ent2.getName());
        System.out.println(ent2.getTime());

        ctx.close();
    }
}