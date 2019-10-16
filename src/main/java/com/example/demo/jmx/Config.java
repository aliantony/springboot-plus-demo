package com.example.demo.jmx;
/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-29
 * @version  1.0.0
 */
public class Config implements ConfigMXBean {
    private boolean isNotify;

    public boolean isNotify() {
        return isNotify;
    }

    @Override
    public void setNotify(boolean notify) {
        isNotify = notify;
        System.out.println("是否开启通知：" + isNotify);
    }
}
