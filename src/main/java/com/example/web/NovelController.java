package com.example.web;

import com.example.dto.ResponseResult;
import com.example.entity.Novel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-05-06
 * @version  1.0.0
 */
@RestController
@RequestMapping("/novels")
@ResponseResult
public class NovelController {

    @GetMapping("{id}")
    public Novel getNovel(@PathVariable("id") Long id) {
        Novel novel = new Novel();
        novel.setId(id);
        novel.setTitle("红楼梦");
        novel.setContent("贾宝玉和林黛玉");
        return novel;
    }
}
