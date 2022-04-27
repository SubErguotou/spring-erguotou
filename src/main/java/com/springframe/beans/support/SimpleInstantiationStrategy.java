package com.springframe.beans.support;

import com.springframe.BeansException;
import com.springframe.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 简单实例化策略，使用无参构造方法创建bean
 */

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        try {
//            获取无参对象
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
