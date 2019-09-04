package com.example.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program demo1
 * @description 线程池提交异步任务
 * @author wangqian
 * created on 2019-09-04
 * @version  1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTest {

    @Autowired
    private Worker task;

    @Test
    public void test() throws Exception {

        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();

        //Thread.currentThread().join();
    }
}
