package com.springframe.beans.support;

import com.springframe.beans.config.BeanDefinition;


public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    /**
     * Cglib动态生成子类（未实现）
     * @param beanDefinition
     * @return
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        return null;
    }
}
