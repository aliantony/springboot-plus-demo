package com.example.dao;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(7, userList.size());
        userList.forEach(System.out::println);
    }

    public static void main(String[] args) {
       String x = "001001001002";
       StringBuilder b = new StringBuilder(x);
        System.out.println(b.replace(x.length() - 3, x.length(), "113").toString());

    }

}