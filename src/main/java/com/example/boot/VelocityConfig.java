package com.example.boot;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-03
 * @version  1.0.0
 */
@Configuration
public class VelocityConfig {

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine engine = new VelocityEngine();
        return engine;
    }
}
