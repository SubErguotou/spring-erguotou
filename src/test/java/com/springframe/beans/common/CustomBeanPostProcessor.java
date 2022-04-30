package com.springframe.beans.common;

import com.springframe.BeansException;
import com.springframe.beans.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    //实例初始化前要做的操作
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("友利创建前");
        return null;
    }
    // 实例初始化后要做的操作
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("友利创建后");
        return null;
    }
}
