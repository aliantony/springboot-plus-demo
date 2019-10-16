package com.example.demo.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class AppAgent {
    public static void main(String[] args) throws Exception {
        // 建立一个MBeanServer，用来管理MBean
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // 创建AppMBean对象
        App mbean = new App();
        // 为AppMBean 创建ObjectName实例
        ObjectName name = new ObjectName("com.example.mbeans1:type1=App");
        // 将AppMbean对象注册到MBeanServer上去
        mbs.registerMBean(mbean, name);

        // Wait forever
        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
