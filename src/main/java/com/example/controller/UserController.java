package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangqian
 * created on 2019-08-29
 * @version 1.0.0
 * @program springboot-plus-demo
 * @description 控制器
 */
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @ApiOperation("查询用户列表接口")
    @GetMapping("/list")
    public List<User> getUserList() {
        return mapper.selectList(null);
    }
}
