package com.example.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author wangqian
 * created on 2019-10-15
 * @version 1.0.0
 * @program demo1
 * @description
 */
@RunWith(MockitoJUnitRunner.class) //让测试运行于Mockito环境
public class MockItoTest {
    @InjectMocks    //此注解表示这个对象需要被注入mock对象
    private LocalServiceImpl localService;
    @Mock   //此注解会自动创建1个mock对象并注入到@InjectMocks对象中
    private RemoteServiceImpl remoteService;

    //如果不使用上述注解，可以使用@Before方法来手动进行mock对象的创建和注入，但会几行很多代码
    /*
    private LocalServiceImpl localService;
    private RemoteServiceImpl remoteService;

    @Before
    public void setUp() throws Exception {
        localService = new LocalServiceImpl();
        remoteService = Mockito.mock(RemoteServiceImpl.class);  //创建Mock对象
        Whitebox.setInternalState(localService, "remoteService", remoteService); //注入依赖对象
    }
    */

    @Test
    public void testMock() {
        Node target = new Node(1, "target");    //创建一个Node对象作为返回值
        Mockito.when(remoteService.getRemoteNode(1)).thenReturn(target); //指定当remoteService.getRemoteNode(int)方法传入参数为1时返回target对象
        Node result = localService.getRemoteNode(1);    //调用我们的业务方法，业务方法内部调用依赖对象方法
        assertEquals(target, result);   //可以断言我们得到的返回值其实就是target对象
        assertEquals(1, result.getNum());   //具体属性和我们指定的返回值相同
        assertEquals("target", result.getName());   //具体属性和我们指定的返回值相同
        Node result2 = localService.getRemoteNode(2);   //未指定参数为2时对应的返回规则
        assertNull(result2);    //未指定时返回为null
    }
}
