package com.springframe.beans.support;

import com.springframe.BeansException;
import com.springframe.beans.config.BeanDefinition;

/**
 * 定义注册bean的方法，bean的Name和信息注册
 */
public interface BeanDefinitionRegistry {
//    注册一个bean信息
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException 如果找不到BeanDefintion
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 是否包含指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回定义的所有bean的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
