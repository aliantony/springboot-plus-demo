package com.example.service;

import com.example.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransationServiceTest {

    @Autowired
    private TransationService txService;

    @Test
    public void parent() {
        Dept dept = new Dept();
        dept.setName("研发部");
        dept.setParentId(1);
        txService.parent(dept);
    }

    @Test
    public void nest() {
        Dept dept = new Dept();
        dept.setName("研发部");
        dept.setParentId(1);
        txService.nest(dept);
    }

    @Test
    public void outer() {
        txService.outer();
    }
}