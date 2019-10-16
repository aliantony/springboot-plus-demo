package com.example.demo.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class AppAgent2 {
    public static void main(String[] args) throws Exception {
        // 建立一个MBeanServer，用来管理MBean
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // 创建AppMBean对象
        App mbean = new App();
        WatchDog dog = new WatchDog();
        dog.setPid("10024545");
        dog.setUpTime("22222");
        mbean.addWatchDog(dog);
        // 为AppMBean 创建ObjectName实例
        ObjectName name = new ObjectName("com.example.mbeans:type=App");
        // 将AppMbean对象注册到MBeanServer上去
        mbs.registerMBean(mbean, name);

        // Wait forever
        //System.out.println("Waiting forever...");
        //Thread.sleep(Long.MAX_VALUE);

        //创建一个AdaptorServer，AdaptorServer也是一个MBean，提供MBean的HTML管理界面。
        ObjectName adapterName = new ObjectName("com.example.mbeans:name=htmladapter,port=8888");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer(8888);
        mbs.registerMBean(adapter, adapterName);
        adapter.start();
    }
}
