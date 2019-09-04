package com.example.web;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper mapper;

    @ApiOperation("查询用户列表接口")
    @GetMapping("/list")
    public List<User> getUserList() {
        log.info("请求查询用户列表接口开始");
        List<User> list = mapper.selectList(null);
        log.info("请求查询用户列表接口结束");
        return list;
    }
}
