package com.example.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class CustomizeApplicationContext extends AnnotationConfigServletWebServerApplicationContext {

    Logger logger = LoggerFactory.getLogger(CustomizeApplicationContext.class);

    @Override
    protected void initPropertySources() {
        super.initPropertySources();
        logger.info("execute override initPropertySources");
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        super.postProcessBeanFactory(beanFactory);
        logger.info("execute override postProcessBeanFactory");
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        logger.info("execute override onRefresh");
    }
}
