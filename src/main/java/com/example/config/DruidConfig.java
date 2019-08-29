package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource durid() {
        return new DruidDataSource();
    }

    //配置Druid的监控

    //1.配置一个管理后台的sevlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        //设置ip白名单
        initParams.put("allow", "");
        //设置ip黑名单。deny优先级高于allow
        initParams.put("deny", "192.168.10.125");
        bean.setInitParameters(initParams);
        return bean;
    }

    //2.配置一个web监控的filter
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        //忽略过滤的形式
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        //设置过滤器过滤路径
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
