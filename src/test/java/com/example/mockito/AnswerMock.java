package com.example.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @program demo1
 * @description  使用方法预期回调接口生成期望值（Answer结构）
 * @author wangqian
 * created on 2019-10-15
 * @version  1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerMock {

    @Mock
    private List mockList;

    @Test
    public void answerTest(){
        when(mockList.get(anyInt())).thenAnswer(new CustomAnswer());
        assertEquals("hello world:0",mockList.get(0));
        assertEquals("hello world:999",mockList.get(999));
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world:"+args[0];
        }
    }

    @Test
    public void answer_with_callback(){
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return "hello world:"+args[0];
        });
        assertEquals("hello world:0",mockList.get(0));
        assertEquals("hello world:999",mockList.get(999));
    }

    @Test
    public void unstubbed_invocations(){
        //用when预设
        //mock对象使用Answer来对未预设的调用返回默认期望值
        List mock = mock(List.class, invocation -> 999);
        //下面的get(1)没有预设，通常情况下会返回NULL，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.get(1));
        //下面的size()没有预设，通常情况下会返回0，但是使用了Answer改变了默认期望值
        assertEquals(999,mock.size());
    }
}
