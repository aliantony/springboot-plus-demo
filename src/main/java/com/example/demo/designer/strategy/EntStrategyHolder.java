package com.example.demo.designer.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EntStrategyHolder {

    @Autowired
    private EntAlias config;

    //也能给List变量自动装配
    //@Autowired
    //private List<EntStrategy> entStrategyList;
    // 关键功能 Spring 会自动将 EntStrategy 接口的类注入到这个Map中
    @Autowired
    private Map<String, EntStrategy> entStrategyMap;

    public EntStrategy getBy(String entNum) {
        config.getAliasMap().get("entA");
        return entStrategyMap.get(entNum);
    }
}
