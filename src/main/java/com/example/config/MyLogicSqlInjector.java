package com.example.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.example.methods.DeleteAll;
import com.example.methods.MyInsertAll;
import com.example.methods.BatchInsert;

import java.util.List;

/**
 * 自定义 SqlInjector
 *
 * @author miemie
 * @since 2018-08-13
 */
public class MyLogicSqlInjector extends DefaultSqlInjector {

    /**
     * 如果只需增加方法，保留MP自带方法
     * 可以super.getMethodList() 再add
     * @return
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAll());
        methodList.add(new MyInsertAll());
        methodList.add(new BatchInsert());
        //methodList.add(new SelectById());
        return methodList;
    }
}