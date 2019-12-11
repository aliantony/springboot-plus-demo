package com.example.demo.designer.decorator;
/**
 * @program demo1
 * @description 增加打电话前听音乐
 * @author wangqian
 * created on 2019-12-11
 * @version  1.0.0
 */
public class MusicPhoneDecorator extends PhoneDecorator {
    public MusicPhoneDecorator(Phone phone) {
      super(phone);
    }
    public void listenMucsic() {
        System.out.println("继续跑 带着赤子的骄傲，生命的闪耀不坚持到底怎能看到，与其苟延残喘不如纵情燃烧");
    }

    @Override
    public void call() {
        listenMucsic();
        super.call();
    }
}
