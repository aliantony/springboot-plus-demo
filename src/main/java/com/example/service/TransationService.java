package com.example.service;

import com.example.entity.Dept;
import com.example.entity.Novel;
import com.example.mapper.DeptMapper;
import com.example.mapper.NovelMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program demo1
 * @description 用户
 * @author wangqian
 * created on 2019-12-02
 * @version  1.0.0
 * spring事务实现原理参考：https://zhuanlan.zhihu.com/p/54067384
 * PlatformTransactionManager定义了通用的事务操作行为，其依赖TransactionDefinition和TransactionStatus接口
 * PlatformTransactionManager的实现有，DataSourceTransactionManager，JtaTransactionManager（需要web容器支持，weblogic,websphre可以支持）
 * 默认获取应用服务器的JTA资源管理器，获取用开源的atomiko获取实现了JtaTransactionManager的JTA资源管理器
 * tomcat需要开源框架，有基于JTA的atomiko
 * 基于数据库XA协议的JTA（JAVA TRANSACTION API）事务实现原理，参考https://juejin.im/post/5b83b8756fb9a019ff71389d
 */
@Service
public class TransationService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private NovelMapper novelMapper;


    /*//默认REQUIRED
    @Transactional(propagation = Propagation.REQUIRED)
    public void parent(Dept dept) {
        deptMapper.insert(dept);
        Novel novel = new Novel();
        novel.setTitle("仙丹修炼之路");
        novel.setContent("练成了长生不老之药");
        child(novel); //没捕获异常，都会回滚
    }*/

    //默认REQUIRED
    /*@Transactional(propagation = Propagation.REQUIRED)
    public void parent(Dept dept) {
        deptMapper.insert(dept);
        Novel novel = new Novel();
        novel.setTitle("仙丹修炼之路");
        novel.setContent("练成了长生不老之药");
        try {
            child(novel); //捕获了异常parent和child都会成功
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 情况三 内部事务异常回滚，外部事务正常提交
     * @param dept
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void parent(Dept dept) {
        deptMapper.insert(dept);
        Novel novel = new Novel();
        novel.setTitle("仙丹修炼之路");
        novel.setContent("练成了长生不老之药");
        try {
            ((TransationService)AopContext.currentProxy()).child(novel); //实现内部事务回滚，外部事务不回滚
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 两个事务，外部事务会挂起，parent没捕获，都会回滚，相当于在parent直接调用child
     * parent捕获了异常，prent和child都会成功，本质是因为动态代理，child没有事务
     * @param novel
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void child(Novel novel) {
        novelMapper.insert(novel);
        int s = 1 / 0;
    }

    /**
     * 默认REQUIRED
     * @param dept
     */
    @Transactional
    public void nest(Dept dept) {
        deptMapper.insert(dept);
        Novel novel = new Novel();
        novel.setTitle("仙丹修炼之路");
        novel.setContent("练成了长生不老之药");
        try {
            //savepoint(novel);//动态代理导致了savepoint方法的事务注解失效

            /**
             * REQUIRES_NEW也能实现内部事务回滚，外部事务不受影响，唯一不同是NESTED是基于jdbc savepoint实现的
             */
            ((TransationService)AopContext.currentProxy()).savepoint(novel); //实现内部事务回滚，外部事务不回滚
        } catch (RuntimeException e) {
            System.out.println("内部事务回滚到保存点，此处可以调用其它方法处理内部事务回滚后的其它逻辑");
            e.printStackTrace();
        }
    }

    /**
     * 基于保存点，前面提交成功的事务提交不回滚，保存点之后的事务抛出异常后回滚到保存点
     * 保存点参考：https://my.oschina.net/heroShane/blog/197582
     * Propagation.NESTED,如果没有，就新建一个事务；如果有，就在当前事务中嵌套其他事务。
     * 嵌套事务是外部事务的一部分, 只有外部事务结束后它才会被提交.
     * 外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，
     * 修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
     *
     * 在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，
     * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
     */
    @Transactional(propagation = Propagation.NESTED)
    public void savepoint(Novel novel) {
        novelMapper.insert(novel);
        int s = 1 / 0;
    }

    @Transactional
    public void outer() {
        Dept dept = new Dept();
        dept.setName("研发部");
        dept.setParentId(1);
        deptMapper.insert(dept);
        //线程里面开启的事务相当于一个新的请求，和外部事务互不影响
        Thread thread = new Thread(() -> {
            ((TransationService)AopContext.currentProxy()).threadCall(); //threadCall事务失效，动态代理的缘故
        });
        /**
         * 线程里面的事务处理之前可能主线程的事务已经提交了。所以没有影响
         */
        thread.start();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void threadCall() {
        Novel novel = new Novel();
        novel.setTitle("仙丹修炼之路");
        novel.setContent("练成了长生不老之药");
        novelMapper.insert(novel);
        int s = 1 / 0;
    }
}
