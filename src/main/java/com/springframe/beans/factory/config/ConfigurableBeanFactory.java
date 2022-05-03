package com.springframe.beans.factory.config;

import com.springframe.beans.factory.BeanFactory;
import com.springframe.beans.factory.HierarchicalBeanFactory;

/**
 * 可配置的bean工厂
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    //添加前后置处理器进池子。
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
