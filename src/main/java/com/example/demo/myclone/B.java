package com.example.demo.myclone;

import lombok.Data;

import java.io.Serializable;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-05-08
 * @version  1.0.0
 */
@Data
public class B implements Serializable,Cloneable {
    private Integer b;
    private A a;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        B b = (B) super.clone();
        A a = b.getA();
        b.setA((A) a.clone());
        return b;
    }
}
