package com.example.entity;

import lombok.Data;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-12-02
 * @version  1.0.0
 */
@Data
public class Dept extends BaseEntity{
    private String name;
    private Integer parentId;
}
