package com.example.demo.designer.strategy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 使用AnnotationConfigApplicationContext测试不能把配置项注入到map，正常启动web项目可以
 */
@Component
//@PropertySource("classpath:application.yml") //指定yml文件位置
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ent")
public class EntAlias {

    private HashMap<String, String> aliasMap = new HashMap<>();
    
    public static final String DEFAULT_STATEGY_NAME = "defaultStrategy";

    public HashMap<String, String> getAliasMap() {
        return aliasMap;
    }

    public void setAliasMap(HashMap<String, String > aliasMap) {
        this.aliasMap = aliasMap;
    }

    String of(String entNum) {
        return aliasMap.get(entNum);
    }
}