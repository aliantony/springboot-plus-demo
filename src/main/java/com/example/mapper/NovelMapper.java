package com.example.mapper;

import com.example.config.MyBaseMapper;
import com.example.entity.Novel;

/**
 * <p>
 * MP 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
public interface NovelMapper extends MyBaseMapper<Novel> {
    /**
     * 插入数据
     * @param node
     */
    void save(Novel node);
}