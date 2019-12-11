package com.example.demo.designer.strategy1;
/**
 * @program demo1
 * @description 骑自行车出行
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class Bus implements Way {
    @Override
    public void action() {
        System.out.println("坐公交出行");
    }
}
