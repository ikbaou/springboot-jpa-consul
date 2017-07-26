package com.example.demo.util;

import javax.inject.Singleton;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
 
@Singleton
@Component
public class SpringBeanResolver implements ApplicationContextAware, BeanResolver {
 
    private static ApplicationContext CONTEXT;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
 
    /**
     * Get a Spring bean by type.
     **/
    public <T> T getBean(Class<T> beanClass) {
        return CONTEXT.getBean(beanClass);
    }
 
}