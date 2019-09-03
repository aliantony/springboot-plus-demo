package com.example.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private SynTask task;

	@Autowired
	private AsynTask asynTask;

	@Autowired
	private RetAsynTask retAsynTask;

	@Test
	public void synTest() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}

	@Test
	public void asynTest() throws Exception {
		asynTask.doTaskOne();
		asynTask.doTaskTwo();
		asynTask.doTaskThree();
	}

	@Test
	public void retsynTest() throws Exception {
		long start = System.currentTimeMillis();

		Future<String> task1 = retAsynTask.doTaskOne();
		Future<String> task2 = retAsynTask.doTaskTwo();
		Future<String> task3 = retAsynTask.doTaskThree();

		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}