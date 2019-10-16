package com.example.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @program demo1
 * @description @PrepareForTest注解（PrepareFotTest注解会修改传入参数类的字节码，通过修改字节码达到模拟final、static、私有方法、系统类等的功能），此注解可写在类上也可写在方法上
 * @author wangqian
 * created on 2019-10-15
 * @version  1.0.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LocalServiceImpl.class, RemoteServiceImpl.class, Node.class}) //PrepareForTest修改local类的字节码以覆盖new的功能
public class PowerMockTest {

    @InjectMocks    //此注解表示这个对象需要被注入mock对象
    private LocalServiceImpl localService;

    @Mock   //此注解会自动创建1个mock对象并注入到@InjectMocks对象中
    private RemoteServiceImpl remoteService;
    /**
     * mock new关键字
     */
    @Test
    public void testNew() throws Exception {
        Node target = new Node(1, "target");
        //当传入任意int且name属性为"name"时，new对象返回为target
        //当参数条件使用了any系列方法时，剩余的参数都得使用相应的模糊匹配规则，如eq("name")代表参数等于"name"
        //剩余还有isNull(), isNotNull(), isA()等方法
        PowerMockito.whenNew(Node.class).withArguments(anyInt(), eq("name")).thenReturn(target);
        Node result = localService.getLocalNode(2, "name");
        assertEquals(target, result); //返回值为target
        assertEquals(1, result.getNum());
        assertEquals("target", result.getName());

        //未指定name为"test"的返回值，默认返回null
        Node result2 = localService.getLocalNode(1, "test");
        assertNull(result2);
    }

    /**
     * mock final方法
     */
    @Test
    public void testFinal() {
        Node target = new Node(2, "mock");
        PowerMockito.when(remoteService.getFinalNode()).thenReturn(target); //指定返回值

        Node result = remoteService.getFinalNode(); //直接调用final方法，返回mock后的值
        assertEquals(target, result); //验证返回值
        assertEquals(2, result.getNum());
        assertEquals("mock", result.getName());
    }

    /**
     * mock static方法
     */
    @Test
    public void testStatic() {
        Node target = new Node(2, "mock");
        PowerMockito.mockStatic(Node.class); //mock static方法前需要加这一句
        PowerMockito.when(Node.getStaticNode()).thenReturn(target); //指定返回值

        Node result = Node.getStaticNode(); //直接调用static方法，返回mock后的值
        assertEquals(target, result); //验证返回值
        assertEquals(2, result.getNum());
        assertEquals("mock", result.getName());
    }

    /**
     * mock 私有方法
     */
    @Test
    public void testPrivate() throws Exception {
        Node target = new Node(2, "mock");
        //按照真实代码调用privateMethod方法
        PowerMockito.when(remoteService.getPrivateNode()).thenCallRealMethod();
        //私有方法无法访问，类似反射传递方法名和参数，此处无参数故未传
        PowerMockito.when(remoteService, "privateMethod").thenReturn(target);

        Node result = remoteService.getPrivateNode();
        assertEquals(target, result); //验证返回值
        assertEquals(2, result.getNum());
        assertEquals("mock", result.getName());
    }

    /**
     * mock 系统类方法
     */
    @Test
    public void testSystem() {
        PowerMockito.mockStatic(System.class); //调用的是系统类的静态方法，所以要加这一句
        PowerMockito.when(System.getProperty("abc")).thenReturn("mock"); //设置System.getProperty("abc")返回"mock"
        PowerMockito.when(remoteService.getSystemPropertyNode()).thenCallRealMethod(); //设置mock对象调用实际方法

        Node result = remoteService.getSystemPropertyNode(); //按代码会返回一个name属性为"mock"的对象
        assertEquals(0, result.getNum()); //int默认值为0
        assertEquals("mock", result.getName()); //remoteService对象中调用System.getProperty("abc")返回的是上面设置的"mock"
    }

}
