package com.example.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author wangqian
 * created on 2019-10-08
 * @version 1.0.0
 * @program demo1
 * @description 无需初始化mock this
 */
@RunWith(MockitoJUnitRunner.class)
public class MockItoBuilt {

    @Mock
    private List mockList;

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }
}
