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
public class Novel extends BaseEntity{
    private String title;
    private String content;
}
