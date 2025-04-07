package com.dw.chat.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Spring上下文工具类
 *
 * @author dawei
 */
public class SpringContextHolder implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        checkApplicationContext();
        try {
            return (T) applicationContext.getBean(beanName);
        } catch (NoSuchBeanDefinitionException ex) {
            logger.error("Failed to getBean.", ex);
            return null;
        }
    }

    public static <T> T getBean(Class<T> requiredType) {
        checkApplicationContext();
        try {
            return applicationContext.getBean(requiredType);
        } catch (NoSuchBeanDefinitionException ex) {
            logger.error("Failed to getBean.", ex);
            return null;
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> beanClass){
        checkApplicationContext();
        try {
            return applicationContext.getBeansOfType(beanClass);
        } catch (BeansException e) {
            logger.error("Failed to getBeansOfType.", e);
            return null;
        }
    }

    private static void checkApplicationContext() {
        if (SpringContextHolder.applicationContext == null) {
            throw new RuntimeException("applicationContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }


}
