package com.springframe.beans.factory.support;

import com.springframe.beans.factory.config.BeanDefinition;

/**
 * 定义注册bean的方法，bean的Name和信息注册
 */
public interface BeanDefinitionRegistry {
//    注册一个bean信息
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
