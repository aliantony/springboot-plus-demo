package com.example.demo.designer.strategy1;
/**
 * @program demo1
 * @description 策略上下文，根据不同的策略执行
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class PerSon {
    public void goHome(Way way) {
        way.action();
    }
}
