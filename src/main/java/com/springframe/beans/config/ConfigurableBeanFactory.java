package com.springframe.beans.config;

import com.springframe.beans.BeanFactory;

/**
 * 可配置的bean工厂
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {
    //添加前后置处理器进池子。
    void addPostProcessor(BeanPostProcessor beanPostProcessor);
}
