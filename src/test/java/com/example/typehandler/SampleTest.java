package com.example.typehandler;

import com.example.entity.Currency;
import com.example.entity.OtherInfo;
import com.example.entity.User;
import com.example.entity.Wallet;
import com.example.enums.AgeEnum;
import com.example.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 内置 类型处理器 演示
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 自定义类型处理器演示参考 mybatis-plus-sample-deluxe 模块
     */
    @Test
    public void test() {
        // 自己去观察打印 SQL 目前随机访问 user_2018  user_2019 表
        User Jone = userMapper.selectById(1);
        System.out.println(Jone);
        Wallet w = new Wallet();
        w.setName("钱包");
        List<Currency> currencies = new ArrayList<>();
        Currency currency = new Currency();
        currency.setAmount(1000D);
        currency.setType("人们");
        currencies.add(currency);
        w.setCurrencyList(currencies);
        Jone.setWallet(w);
        OtherInfo info = new OtherInfo();
        info.setCity("成都");
        info.setSex("男");
        Jone.setOtherInfo(info);
        Jone.setId(11L);
        userMapper.insert(Jone);
    }

    @Test
    public void testFill() {
        //User Jone = userMapper.selectById(1);
        //Jone.setName("王五");
        //userMapper.updateById(Jone);
        User u =  new User();
        u.setName("张素");
        u.setId(12L);
        u.setAge(AgeEnum.THREE);
        userMapper.insert(u);
    }
}