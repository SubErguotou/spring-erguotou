package com.springframe.beans.factory;

import com.springframe.beans.BeansException;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.config.BeanPostProcessor;

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

    /**
     * 添加前后置处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
