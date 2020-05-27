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
public class A implements Serializable, Cloneable{
    private int a;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
