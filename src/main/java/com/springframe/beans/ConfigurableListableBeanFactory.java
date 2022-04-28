package com.springframe.beans;

import com.springframe.BeansException;
import com.springframe.beans.config.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory{
    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException 如果找不到BeanDefintion
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}