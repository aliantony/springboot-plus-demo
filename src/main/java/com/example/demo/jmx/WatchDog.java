package com.example.demo.jmx;

import lombok.Data;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-29
 * @version  1.0.0
 */
@Data
public class WatchDog {

    private String pid;
    private String upTime;

    @Override
    public String toString() {
        return "WatchDog{" +
                "pid='" + pid + '\'' +
                ", upTime='" + upTime + '\'' +
                '}';
    }
}
