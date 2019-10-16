package com.example.mockito;


import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author wangqian
 * created on 2019-10-08
 * @version 1.0.0
 * @program demo1
 * @description
 */
public class MockItoTestAnnotation {

    @Mock
    private List mockList;

    public MockItoTestAnnotation() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }

}
