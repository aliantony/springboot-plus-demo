package com.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Arrays;

/**
 * @program demo1
 * @description 实现bean的aware接口，在bean创建中给bean设置一些东西
 * @author wangqian
 * created on 2019-11-01
 * @version  1.0.0
 */
@Service
public class ServiceTwo implements ApplicationContextAware,
        ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware, ServletContextAware,  ServletConfigAware {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("执行setBeanClassLoader,ClassLoader Name = " + classLoader.getClass().getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("获得BeanFactory对象，可以用来检测Bean的作用域:" + beanFactory.isPrototype("serviceTwo"));
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("获得Bean在配置文件中定义的名字：" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("可以用来获取所有Bean definition的名字");
        System.out.println("执行setApplicationContext:: Bean Definition Names="
                + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("获得事件发布器，用于实现发布订阅");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("设置environment");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("可以获得classpath中某个文件");
        Resource resource = resourceLoader.getResource("classpath:application.yml");
        System.out.println(resource);
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("执行setImportMetadata");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("在一个MVC应用中可以获取ServletContext对象，可以读取context中的参数:" + servletContext);
    }

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        System.out.println("在一个MVC应用中可以获取ServletConfig对象，可以读取config中的参数");
    }
}
