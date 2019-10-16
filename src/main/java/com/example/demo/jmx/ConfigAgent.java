package com.example.demo.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-29
 * @version  1.0.0
 */
public class ConfigAgent {

    public static void main(String[] args) throws Exception {
        // 建立一个MBeanServer，用来管理MBean
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // 创建AppMBean对象
        Config mbean = new Config();
        // 为AppMBean 创建ObjectName实例
        ObjectName name = new ObjectName("com.example.mbeans:type=Config");
        // 将AppMbean对象注册到MBeanServer上去
        mbs.registerMBean(mbean, name);

        ObjectName adapterName = new ObjectName("com.example:name=htmlAdapter,port=8888");
        HtmlAdaptorServer adaptorServer = new HtmlAdaptorServer(8888);
        mbs.registerMBean(adaptorServer, adapterName);
        adaptorServer.start();
    }
}
