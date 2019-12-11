package com.example.demo.designer.decorator;
/**
 * @program demo1
 * @description 增加打电话后报时间
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class GiveCurrentTimePhoneDecorator extends PhoneDecorator {

    public GiveCurrentTimePhoneDecorator(Phone phone) {
      super(phone);
    }

    public void currentTime() {
        System.out.println("当前的时间是：" + System.currentTimeMillis());
    }

    @Override
    public void call() {
        super.call();
        currentTime();
    }
}
